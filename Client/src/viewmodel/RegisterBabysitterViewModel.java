package viewmodel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Babysitter;
import model.Booking;
import model.LocalModel;
import model.MyDateTime;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

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
  private ObjectProperty<Boolean> cprCertificate;
  private StringProperty errorLabel;

  LocalDate selectedBirthDate;
  String selectedBirthDateString;
  int selectedBirthDay;
  int selectedBirthMonth;
  int selectedBirthYear;
  MyDateTime selectedBirthDateMyDateTime;

  public RegisterBabysitterViewModel(LocalModel model)
  {
    this.model = model;
    firstName = new SimpleStringProperty("");
    lastName = new SimpleStringProperty("");
    username = new SimpleStringProperty("");
    email = new SimpleStringProperty("");
    password = new SimpleStringProperty("");
    age = new SimpleObjectProperty<>();
    hasCertificate = new SimpleObjectProperty<>();
    babysittingExperience = new SimpleDoubleProperty();
    paymentPerHour = new SimpleDoubleProperty();
    motherTongue = new SimpleStringProperty();
    errorLabel = new SimpleStringProperty("");
    cprCertificate = new SimpleObjectProperty<>();
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
  //  hasCertificate.set(true);
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

  /*public boolean registerWithRequiredData() throws RemoteException
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
  }*/

  public boolean register() throws RemoteException
  {
    try {
      selectedBirthDate = getAge().get();
      setCprCertificate(hasCertificate);
      if (motherTongue.get()==null){
        errorLabel.set("føz");
      }
//       else if (hasCertificate.get()==null){
//        errorLabel.set("cpr");}
        else{
        System.out.println(selectedBirthDate);
        selectedBirthDateString = String.valueOf(selectedBirthDate);

        selectedBirthDay = Integer.parseInt(selectedBirthDateString.substring(0, 4));
        selectedBirthMonth = Integer.parseInt(selectedBirthDateString.substring(5, 7));
        selectedBirthYear = Integer.parseInt(selectedBirthDateString.substring(8, 10));

        selectedBirthDateMyDateTime = new MyDateTime(selectedBirthDay, selectedBirthMonth, selectedBirthYear);

        model.registerBabysitter(firstName.get(), lastName.getValue(), username.get(), email.get(), password.get(),
                selectedBirthDateMyDateTime, babysittingExperience.get(), paymentPerHour.get(),
                motherTongue.get(),
                cprCertificate.get());
        errorLabel.set("");





        ObservableList<BookingBabysitterTableRowData> babysitters;
        babysitters = FXCollections.observableArrayList();
        for (int i = 0; i < model.getBabysitterList().getNumberOfAccounts(); i++) {

            babysitters.add(new BookingBabysitterTableRowData(
                    model.getBabysitterList().getAllBabysitterAccounts().get(i)));
          }
        System.out.println(babysitters);



        return true;
            }
return false;
    }
    catch (Exception e)
    {
      System.out.println("exception: "+e.getMessage());
      errorLabel.set("exception: "+e.getMessage());
      return false;
    }
  }

  public ObjectProperty<Boolean> getCprCertificate() {
    return cprCertificate;
  }

  public void setCprCertificate(ObjectProperty<Boolean> cprCertificate) {
    if (getHasCertificate().get()==null){
      this.cprCertificate.set(false);
    } else {
      this.cprCertificate.set(true);
    }
  }
}