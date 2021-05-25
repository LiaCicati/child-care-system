package view;

import javafx.event.ActionEvent;
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
  @FXML private Label errorLabel;

  @Override protected void init()
  {
    viewModel = getViewModelFactory().getBookingListViewModel();
    errorLabel.textProperty().bind(viewModel.getError());
    dateTimeColumn
        .setCellValueFactory(cellData -> cellData.getValue().getDateTime());
    dateColumn.setCellValueFactory(cellData -> cellData.getValue().getDate());
    startTimeColumn
        .setCellValueFactory(cellData -> cellData.getValue().getStartTime());
    endTimeColumn
        .setCellValueFactory(cellData -> cellData.getValue().getEndTime());
    parentColumn
        .setCellValueFactory(cellData -> cellData.getValue().getParent());
    bookingsTable.setItems(viewModel.getBookings());
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
  }

  public void onReject()
  {
  }

  public void onDetails()
  {
  }
}
