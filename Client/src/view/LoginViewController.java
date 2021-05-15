package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import viewmodel.LoginViewModel;

public class LoginViewController extends ViewController
{
  private LoginViewModel viewModel;
  @FXML private TextField userName;
  @FXML private PasswordField password;
  @FXML private Label errorLabel;

  @Override protected void init()
  {
    viewModel = getViewModelFactory().getLoginViewModel();
    userName.textProperty().bindBidirectional(viewModel.getUsername());
    password.textProperty().bindBidirectional(viewModel.getPassword());
    errorLabel.textProperty().bind(viewModel.getError());
  }

  @Override public void reset()
  {
    viewModel.reset();
  }

  public void onLogIn()
  {

    switch (viewModel.login())
    {
      case 0:
        break;
      case 1:
        getViewHandler().openView(View.PARENT_PROFILE_VIEW);
        break;
      case 2:
        getViewHandler().openView(View.BABYSITTER_PROFILE_VIEW);
        break;
    }
  }

  public void onRegister()
  {
    getViewHandler().openView(View.ACCOUNT_TYPE_VIEW);
  }

}