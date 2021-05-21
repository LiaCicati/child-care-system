package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Booking;
import model.LocalModel;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;

public class BookingListViewModel implements LocalListener<Booking, Booking>
{
  private ObservableList<BookingViewModel> bookings;
  private ObjectProperty<BookingViewModel> selectedBooking;
  private LocalModel model;
  private ViewState viewState;
  private StringProperty error;

  public BookingListViewModel(LocalModel model, ViewState viewState)
  {
    this.model = model;
    this.viewState = viewState;
    this.bookings = FXCollections.observableArrayList();
    this.selectedBooking = new SimpleObjectProperty<>();
    this.error = new SimpleStringProperty();
    model.addListener(this, "add");
  }

  public void reset()
  {
    error.set("");
    update();
  }

  public void update()
  {
    bookings.clear();
    for (int i = 0;
         i < model.getAllBookings(viewState.getBabysitter()).size(); i++)
    {
      bookings.add(new BookingViewModel(
          model.getAllBookings(viewState.getBabysitter()).get(i)));
    }
  }

  public ObservableList<BookingViewModel> getBookings()
  {
    return bookings;
  }

  public StringProperty getError()
  {
    return error;
  }

  @Override public void propertyChange(ObserverEvent<Booking, Booking> event)
  {
    Platform.runLater(() ->
        bookings.add(new BookingViewModel(event.getValue2())));
  }
}
