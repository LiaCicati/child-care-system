package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Booking implements Serializable
{
  private int bookingID;
  private LocalDateTime dateTimeOfBooking;
  private TimeInterval time;
  private Babysitter babysitter;
  private Parent parent;
  private String status;
  private String description;
  public static final String PENDING = "Pending";
  public static final String ACCEPTED = "Accepted";
  public static final String REJECTED = "Rejected";

  public Booking(TimeInterval time, Parent parent, Babysitter babysitter,
      String description)
  {
    this.time = time;
    int minimum = 1;
    int maximum = 100;
    this.bookingID = (int) ((Math.random() * maximum) + minimum);
    this.dateTimeOfBooking = LocalDateTime.now();
    this.parent = parent;
    this.babysitter = babysitter;
    this.status = PENDING;
    this.description = description;
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

  public String getDateTimeOfBooking()
  {
    return dateTimeOfBooking
        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy, hh:mm"));
  }

  public String getBookingWorkTime()
  {
    return getTime().getStartTime().getSimpleDate() + ", " + getTime()
        .getStartTime().getSimpleTime() + " - " + getTime().getEndTime()
        .getSimpleTime();
  }

  public Parent getParent()
  {
    return parent;
  }

  public String getStatus()
  {
    return status;
  }

  public String getDescription()
  {
    return description;
  }

  public void setStatus(String status)
  {
    this.status = status;
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
    if (dateTimeOfBooking == null)
    {
      throw new IllegalArgumentException(
          "Please select the date and time of your booking");
    }
    this.dateTimeOfBooking = dateTimeOfBooking;
  }

  public void setTime(TimeInterval time)
  {
    this.time = time;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public String toString()
  {
    return "Booking id: " + bookingID + "\n" + "Parent: " + parent
        + "Babysitter: " + babysitter + "\n" + "Time: " + time + "\n"
        + "Date and time of booking: " + getDateTimeOfBooking() + "\n"
        + "Description: " + description;
  }
}