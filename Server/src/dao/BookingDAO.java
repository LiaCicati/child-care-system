package dao;

import model.*;

import java.sql.SQLException;

public interface BookingDAO extends DAO
{
//   void addBooking(Booking booking) throws SQLException;
  Booking addBooking(Booking booking, Account parent, Account babysitter
       ) throws SQLException;
  BookingList getAllBookings() throws SQLException;
}
