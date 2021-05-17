package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class BookingBabysitterViewController extends ViewController
{
  @FXML private Label errorLabel;
  @FXML private TableView<?> babysittersTable;
  @FXML private TableColumn<?, ?> babysitterNameColumn;
  @FXML private TableColumn<?, ?> babysitterAgeColumn;
  @FXML private TableColumn<?, ?> babysitterExperienceColumn;
  @FXML private TableColumn<?, ?> babysitterPaymentColumn;

  @Override protected void init()
  {

  }

  @Override public void reset()
  {

  }

  public void bookBabysitter()
  {
  }

  public void onDateEntered()
  {
  }


}
