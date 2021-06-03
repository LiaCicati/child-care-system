package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import viewmodel.RegisterParentViewModel;

import java.rmi.RemoteException;

public class RegisterParentViewController extends ViewController
{
  private RegisterParentViewModel viewModel;
  @FXML private TextField firstName;
  @FXML private TextField lastName;
  @FXML private TextField username;
  @FXML private TextField email;
  @FXML private PasswordField password;
  @FXML private RadioButton hasPets;
  @FXML private RadioButton doesNotHavePets;
  @FXML private Label errorLabel;
  @FXML ToggleGroup groupToggle;

  @Override protected void init()
  {
    hasPets.setToggleGroup(groupToggle);
    doesNotHavePets.setToggleGroup(groupToggle);
    viewModel = getViewModelFactory().getRegisterParentViewModel();
    firstName.textProperty().bindBidirectional(viewModel.getFirstName());
    lastName.textProperty().bindBidirectional(viewModel.getLastName());
    username.textProperty().bindBidirectional(viewModel.getUsername());
    email.textProperty().bindBidirectional(viewModel.getEmail());
    password.textProperty().bindBidirectional(viewModel.getPassword());
    hasPets.selectedProperty().bindBidirectional(viewModel.getHasPets());
    errorLabel.textProperty().bind(viewModel.getError());
    reset();
  }

  @Override public void reset()
  {
    viewModel.reset();
  }

  /**
   * Opening the LogIn view if an account was successfully registered
   */
  public void onRegister()
  {
    if (viewModel.register())
    {
      onLogIn();
    }
  }

  /**
   * Opens the LogIn View
   */
  public void onLogIn()
  {
    getViewHandler().openView(View.LOGIN_VIEW);
  }

}
