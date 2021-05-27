package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Account;
import model.Booking;
import model.LocalModel;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;


import java.awt.*;

public class BookingListViewModel implements LocalListener<Account, Booking>
{
  private ObservableList<BookingViewModel> bookings;
  private ObservableList<BookingViewModel> parentBookings;
  private ObjectProperty<BookingViewModel> selectedBooking;
  private LocalModel model;
  private ViewState viewState;
  private StringProperty message;

  public BookingListViewModel(LocalModel model, ViewState viewState)
  {
    this.model = model;
    this.viewState = viewState;
    this.bookings = FXCollections.observableArrayList();
    this.parentBookings = FXCollections.observableArrayList();
    this.selectedBooking = new SimpleObjectProperty<>();
    this.message = new SimpleStringProperty();
    model.addListener(this, "add");
    model.addListener(this, "status");
  }

  public void reset()
  {
    message.set("");
    update();
    updateParentBookings();
  }

  public void resetLabel(){
    message.set("");
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

  public void updateParentBookings()
  {
    System.out.println(viewState.getParent());
    parentBookings.clear();
    for (int i = 0; i < model.getAllBookings(viewState.getParent()).size(); i++)
    {
      parentBookings.add(new BookingViewModel(
          model.getAllBookings(viewState.getParent()).get(i)));
    }
  }

  public ObservableList<BookingViewModel> getBookings()
  {
    return bookings;
  }

  public ObservableList<BookingViewModel> getParentBookings()
  {
    return parentBookings;
  }

  public boolean isBookingSelected(){
    if (selectedBooking.get() != null)
    {
      return true;
    }
    else {
      return false;
    }
  }

  public boolean onAccept(Label label)
  {
    if (selectedBooking.get() != null)
    {
      viewState.setSelectedBooking(selectedBooking.get().getId().get());
      model.changeStatus(viewState.getSelectedBooking(), Booking.ACCEPTED);
      label.setTextFill(Color.web("black"));
      message.set("Status changed to " + model
          .getBookingById(viewState.getSelectedBooking()).getStatus());
      update();
      //message.set("");

      return true;
    }
    else
    {
      viewState.removeSelectedBooking();
      label.setTextFill(Color.web("red"));
      message.set("Please select a booking first");
      return false;
    }

  }

  public void onReject(Label label)
  {
    if (selectedBooking.get() != null)
    {
      viewState.setSelectedBooking(selectedBooking.get().getId().get());
      model.changeStatus(viewState.getSelectedBooking(), Booking.REJECTED);
      label.setTextFill(Color.web("black"));
      message.set("Status changed to " + model
          .getBookingById(viewState.getSelectedBooking()).getStatus());

      update();
      //message.set("");
    }
    else
    {
      viewState.removeSelectedBooking();
      label.setTextFill(Color.web("red"));
      message.set("Please select a booking first");

    }

  }

  public boolean onDetails(Label label)
  {
    if (selectedBooking.get() != null)
    {
      viewState.setSelectedBooking(selectedBooking.get().getId().get());
      return true;
    }
    else
    {
      viewState.removeSelectedBooking();
      label.setTextFill(Color.web("red"));

      message.set("Please select a booking first");
      return false;
    }

  }

  public void setSelectedBooking(BookingViewModel selectedBooking)
  {
    this.selectedBooking.set(selectedBooking);
  }

  public StringProperty getError()
  {
    return message;
  }
  public StringProperty getMessage()
  {
    return message;
  }

  @Override public void propertyChange(ObserverEvent<Account, Booking> event)
  {
    Platform.runLater(() -> {
      if (event.getValue1().equals(viewState.getBabysitter()))
      {
        bookings.add(new BookingViewModel(event.getValue2()));
        parentBookings.add(new BookingViewModel(event.getValue2()));
      }
      if (event.getPropertyName().equals("status")){
        parentBookings.
      }
    });
  }

}
