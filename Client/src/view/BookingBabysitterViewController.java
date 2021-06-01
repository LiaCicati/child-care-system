package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import viewmodel.BabysitterProfileViewModel;
import viewmodel.BookingBabysitterTableRowData;
import viewmodel.BookingBabysitterViewModel;

import java.rmi.RemoteException;
import java.time.LocalDate;

public class BookingBabysitterViewController extends ViewController
{

  @FXML private ComboBox durationHoursComboBox;
  @FXML private ComboBox durationMinutesComboBox;
  @FXML private ComboBox minuteComboBox;
  @FXML private ComboBox hourComboBox;
  @FXML private DatePicker bookingDatePicker;
  @FXML private Label errorLabel;
  @FXML private TableView<BookingBabysitterTableRowData> babysittersTable;
  @FXML private TableColumn<BookingBabysitterTableRowData, String> babysitterNameColumn;
  @FXML private TableColumn<BookingBabysitterTableRowData, Number> babysitterAgeColumn;
  @FXML private TableColumn<BookingBabysitterTableRowData, Number> babysitterExperienceColumn;
  @FXML private TableColumn<BookingBabysitterTableRowData, Number> babysitterPaymentColumn;
  @FXML private TextArea description;

  private BookingBabysitterViewModel viewModel;

  @Override protected void init()
  {

    viewModel = getViewModelFactory().getBookingBabysitterViewModel();
    reset();
    bookingDatePicker.valueProperty().bindBidirectional(viewModel.getDate());
    hourComboBox.valueProperty().bindBidirectional(viewModel.getHour());
    minuteComboBox.valueProperty().bindBidirectional(viewModel.getMinute());
    durationHoursComboBox.valueProperty()
        .bindBidirectional(viewModel.getDurationHours());
    durationMinutesComboBox.valueProperty()
        .bindBidirectional(viewModel.getDurationMinutes());
    description.textProperty().bindBidirectional(viewModel.getDescription());
    errorLabel.textProperty().bind(viewModel.getError());

    babysitterNameColumn.setCellValueFactory(d -> d.getValue().getName());
    babysitterAgeColumn.setCellValueFactory(d -> d.getValue().getAge());
    babysitterExperienceColumn
        .setCellValueFactory(d -> d.getValue().getBabysittingExperience());
    babysitterPaymentColumn
        .setCellValueFactory(d -> d.getValue().getPaymentPerHour());

    babysittersTable.setItems(viewModel.getBabysitters());
    reset();
  }

  @Override public void reset()
  {
    bookingDatePicker.setValue(null);
    durationHoursComboBox.setValue(null);
    durationMinutesComboBox.setValue(null);
    minuteComboBox.setValue(null);
    hourComboBox.setValue(null);
    bookingDatePicker.setValue(null);
    viewModel.reset();
  }

  public void onProfile()
  {
    reset();
    getViewHandler().openView(View.PARENT_PROFILE_VIEW);
  }

  public void bookBabysitter()
  {
    String chosenBabysitter = String
        .valueOf(babysittersTable.getSelectionModel().getSelectedItem());
    viewModel.createBooking(chosenBabysitter, errorLabel);
  }

  public void onDateEntered() throws RemoteException
  {
    viewModel.resetLabel(errorLabel);
    viewModel.date();
    viewModel.getBookedBabysitters();
  }

  public void onHourEntered(ActionEvent actionEvent) throws RemoteException
  {
    viewModel.resetLabel(errorLabel);
    viewModel.hour();
    viewModel.getBookedBabysitters();
  }

  public void onMinuteEntered(ActionEvent actionEvent) throws RemoteException
  {
    viewModel.resetLabel(errorLabel);
    viewModel.minute();
    viewModel.getBookedBabysitters();
  }

  public void onDurationHourEntered(ActionEvent actionEvent)
      throws RemoteException
  {
    viewModel.resetLabel(errorLabel);
    viewModel.durationHour();
    viewModel.getBookedBabysitters();
  }

  public void onDurationMinutesEntered(ActionEvent actionEvent)
      throws RemoteException
  {
    viewModel.resetLabel(errorLabel);
    viewModel.durationMinute();
    viewModel.getBookedBabysitters();
  }

  public void onMyBookings()
  {
    reset();
    getViewHandler().openView(View.PARENT_BOOKINGS_VIEW);
  }
}
