package viewmodel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Booking;

public class BookingViewModel
{
  private IntegerProperty id;
  private StringProperty parent;
  private StringProperty dateTime;

  public BookingViewModel(Booking booking)
  {
    id = new SimpleIntegerProperty(booking.getBookingID());
    parent = new SimpleStringProperty(booking.getParent().getFirstName());
    dateTime = new SimpleStringProperty(booking.getDateTimeOfBooking());
  }

  public IntegerProperty getId()
  {
    return id;
  }

  public StringProperty getDateTime()
  {
    return dateTime;
  }

  public StringProperty getParent()
  {
    return parent;
  }
}
