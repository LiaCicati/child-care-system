package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Booking;
import model.LocalModel;

public class BookingDetailsViewModel
{
  private LocalModel model;
  private ViewState viewState;
  private StringProperty bookedAt;
  private StringProperty parent;
  private StringProperty date;
  private StringProperty startTime;
  private StringProperty endTime;
  private StringProperty description;

  public BookingDetailsViewModel(LocalModel model, ViewState viewState)
  {
    this.model = model;
    this.viewState = viewState;
    this.bookedAt = new SimpleStringProperty();
    this.parent = new SimpleStringProperty();
    this.date = new SimpleStringProperty();
    this.startTime = new SimpleStringProperty();
    this.endTime = new SimpleStringProperty();
    this.description = new SimpleStringProperty();
  }

  public void reset()
  {
    loadData();
  }

  public void loadData()
  {
    Booking booking = model.getBookingById(viewState.getSelectedBooking());
    bookedAt.set(booking.getDateTimeOfBooking());
    parent.set(booking.getParent().getFirstName());
    date.set(booking.getTime().getStartTime().getSimpleDate());
    startTime.set(booking.getTime().getStartTime().getSimpleTime());
    endTime.set(booking.getTime().getEndTime().getSimpleTime());
    description.set(booking.getDescription());
  }

  public StringProperty getBookedAt()
  {
    return bookedAt;
  }

  public StringProperty getParent()
  {
    return parent;
  }

  public StringProperty getDate()
  {
    return date;
  }

  public StringProperty getStartTime()
  {
    return startTime;
  }

  public StringProperty getEndTime()
  {
    return endTime;
  }

  public StringProperty getDescription()
  {
    return description;
  }
}
