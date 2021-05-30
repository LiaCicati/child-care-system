package database;

import model.Booking;
import model.MyDateTime;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class BookingManager
{
  private AccountManager accountManager;

  public BookingManager(AccountManager accountManager)
  {
    this.accountManager = accountManager;

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
