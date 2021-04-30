package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class RegisterBabysitterViewController extends ViewController
{
  @FXML private TextField firstName;
  @FXML private TextField lastName;
  @FXML private TextField username;
  @FXML private TextField email;
  @FXML private PasswordField password;
  @FXML private DatePicker age;
  @FXML private TextField babysittingExperience;
  @FXML private TextField paymentPerHour;
  @FXML private TextField motherTongue;
  @FXML private ComboBox<String> firstAidCertificate;
  @FXML private Label errorLabel;

  @Override protected void init()
  {

  }

  @Override public void reset()
  {

  }

  public void onRegister()
  {
    getViewHandler().openView(View.BABYSITTER_PROFILE_VIEW);
  }

  public void onLogIn()
  {
    getViewHandler().openView(View.LOGIN_VIEW);
  }
}
