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

  private BookingBabysitterViewModel viewModel;

  @Override protected void init()
  {
    viewModel = getViewModelFactory().getBookingBabysitterViewModel();

    bookingDatePicker.valueProperty().bindBidirectional(viewModel.getDate());
    hourComboBox.valueProperty().bindBidirectional(viewModel.getHour());
    minuteComboBox.valueProperty().bindBidirectional(viewModel.getMinute());
    durationHoursComboBox.valueProperty().bindBidirectional(viewModel.getDurationHours());
    durationMinutesComboBox.valueProperty().bindBidirectional(viewModel.getDurationMinutes());
    errorLabel.textProperty().bind(viewModel.getError());

    babysitterNameColumn.setCellValueFactory(d -> d.getValue().getName());
    babysitterAgeColumn.setCellValueFactory(d -> d.getValue().getAge());
    babysitterExperienceColumn.setCellValueFactory(d -> d.getValue().getBabysittingExperience());
    babysitterPaymentColumn.setCellValueFactory(d -> d.getValue().getPaymentPerHour());

    babysittersTable.setItems(viewModel.getBabysitters());
  }

  @Override public void reset()
  {

  }

  public void bookBabysitter() {

    String chosenBabysitter = String.valueOf(babysittersTable.getSelectionModel().getSelectedItem());
    System.out.println(chosenBabysitter);
    viewModel.createBooking(chosenBabysitter);
  }

  public void onDateEntered() throws RemoteException {
    viewModel.date();
  }
  public void onProfile()
  {
    getViewHandler().openView(View.PARENT_PROFILE_VIEW);
  }

  public void onHourEntered(ActionEvent actionEvent) throws RemoteException {
    viewModel.hour();


  }

  public void onMinuteEntered(ActionEvent actionEvent) throws RemoteException {
    viewModel.minute();

  }


  public void onDurationHourEntered(ActionEvent actionEvent) throws RemoteException {
  viewModel.durationHour();
  }

  public void onDurationMinutesEntered(ActionEvent actionEvent) throws RemoteException {
    viewModel.durationMinute();
  }
}
