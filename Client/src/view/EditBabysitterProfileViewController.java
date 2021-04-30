package view;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EditBabysitterProfileViewController extends ViewController
{
  @FXML private TextField firstName;
  @FXML private TextField lastName;
  @FXML private TextField username;
  @FXML private DatePicker age;
  @FXML private TextField babysittingExperience;
  @FXML private TextField paymentPerHour;
  @FXML private TextField language1;
  @FXML private TextField language4;
  @FXML private TextField language2;
  @FXML private TextField language3;
  @FXML private ComboBox<String> firstAidCertificate;
  @FXML private Label errorLabel;

  @Override protected void init()
  {

  }

  @Override public void reset()
  {

  }

  public void onSave()
  {
    getViewHandler().openView(View.BABYSITTER_PROFILE_VIEW);
  }

  public void onCancel()
  {
    getViewHandler().openView(View.BABYSITTER_PROFILE_VIEW);
  }
}
