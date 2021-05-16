package view;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import viewmodel.BabysitterProfileViewModel;
import viewmodel.DoubleStringConverter;
import viewmodel.StringIntegerConverter;

public class BabysitterProfileViewController extends ViewController
{
  @FXML private Label firstName;
  @FXML private Label lastName;
  @FXML private Label username;
  @FXML private Label email;
  @FXML private Label age;
  @FXML private Label paymentPerHour;
  @FXML private Label spokenLanguages;
  @FXML private Label experience;
  @FXML private Label firstAidCertificate;
  @FXML private Label greetingName;

  private BabysitterProfileViewModel viewModel;

  @Override protected void init()
  {
    viewModel = getViewModelFactory().getBabysitterProfileViewModel();
    greetingName.textProperty().bind(viewModel.getFirstName());
    firstName.textProperty().bind(viewModel.getFirstName());
    lastName.textProperty().bindBidirectional(viewModel.getLastName());
    username.textProperty().bindBidirectional(viewModel.getUsername());
    email.textProperty().bindBidirectional(viewModel.getEmail());
    //    password.textProperty().bindBidirectional(viewModel.getPassword());
    age.textProperty()
        .bindBidirectional(viewModel.getAge(), new StringIntegerConverter(0));
    Bindings.bindBidirectional(experience.textProperty(),
        viewModel.getBabysittingExperience(), new DoubleStringConverter());
    Bindings.bindBidirectional(paymentPerHour.textProperty(),
        viewModel.getPaymentPerHour(), new DoubleStringConverter());
    spokenLanguages.textProperty()
        .bindBidirectional(viewModel.getMotherTongue());

    firstAidCertificate.textProperty()
        .bindBidirectional(viewModel.getFirstAidCertificate());
    //
    //    errorLabel.textProperty().bind(viewModel.getError());
    reset();
  }

  @Override public void reset()
  {
    viewModel.reset();
  }

  public void onEdit()
  {
    getViewHandler().openView(View.EDIT_BABYSITTER_PROFILE_VIEW);
  }

  public void onHome()
  {

  }

  public void onLogOut()
  {
    viewModel.logout();
    getViewHandler().openView(View.ACCOUNT_TYPE_VIEW);
  }
}
