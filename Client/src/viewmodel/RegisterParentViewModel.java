package viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.LocalModel;

import java.rmi.RemoteException;

public class RegisterParentViewModel
{
  private LocalModel model;
  private StringProperty firstName;
  private StringProperty lastName;
  private StringProperty username;
  private StringProperty email;
  private StringProperty password;
  private ObjectProperty<Boolean> hasPets;
  private StringProperty errorLabel;

  public RegisterParentViewModel(LocalModel model)
  {
    this.model = model;
    firstName = new SimpleStringProperty("");
    lastName = new SimpleStringProperty("");
    username = new SimpleStringProperty("");
    email = new SimpleStringProperty("");
    password = new SimpleStringProperty("");
    hasPets = new SimpleObjectProperty<>();
    errorLabel = new SimpleStringProperty("");
  }

  public void reset()
  {
    firstName.set("");
    lastName.set("");
    username.set("");
    email.set("");
    password.set("");
    errorLabel.set("");
    hasPets.set(true);
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

  public ObjectProperty<Boolean> getHasPets()
  {
    return hasPets;
  }

  public StringProperty getError()
  {
    return errorLabel;
  }

  public boolean hasPets()
  {
    return hasPets.getValue();
  }

  /**
   * Registers a parent account
   * @return true if the registration was successful, false if not catching exceptions
   * and showing them as messages
   */
  public boolean register()
  {
    try
    {
      // calls the register method from the model
      model.registerParent(firstName.get(), lastName.get(), username.get(),
          email.get(), password.getValue(), hasPets());
      //if successful registration no error messages
      errorLabel.set("");
      return true;
    }
    catch (Exception e)
    {
      //catches exceptions and shows them
      System.out.println(e.getMessage());
      errorLabel.set(e.getMessage());
      return false;
    }
  }
}
