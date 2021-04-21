package model;

import java.time.LocalDateTime;


public class Booking
{
  private int bookingID;
  private LocalDateTime dateTimeOfBooking;
  private TimeInterval time;
  private Babysitter babysitter;
  private Parent parent;

  public Booking(TimeInterval time, Parent parent, Babysitter babysitter)
  {
    this.time = time;
    int minimum = 1;
    int maximum = 100;
    this.bookingID = (int) ((Math.random() * maximum) + minimum);
    this.dateTimeOfBooking = null;
    this.parent = parent;
    this.babysitter = babysitter;

  }

  public Babysitter getBabysitter()
  {
    return babysitter;
  }

  public TimeInterval getTime()
  {
    return time;
  }

  public int getBookingID()
  {
    return bookingID;
  }

  public LocalDateTime getDateTimeOfBooking()
  {
    return dateTimeOfBooking;
  }

  public Parent getParent()
  {
    return parent;
  }

  public void setParent(Parent parent)
  {
    this.parent = parent;
  }

  public void setBabysitter(Babysitter babysitter)
  {
    this.babysitter = babysitter;
  }

  public void setDateTimeOfBooking(LocalDateTime dateTimeOfBooking)
  {
    this.dateTimeOfBooking = dateTimeOfBooking;
  }

  public void setTime(TimeInterval time)
  {
    this.time = time;
  }

  public String toString()
  {
    return "Booking id: " + bookingID + "\n" + "Parent: " + parent
        + "Babysitter: " + babysitter + "\n" + "Time: " + time;
  }
}