package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Account;
import model.Babysitter;
import model.LocalModel;

public class LoginViewModel
{
  private StringProperty username;
  private StringProperty password;
  private StringProperty error;
  private LocalModel model;

  public LoginViewModel(LocalModel model)
  {
    this.model = model;
    username = new SimpleStringProperty("");
    password = new SimpleStringProperty("");
    error = new SimpleStringProperty("");
  }

  public void reset()
  {
    error.setValue("");
    username.setValue("");
    password.setValue("");
  }

  public int login()
  {
    try
    {
      Account loggedIn = model.login(username.get(), password.get());
      if (loggedIn instanceof Babysitter)
      {
        return 2;
      }
      else // parent
      {
        return 1;
      }
    }
    catch (Exception e) //couldn't log in
    {
      error.set(e.getMessage());
      return 0;
    }
  }

  public StringProperty getUsername()
  {
    return username;
  }

  public StringProperty getPassword()
  {
    return password;
  }

  public StringProperty getError()
  {
    return error;
  }
}