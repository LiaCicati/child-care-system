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
    hasPets.set(null);
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

  public boolean registerWithRequiredData() throws RemoteException
  {

    try
    {
      model.registerParent(username.get(), password.get(), email.get(),
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
public boolean hasPets()
{
  return hasPets.getValue();
}
  public boolean register() throws RemoteException
  {
    try
    {
      model.registerParent(username.get(), password.get(), email.get(),
          firstName.get(), lastName.getValue(), hasPets());
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
