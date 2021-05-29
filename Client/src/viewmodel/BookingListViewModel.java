package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.*;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.util.Optional;

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
    model.addListener(this, "add", "remove");
  }

  public void reset()
  {
    message.set("");
    update();
    updateParentBookings();
  }

  public void resetLabel()
  {
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

  public boolean isBookingSelected()
  {
    if (selectedBooking.get() != null)
    {
      return true;
    }
    else
    {
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

  public void onCancelBooking(Label label)
  {

    if (selectedBooking.get() != null)
    {
      if (confirmation())
      {
        viewState.setSelectedBooking(selectedBooking.get().getId().get());
        Booking booking = model.getBookingList()
            .getBookingById(selectedBooking.get().getId().get());

        model.cancelBooking(booking);
        parentBookings.remove(selectedBooking.get());
        System.out
            .println("size: " + model.getBookingList().getNumberOfBookings());
        label.setTextFill(Color.web("green"));
        message.set("Booking successfully canceled");
      }
    }
    else
    {
      System.out.println(selectedBooking.getValue());
      viewState.removeSelectedBooking();
      label.setTextFill(Color.web("red"));
      message.set("Please select a booking");

    }
  }

  private boolean confirmation()
  {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    Optional<ButtonType> result = null;
    alert.setTitle("Confirm cancelling a booking");
    alert.setHeaderText(
        "Are you sure you want to cancel this booking? \n\n" + "Babysitter: "
            + selectedBooking.get().getBabysitter().getValue() + "\n" + "Date: "
            + selectedBooking.get().getDate().getValue() + "\n" + "Duration: "
            + selectedBooking.get().getStartTime().getValue() + " - "
            + selectedBooking.get().getEndTime().getValue());
    result = alert.showAndWait();

    return result.isPresent() && result.get() == ButtonType.OK;
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
    Platform.runLater(this::update);
  }

}
