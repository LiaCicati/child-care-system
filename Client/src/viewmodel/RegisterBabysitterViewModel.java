package viewmodel;

import javafx.beans.property.*;
import model.LocalModel;

import java.rmi.RemoteException;

public class RegisterBabysitterViewModel
{
  private LocalModel model;
  private StringProperty firstName;
  private StringProperty lastName;
  private StringProperty username;
  private StringProperty email;
  private StringProperty password;
  //  private IntegerProperty age;
  //  private DoubleProperty babysittingExperience;
  //  private DoubleProperty paymentPerHour;
  //  private StringProperty motherTongue;
  //  private BooleanProperty firstAidCertificate;
  private StringProperty errorLabel;

  public RegisterBabysitterViewModel(LocalModel model)
  {
    this.model = model;
    firstName = new SimpleStringProperty("");
    lastName = new SimpleStringProperty("");
    username = new SimpleStringProperty("");
    email = new SimpleStringProperty("");
    password = new SimpleStringProperty("");
    //    age = new SimpleIntegerProperty();
    //    babysittingExperience = new SimpleDoubleProperty();
    //    paymentPerHour = new SimpleDoubleProperty();
    //    motherTongue = new SimpleStringProperty();
    //    firstAidCertificate = new SimpleBooleanProperty();
    errorLabel = new SimpleStringProperty("");
  }

  public void reset()
  {
    firstName.set("");
    lastName.set("");
    username.set("");
    email.set("");
    password.set("");
    //    age.setValue(0);
    //    babysittingExperience.set(0);
    //    paymentPerHour.set(0);
    //    motherTongue.set("");
    //    firstAidCertificate.set(false);
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

  //  public IntegerProperty getAge()
  //  {
  //    return age;
  //  }
  //
  //  public DoubleProperty getBabysittingExperience()
  //  {
  //    return babysittingExperience;
  //  }
  //
  //  public DoubleProperty getPaymentPerHour()
  //  {
  //    return paymentPerHour;
  //  }
  //
  //  public StringProperty getMotherTongue()
  //  {
  //    return motherTongue;
  //  }
  //
  //  public BooleanProperty getFirstAidCertificate()
  //  {
  //    return firstAidCertificate;
  //  }

  public StringProperty getError()
  {
    return errorLabel;
  }

  public boolean register() throws RemoteException
  {
//    System.out.println("List: " + model.getAccountList());
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
}