package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import viewmodel.RegisterBabysitterViewModel;

import java.rmi.RemoteException;

public class RegisterBabysitterViewController extends ViewController
{
  private RegisterBabysitterViewModel viewModel;
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
    viewModel = getViewModelFactory().getRegisterBabysitterViewModel();
    firstName.textProperty().bindBidirectional(viewModel.getFirstName());
    lastName.textProperty().bindBidirectional(viewModel.getLastName());
    username.textProperty().bindBidirectional(viewModel.getUsername());
    email.textProperty().bindBidirectional(viewModel.getEmail());
    password.textProperty().bindBidirectional(viewModel.getPassword());
    //age.valueProperty().bindBidirectional(((SimpleIntegerProperty) viewModel.getAge()));
    //babysittingExperience.textProperty().bindBidirectional(viewModel.getBabysittingExperience());
    //paymentPerHour.textProperty().bindBidirectional(viewModel.getPaymentPerHour());
    //    motherTongue.textProperty().bindBidirectional(viewModel.getMotherTongue());
    //firstAidCertificate.valueProperty().bindBidirectional(viewModel.getFirstAidCertificate());
    errorLabel.textProperty().bind(viewModel.getError());

  }

  @Override public void reset()
  {
viewModel.reset();
  }

  public void onRegister() throws RemoteException
  {
    if (viewModel.register())
    {
      onLogIn();
    }
  }

  public void onLogIn()
  {
    getViewHandler().openView(View.LOGIN_VIEW);
  }
}
