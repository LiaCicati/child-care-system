package view;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class BabysitterNotificationsViewController extends ViewController
{
  @FXML
  private TableView<?> bookingsTable;

  @FXML
  private TableColumn<?, ?> dateColumn;

  @FXML
  private TableColumn<?, ?> timeColumn;

  @FXML
  private TableColumn<?, ?> parentColumn;

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


}
