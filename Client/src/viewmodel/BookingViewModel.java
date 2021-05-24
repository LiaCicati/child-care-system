package viewmodel;

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

  public BookingViewModel(Booking booking)
  {
    parent = new SimpleStringProperty(booking.getParent().getFirstName());
    dateTime = new SimpleStringProperty(booking.getDateTimeOfBooking());
    date = new SimpleStringProperty(
        booking.getTime().getStartTime().getSimpleDate());
    startTime = new SimpleStringProperty(
        booking.getTime().getStartTime().getSimpleTime());
    endTime = new SimpleStringProperty(
        booking.getTime().getEndTime().getSimpleTime());
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
}
