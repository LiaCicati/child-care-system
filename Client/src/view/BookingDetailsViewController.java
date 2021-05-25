package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class BookingDetailsViewController extends ViewController
{
  @FXML private Label bookedAt;
  @FXML private Label parent;
  @FXML private Label date;
  @FXML private Label startTime;
  @FXML private Label endTime;
  @FXML private TextArea description;

  @Override protected void init()
  {

  }

  @Override public void reset()
  {

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
