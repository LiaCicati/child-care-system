package dao;

import model.*;

import java.sql.*;

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

      System.out.println(booking.getTime().getStartTime().getSimpleTime());
      statement.setObject(1, booking.getTime().getStartTime().getSimpleTime(),
          Types.TIME);
      statement.setObject(2, booking.getTime().getEndTime().getSimpleTime(),
          Types.TIME);
      statement.setString(3, parent.getEmail());
      statement.setString(4, babysitter.getEmail());
      statement.setString(5, booking.getDescription());
      statement.setString(6, booking.getStatus());
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

        Object startTime = resultSet.getObject("start_time");
        Object endTime = resultSet.getObject("end_time");
        String parentEmail = resultSet.getString("parent_email");
        String babysitterEmail = resultSet.getString("babysitter_email");
        String description = resultSet.getString("description");
        String status = resultSet.getString("status");
        String dateOfBooking = resultSet.getString("dateTimeOfBooking");
        //        String date = resultSet.getString("date");
        Integer id = resultSet.getInt("booking_id");

        Booking booking = new Booking(new TimeInterval(null, null),
            (Parent) AccountDAOImpl.getInstance()
                .readParentByEmail(parentEmail),
            (Babysitter) AccountDAOImpl.getInstance()
                .readBabysitterByEmail(babysitterEmail), description);

        bookingList.addBooking(booking);
      }
      return bookingList;
    }

  }
}
