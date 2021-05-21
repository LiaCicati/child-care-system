package view;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import viewmodel.DoubleStringConverter;
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
  @FXML private RadioButton hasCertificate;
  @FXML private RadioButton doNotHaveCertificate;
  @FXML private Label errorLabel;
  @FXML ToggleGroup groupToggle;

  @Override protected void init()
  {
    hasCertificate.setToggleGroup(groupToggle);
    doNotHaveCertificate.setToggleGroup(groupToggle);
    viewModel = getViewModelFactory().getRegisterBabysitterViewModel();
    firstName.textProperty().bindBidirectional(viewModel.getFirstName());
    lastName.textProperty().bindBidirectional(viewModel.getLastName());
    username.textProperty().bindBidirectional(viewModel.getUsername());
    email.textProperty().bindBidirectional(viewModel.getEmail());
    password.textProperty().bindBidirectional(viewModel.getPassword());
    Bindings.bindBidirectional(age.valueProperty(), viewModel.getAge());
    Bindings.bindBidirectional(babysittingExperience.textProperty(),
        viewModel.getBabysittingExperience(), new DoubleStringConverter());
    Bindings.bindBidirectional(paymentPerHour.textProperty(),
        viewModel.getPaymentPerHour(), new DoubleStringConverter());
    motherTongue.textProperty().bindBidirectional(viewModel.getMotherTongue());
    hasCertificate.selectedProperty()
        .bindBidirectional(viewModel.getHasCertificate());
    errorLabel.textProperty().bind(viewModel.getError());
    reset();

  }

  @Override public void reset()
  {
    viewModel.reset();
  }

//  public void onRegister() throws RemoteException
//  {
//    if (viewModel.register())
//    {
//      onLogIn();
//    }

//    if (viewModel.registerWithRequiredData())
//    {
//      onLogIn();
//    }

  public void onLogIn()
  {
    getViewHandler().openView(View.LOGIN_VIEW);
  }

  public void onRegister(ActionEvent actionEvent) throws RemoteException {

   if (viewModel.register())
   {
      onLogIn();
    }
  }
}
