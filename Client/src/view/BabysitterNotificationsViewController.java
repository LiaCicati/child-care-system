package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class BabysitterNotificationsViewController extends ViewController
{
  @FXML
  private TableView<?> bookingsTable;
  @FXML private TableColumn<?, ?> idColumn;
  @FXML private TableColumn<?, ?> dateTimeColumn;
  @FXML private TableColumn<?, ?> parentColumn;
  @FXML private Label errorLabel;

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
