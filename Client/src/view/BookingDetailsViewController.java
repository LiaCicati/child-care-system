package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import viewmodel.BookingDetailsViewModel;

public class BookingDetailsViewController extends ViewController
{
  private BookingDetailsViewModel viewModel;
  @FXML private Label bookedAt;
  @FXML private Label parent;
  @FXML private Label date;
  @FXML private Label startTime;
  @FXML private Label endTime;
  @FXML private TextArea description;

  @Override protected void init()
  {
    viewModel = getViewModelFactory().getBookingDetailsViewModel();
    bookedAt.textProperty().bindBidirectional(viewModel.getBookedAt());
    parent.textProperty().bindBidirectional(viewModel.getParent());
    date.textProperty().bindBidirectional(viewModel.getDate());
    startTime.textProperty().bindBidirectional(viewModel.getStartTime());
    endTime.textProperty().bindBidirectional(viewModel.getEndTime());
    description.textProperty().bindBidirectional(viewModel.getDescription());

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

  public void onNotifications()
  {
    getViewHandler().openView(View.BABYSITTER_NOTIFICATIONS_VIEW);
  }

  public void onBack()
  {
    getViewHandler().openView(View.BABYSITTER_NOTIFICATIONS_VIEW);
  }
}
