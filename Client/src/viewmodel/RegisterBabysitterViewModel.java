package viewmodel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Babysitter;
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
  private ObjectProperty<Boolean> hasCertificate;
  private StringProperty errorLabel;


  int selectedBirthYear = 0;
  int selectedBirthMonth = 0;
  int selectedBirthDay = 0;
  String selectedBirthDateString;

  public RegisterBabysitterViewModel(LocalModel model)
  {
    this.model = model;
    firstName = new SimpleStringProperty();
    lastName = new SimpleStringProperty();
    username = new SimpleStringProperty();
    email = new SimpleStringProperty();
    password = new SimpleStringProperty();
    age = new SimpleObjectProperty<>();
    hasCertificate = new SimpleObjectProperty<>();
    babysittingExperience = new SimpleDoubleProperty();
    paymentPerHour = new SimpleDoubleProperty();
    motherTongue = new SimpleStringProperty();
    errorLabel = new SimpleStringProperty();
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
    errorLabel.set("");
    hasCertificate.set(true);
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

  public ObjectProperty<Boolean> getHasCertificate()
  {
    return hasCertificate;
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


  public boolean register()
  {
    try
    {
      errorLabel.set("All fields marked with * is mandatory to fill out.");
      System.out.println("hje");

      LocalDate selectedBirthDate = age.get();
      if (selectedBirthDateString==null) {
      }else {
        selectedBirthDateString = String.valueOf(selectedBirthDate);
        System.out.println(selectedBirthDateString);


        selectedBirthYear = Integer.parseInt(selectedBirthDateString.substring(0, 4));
        selectedBirthMonth = Integer.parseInt(selectedBirthDateString.substring(5, 7));
        selectedBirthDay = Integer.parseInt(selectedBirthDateString.substring(8, 10));
        System.out.println(selectedBirthDay +" "+ selectedBirthMonth +" "+ selectedBirthYear);
      }


      MyDateTime selectedBirthDateMyDateTime = new MyDateTime(selectedBirthDay, selectedBirthMonth, selectedBirthYear);

      model.registerBabysitter(username.get(), password.get(), email.get(),
          firstName.get(), lastName.getValue(), selectedBirthDateMyDateTime, paymentPerHour.get(),
          motherTongue.get(), babysittingExperience.get(),
          hasCertificate.get());
      errorLabel.set("");
      return true;
    }
    catch (Exception e)
    {
      errorLabel.set(e.getMessage());
      System.out.println("error");
      return false;
    }
  }
}