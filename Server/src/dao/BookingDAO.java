package dao;

import model.Booking;

import java.sql.SQLException;

public interface BookingDAO extends DAO
{
   void addBooking(Booking booking) throws SQLException;
}
