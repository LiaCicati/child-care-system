package dao;

import database.Database;
import model.Booking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

  public void addBooking(Booking booking) throws SQLException
  {
    try (Connection connection = Database.getInstance().getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO booking(bookingID,parent_email,babysitter_email,dateTime,status,description,dateTimeOfBooking) VALUES (?,?,?,?,?,?,?)");
      statement.setInt(1, booking.getBookingID());
      statement.setString(2, booking.getParent().getEmail());
      statement.setString(3, booking.getBabysitter().getEmail());
    }
  }

}
