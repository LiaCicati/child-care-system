package dao;

import model.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class BookingDAOImpl implements BookingDAO
{
  private static BookingDAOImpl instance;

  public BookingDAOImpl() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized BookingDAOImpl getInstance() throws SQLException
  {

    if (instance == null)
    {
      instance = new BookingDAOImpl();
    }
    return instance;
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(URL, USERNAME, PASSWORD);
  }

  //  public void addBooking(Booking booking) throws SQLException
  //  {
  //    try (Connection connection = getConnection())
  //    {
  //      PreparedStatement statement = connection.prepareStatement(
  //          "INSERT INTO booking(bookingID,parent_email,babysitter_email,dateTime,status,description,dateTimeOfBooking) VALUES (?,?,?,?,?,?,?)");
  //      statement.setInt(1, booking.getBookingID());
  //      statement.setString(2, booking.getParent().getEmail());
  //      statement.setString(3, booking.getBabysitter().getEmail());
  //    }
  //  }
  public Booking addBooking(Booking booking, Account parent, Account babysitter)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO booking (start_time, end_time,  parent_email, babysitter_email, description, status, dateTimeOfBooking, booking_id) VALUES (?,?,?,?,?,?,?,?)");

      //      VALUES ('12:20', '14:20', 'ana@gmail.com', 'mira@gmail.com', 'You need to cook', 5, 'Pending', '04/06/2021', '01/06/2021 14:30');

      //      statement
      //          .setTime(1, Time.valueOf(booking.getTime().getStartTime().getSimpleTime()));
      System.out.println(booking.getTime().getStartTime().getSimpleTime());
      statement.setObject(1,booking.getTime().getStartTime().getSimpleTime(), Types.TIME);
      statement.setObject(2, booking.getTime().getEndTime().getSimpleTime(), Types.TIME);
      //      System.out.println(
      //          "simple time: " + booking.getTime().getStartTime().getSimpleTime());
      //      statement.setTime(2, Time.valueOf(booking.getTime().getEndTime().toString()));
      System.out.println("AICI 1: " + booking.getTime().getStartTime());
      statement.setString(3, parent.getEmail());
      statement.setString(4, babysitter.getEmail());
      System.out.println("parent email: " + booking.getParent().getEmail());
      System.out
          .println("babysitter email: " + booking.getBabysitter().getEmail());
      statement.setString(5, booking.getDescription());
      //      statement.setInt(6, booking.getBookingID());
      System.out.println("ID of booking" + booking.getBookingID());
      //    statement.setObject(4,booking.getTime().getStartTime(),Types.TIMESTAMP);
      //    statement.setObject(5,booking.getTime().getEndTime(),Types.TIMESTAMP);
      statement.setString(6, booking.getStatus());
      System.out.println("Status: " + booking.getStatus());
      //      statement.setString(7, booking.getTime().getStartTime().getSimpleDate());
      //      System.out
      //          .println("Date: " + booking.getTime().getStartTime().getSimpleDate());
      //      System.out.println("Date/time: " + booking.getDateTimeOfBooking());
      statement.setObject(7, booking.getDateTimeOfBooking());
      statement.setInt(8, booking.getBookingID());

      statement.executeUpdate();

      Booking booking1 = new Booking(new TimeInterval(
          new MyDateTime(booking.getTime().getStartTime().getDay(),
              booking.getTime().getStartTime().getMonth(),
              booking.getTime().getStartTime().getYear()),
          new MyDateTime(booking.getTime().getEndTime().getDay(),
              booking.getTime().getEndTime().getMonth(),
              booking.getTime().getEndTime().getYear())), ((Parent) parent),
          ((Babysitter) babysitter), booking.getDescription());
      System.out.println(booking1);
      return booking1;
    }
  }


  //  parent_email      VARCHAR(100),
  //  babysitter_email  VARCHAR(100),
  //  description       VARCHAR(1000),
  //  status            VARCHAR(50),
  //  date              DATE,
  //  dateTimeOfBooking timeStamp,
  @Override public BookingList getAllBookings() throws SQLException
  {
    try (Connection connection = getConnection())
    {
      BookingList bookingList = new BookingList();
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM booking ;");
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next())
      {
        int thisDay = LocalDate.now().getDayOfMonth();
        int thisMonth = LocalDate.now().getMonthValue();
        int thisYear = LocalDate.now().getYear();
        int thisHour = LocalTime.now().getHour();
        int thisMinute = LocalTime.now().getMinute();
//                MyDateTime startTime = ((MyDateTime) resultSet.getObject("start_time"));
        Object startTime = resultSet.getObject("start_time");
        Object endTime = resultSet.getObject("end_time");
//        String workingTime = resultSet.getString("dateTime");
        String parentEmail = resultSet.getString("parent_email");
        String babysitterEmail = resultSet.getString("babysitter_email");
        String description = resultSet.getString("description");
        String status = resultSet.getString("status");
        String dateOfBooking =  resultSet.getString("dateTimeOfBooking");
//        String date = resultSet.getString("date");
        Integer id = resultSet.getInt("booking_id");
//        Booking booking = new Booking(
//            new TimeInterval(new MyDateTime(workingTime),
//                new MyDateTime(Integer.parseInt(workingTime))),
//            (Parent) AccountDAOImpl.getInstance().readByEmail(parentEmail),
//            (Babysitter) AccountDAOImpl.getInstance()
//                .readByEmail(babysitterEmail), description);
        Booking booking = new Booking(new TimeInterval(null, null),(Parent) AccountDAOImpl.getInstance().readParentByEmail(parentEmail),
                        (Babysitter) AccountDAOImpl.getInstance()
                            .readBabysitterByEmail(babysitterEmail), description);

        bookingList.addBooking(booking);
      }
      return bookingList;
    }

  }
  //
  //  public AccountList allBabysitters() throws SQLException
  //  {
  //    AccountList babysitters = new AccountList();
  //    try (Connection connection = getConnection())
  //    {
  //      PreparedStatement statement = connection
  //          .prepareStatement("SELECT * FROM babysitter ;");
  //      ResultSet resultSet = statement.executeQuery();
  //
  //      while (resultSet.next())
  //      {
  //        String username = resultSet.getString("username");
  //        String email = resultSet.getString("email");
  //        String firstName = resultSet.getString("first_name");
  //        String lastName = resultSet.getString("last_name");
  //        String password = resultSet.getString("password");
  //        Date date = (Date) resultSet.getObject(" birthday ");
  //        MyDateTime birthday = new MyDateTime(date.toLocalDate().getDayOfMonth(),
  //            date.toLocalDate().getMonthValue(), date.toLocalDate().getYear());
  //
  //        double experience = resultSet.getDouble("years_of_experience");
  //        double payment = resultSet.getDouble("payment ");
  //        String mainLanguage = resultSet.getString("language");
  //        boolean hasFirstAidCertificate = resultSet
  //            .getBoolean("first_aid_certificate");
  //        Account babysitter = new Babysitter(firstName, lastName, username,
  //            email, password, birthday, experience, payment, mainLanguage,
  //            hasFirstAidCertificate);
  //        babysitters.addAccount(babysitter);
  //      }
  //      return babysitters;
  //    }
  //  }
}
