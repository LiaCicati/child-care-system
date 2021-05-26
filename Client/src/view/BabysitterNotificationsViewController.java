package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
  @FXML private Label messageLabel;

  @Override protected void init()
  {
    viewModel = getViewModelFactory().getBookingListViewModel();
    messageLabel.textProperty().bind(viewModel.getMessage());
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
        (obs, oldValue, newValue) -> {
          viewModel.setSelectedBooking(newValue);
          if (newValue != null) {
            viewModel.resetLabel();
          }
        });

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
   viewModel.onAccept(messageLabel);

  }

  public void onReject()
  {
    viewModel.onReject(messageLabel);
  }

  public void onDetails()
  {
    System.out.println(viewModel.onDetails(messageLabel));
    if(viewModel.onDetails(messageLabel))
    {
      getViewHandler().openView(View.BABYSITTER_BOOKING_DETAILS_VIEW);
    }
  }
}
