package viewmodel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Booking;

public class BookingViewModel
{
  private StringProperty parent;
  private StringProperty dateTime;
  private StringProperty date;
  private StringProperty startTime;
  private StringProperty endTime;
  private IntegerProperty id;
  private StringProperty description;

  private StringProperty babysitter;
  private StringProperty status;

  public BookingViewModel(Booking booking)
  {
    id = new SimpleIntegerProperty(booking.getBookingID());
    parent = new SimpleStringProperty(booking.getParent().getFirstName());
    dateTime = new SimpleStringProperty(booking.getDateTimeOfBooking());
//    date = new SimpleStringProperty(
//        booking.getTime().getStartTime().getSimpleDate());
//    startTime = new SimpleStringProperty(
//        booking.getTime().getStartTime().getSimpleTime());
//    endTime = new SimpleStringProperty(
//        booking.getTime().getEndTime().getSimpleTime());

    babysitter = new SimpleStringProperty(
        booking.getBabysitter().getFirstName() + ", " + booking.getBabysitter()
            .getAge());
    status = new SimpleStringProperty(booking.getStatus());
    description = new SimpleStringProperty(booking.getDescription());
  }

  public StringProperty getDateTime()
  {
    return dateTime;
  }

  public StringProperty getDate()
  {
    return date;
  }

  public StringProperty getParent()
  {
    return parent;
  }

  public StringProperty getStartTime()
  {
    return startTime;
  }

  public StringProperty getEndTime()
  {
    return endTime;
  }

  public StringProperty getBabysitter()
  {
    return babysitter;
  }

  public StringProperty getStatus()
  {
    return status;
  }

  public void setStatus(StringProperty status)
  {
    this.status = status;
  }

  public IntegerProperty getId()
  {
    return id;
  }

  public StringProperty getDescription()
  {
    return description;
  }
}
