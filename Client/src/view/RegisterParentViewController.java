package view;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterParentViewController extends ViewController
{
  @FXML private TextField firstName;
  @FXML private TextField lastName;
  @FXML private TextField username;
  @FXML private TextField email;
  @FXML private PasswordField password;
  @FXML private ComboBox<String> familyPets;
  @FXML private Label errorLabel;

  @Override protected void init()
  {

  }

  @Override public void reset()
  {

  }

  public void onRegister()
  {
    getViewHandler().openView(View.PARENT_PROFILE_VIEW);
  }

  public void onLogIn()
  {
    getViewHandler().openView(View.LOGIN_VIEW);
  }
}
