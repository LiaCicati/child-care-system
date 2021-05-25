package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ParentBookingsViewController extends ViewController
{
  @FXML private TableView<?> bookingsTable;
  @FXML private TableColumn<?, ?> babysitterColumn;
  @FXML private TableColumn<?, ?> dateColumn;
  @FXML private TableColumn<?, ?> startTimeColumn;
  @FXML private TableColumn<?, ?> endTimeColumn;
  @FXML private TableColumn<?, ?> statusColumn;

  @Override protected void init()
  {

  }

  @Override public void reset()
  {

  }

  public void onProfile()
  {
    getViewHandler().openView(View.PARENT_PROFILE_VIEW);
  }

  public void onBookBabysitter()
  {
    getViewHandler().openView(View.BOOKING_BABYSITTERS_VIEW);
  }
}
