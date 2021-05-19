package model;

import java.io.Serializable;
import java.util.ArrayList;

public class BookingList implements Serializable
{
  private ArrayList<Booking> bookings;

  public BookingList()
  {
    this.bookings = new ArrayList<>();
  }

  public void addBooking(Booking booking)
  {
    bookings.add(booking);
  }

  public void removeBooking(Booking booking)
  {
    bookings.remove(booking);
  }

  public int getNumberOfBookings()
  {
    return bookings.size();
  }

  public ArrayList<Booking> getAllBookings()
  {
    return bookings;
  }

  public void removeBookingById(int bookingID)
  {
    bookings.remove(bookingID);
  }

  public boolean contains(Booking booking)
  {
    return bookings.contains(booking);
  }

  public Booking getBookingById(int id)
  {
    for (Booking booking : bookings)
    {
      if (booking.getBookingID() == id)
      {
        return booking;
      }
    }
    return null;
  }

  public Booking getBooking(int index)
  {
    return bookings.get(index);
  }

  public ArrayList<Booking> getTimeOfBooking(TimeInterval time)
  {
    ArrayList<Booking> byTime = new ArrayList<>();
    for (Booking booking : bookings)
    {
      if (booking.getTime() == time)
      {
        byTime.add(booking);
      }
    }
    return byTime;
  }

  public ArrayList<Booking> getBabysitterBookings(Babysitter babysitter)
  {
    ArrayList<Booking> byBabysitter = new ArrayList<>();
    for (Booking booking : bookings)
    {
      if (booking.getBabysitter().equals(babysitter))
      {
        byBabysitter.add(booking);
      }
    }
    return byBabysitter;
  }

  public ArrayList<Booking> getBookingsByParent(Parent parent)
  {
    ArrayList<Booking> byParent = new ArrayList<>();
    for (Booking booking : bookings)
    {
      if (booking.getParent().equals(parent))
      {
        byParent.add(booking);
      }
    }
    return byParent;
  }

  @Override public String toString()
  {
    return "Bookings : " + "\n" + bookings;
  }

}
