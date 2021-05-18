package viewmodel;

import javafx.beans.property.*;
import javafx.collections.ObservableList;
import model.LocalModel;

public class BabysitterProfileViewModel
{
  private LocalModel model;
  private ViewState viewState;
  private StringProperty firstName;
  private StringProperty lastName;
  private StringProperty username;
  private StringProperty email;
  private StringProperty password;
  private IntegerProperty age;
  private DoubleProperty babysittingExperience;
  private DoubleProperty paymentPerHour;
  private StringProperty motherTongue;
  private StringProperty firstAidCertificate;
  private StringProperty errorLabel;
  private ObservableList<model.Babysitter> babysitters;

  public BabysitterProfileViewModel(LocalModel model, ViewState viewState)
  {
    this.model = model;
    this.viewState = viewState;
    firstName = new SimpleStringProperty();
    lastName = new SimpleStringProperty();
    username = new SimpleStringProperty();
    email = new SimpleStringProperty();
    password = new SimpleStringProperty();
    age = new SimpleIntegerProperty();
    babysittingExperience = new SimpleDoubleProperty();
    paymentPerHour = new SimpleDoubleProperty();
    motherTongue = new SimpleStringProperty();
    firstAidCertificate = new SimpleStringProperty();
    errorLabel = new SimpleStringProperty();
  }

  public void reset()
  {
    loadData();
    errorLabel.set("");
  }

  public void logout()
  {
    model.logout(viewState.getAccount());
    viewState.removeAccount();

  }

  private void loadData()
  {
    firstName.set(viewState.getAccount().getFirstName());
    lastName.set(viewState.getAccount().getLastName());
    username.set(viewState.getAccount().getUserName());
    email.set(viewState.getAccount().getEmail());
    age.setValue(viewState.getBabysitter().getAge());
    babysittingExperience
        .set(viewState.getBabysitter().getBabysittingExperience());
    paymentPerHour.set(viewState.getBabysitter().getPaymentPerHour());
    motherTongue.set(viewState.getBabysitter().getMainLanguage());
    firstAidCertificate.set(viewState.getBabysitter().hasFirstAidCertificate() + "");
  }

  public ObservableList<model.Babysitter> getAllBabysitters(){return babysitters;}

  public StringProperty getFirstName()
  {
    return firstName;
  }

  public StringProperty getLastName()
  {
    return lastName;
  }

  public StringProperty getUsername()
  {
    return username;
  }

  public StringProperty getEmail()
  {
    return email;
  }

  public StringProperty getPassword()
  {
    return password;
  }

  public IntegerProperty getAge()
  {
    return age;
  }

  public DoubleProperty getBabysittingExperience()
  {
    return babysittingExperience;
  }

  public DoubleProperty getPaymentPerHour()
  {
    return paymentPerHour;
  }

  public StringProperty getMotherTongue()
  {
    return motherTongue;
  }

  public StringProperty getFirstAidCertificate()
  {
    return firstAidCertificate;
  }

  public StringProperty getError()
  {
    return errorLabel;
  }
}
