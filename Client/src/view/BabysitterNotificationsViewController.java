package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import viewmodel.BookingListViewModel;
import viewmodel.BookingViewModel;

public class BabysitterNotificationsViewController extends ViewController
{
  private BookingListViewModel viewModel;
  @FXML private TableView<BookingViewModel> bookingsTable;
  @FXML private TableColumn<BookingViewModel, String> dateTimeColumn;
  @FXML private TableColumn<BookingViewModel, String> dateColumn;
  @FXML private TableColumn<BookingViewModel, String> startTimeColumn;
  @FXML private TableColumn<BookingViewModel, String> endTimeColumn;
  @FXML private TableColumn<BookingViewModel, String> parentColumn;
  @FXML private TableColumn<BookingViewModel, String> statusColumn;
  @FXML private Label errorLabel;
  @FXML private Label successMessage;

  @Override protected void init()
  {
    viewModel = getViewModelFactory().getBookingListViewModel();
    errorLabel.textProperty().bind(viewModel.getError());
    successMessage.textProperty().bind(viewModel.getSuccessMessage());
    dateTimeColumn
        .setCellValueFactory(cellData -> cellData.getValue().getDateTime());
    dateColumn.setCellValueFactory(cellData -> cellData.getValue().getDate());
    startTimeColumn
        .setCellValueFactory(cellData -> cellData.getValue().getStartTime());
    endTimeColumn
        .setCellValueFactory(cellData -> cellData.getValue().getEndTime());
    parentColumn
        .setCellValueFactory(cellData -> cellData.getValue().getParent());
    statusColumn.setCellValueFactory(cellData -> cellData.getValue().getStatus());
    bookingsTable.setItems(viewModel.getBookings());
    bookingsTable.getSelectionModel().selectedItemProperty().addListener(
        (obs, oldValue, newValue) -> viewModel.setSelectedBooking(newValue));
    reset();
  }

  @Override public void reset()
  {
    viewModel.reset();
  }

  public void onProfile()
  {
    getViewHandler().openView(View.BABYSITTER_PROFILE_VIEW);
  }

  public void onAccept()
  {
   viewModel.onAccept();

  }

  public void onReject()
  {
    viewModel.onReject();
  }

  public void onDetails()
  {
    System.out.println(viewModel.onDetails());
    if(viewModel.onDetails())
    {
      getViewHandler().openView(View.BABYSITTER_BOOKING_DETAILS_VIEW);
    }
  }
}
