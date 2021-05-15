package viewmodel;

import javafx.beans.property.*;
import model.LocalModel;
import model.MyDateTime;

import java.rmi.RemoteException;
import java.time.LocalDate;

public class RegisterBabysitterViewModel
{
  private LocalModel model;
  private StringProperty firstName;
  private StringProperty lastName;
  private StringProperty username;
  private StringProperty email;
  private StringProperty password;
  private ObjectProperty<LocalDate> age;
  private DoubleProperty babysittingExperience;
  private DoubleProperty paymentPerHour;
  private StringProperty motherTongue;
  private StringProperty firstAidCertificate;
  private StringProperty errorLabel;

  public RegisterBabysitterViewModel(LocalModel model)
  {
    this.model = model;
    firstName = new SimpleStringProperty("");
    lastName = new SimpleStringProperty("");
    username = new SimpleStringProperty("");
    email = new SimpleStringProperty("");
    password = new SimpleStringProperty("");
    age = new SimpleObjectProperty<>();
    babysittingExperience = new SimpleDoubleProperty();
    paymentPerHour = new SimpleDoubleProperty();
    motherTongue = new SimpleStringProperty();
    firstAidCertificate = new SimpleStringProperty("");
    errorLabel = new SimpleStringProperty("");
  }

  public void reset()
  {
    firstName.set("");
    lastName.set("");
    username.set("");
    email.set("");
    password.set("");
    age.set(null);
    babysittingExperience.set(0);
    paymentPerHour.set(0);
    motherTongue.set("");
    firstAidCertificate.set("");
    errorLabel.set("");
  }

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

  public ObjectProperty<LocalDate> getAge()
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

  public boolean registerWithRequiredData() throws RemoteException
  {

    try
    {
      model.registerBabysitter(username.get(), password.get(), email.get(),
          firstName.get(), lastName.get());
      errorLabel.set("");

      return true;
    }
    catch (Exception e)
    {
      errorLabel.set(e.getMessage());
      return false;
    }
  }

  public boolean register() throws RemoteException
  {
    try
    {
      model.registerBabysitter(username.get(), password.get(), email.get(),
          firstName.get(), lastName.getValue(), age.get(), paymentPerHour.get(),
          motherTongue.get(), babysittingExperience.get(),
          Boolean.parseBoolean(firstAidCertificate.get()));
      errorLabel.set("");
      System.out.println(model.getAccountList().getAllBabysitterAccounts().get(1).getLanguages());
      System.out.println(model.getAccountList().getAllBabysitterAccounts().get(1).hasFirstAidCertificate());



      return true;
    }
    catch (Exception e)
    {
      errorLabel.set(e.getMessage());
      return false;
    }
  }
}