package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import viewmodel.BookingListViewModel;
import viewmodel.BookingViewModel;

public class ParentBookingsViewController extends ViewController
{
  private BookingListViewModel viewModel;
  @FXML private TableView<BookingViewModel> bookingsTable;
  @FXML private TableColumn<BookingViewModel, String> babysitterColumn;
  @FXML private TableColumn<BookingViewModel, String> dateColumn;
  @FXML private TableColumn<BookingViewModel, String> startTimeColumn;
  @FXML private TableColumn<BookingViewModel, String> endTimeColumn;
  @FXML private TableColumn<BookingViewModel, String> statusColumn;

  @Override protected void init()
  {
    viewModel = getViewModelFactory().getBookingListViewModel();

    dateColumn.setCellValueFactory(cellData -> cellData.getValue().getDate());
    startTimeColumn
        .setCellValueFactory(cellData -> cellData.getValue().getStartTime());
    endTimeColumn
        .setCellValueFactory(cellData -> cellData.getValue().getEndTime());
    babysitterColumn
        .setCellValueFactory(cellData -> cellData.getValue().getBabysitter());
    statusColumn.setCellValueFactory(cellData -> cellData.getValue().getStatus());
    bookingsTable.setItems(viewModel.getParentBookings());
    reset();
  }

  @Override public void reset()
  {
viewModel.reset();
  }

  public void onProfile()
  {
    getViewHandler().openView(View.PARENT_PROFILE_VIEW);
  }

  public void onBookBabysitter()
  {
    getViewHandler().openView(View.BOOKING_BABYSITTERS_VIEW);
  }

  public void onCancelBooking()
  {
  }
}
