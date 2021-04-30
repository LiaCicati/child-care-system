package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginViewController extends ViewController
{
  @FXML private TextField email;
  @FXML private PasswordField password;
  @FXML private Label errorLabel;

  @Override protected void init()
  {

  }

  @Override public void reset()
  {

  }

  public void onParentLogIn()
  {

    getViewHandler().openView(View.PARENT_PROFILE_VIEW);
  }

  public void onRegister()
  {
    getViewHandler().openView(View.ACCOUNT_TYPE_VIEW);
  }

  public void onBabysitterLogIn()
  {
    getViewHandler().openView(View.BABYSITTER_PROFILE_VIEW);
  }
}
