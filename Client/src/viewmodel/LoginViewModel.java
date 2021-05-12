package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Account;
import model.Babysitter;
import model.Model;

public class LoginViewModel
{
  private StringProperty email;
  private StringProperty password;
  private StringProperty error;
  private Model model;

  public LoginViewModel(Model model)
  {
    this.model = model;
    email = new SimpleStringProperty("");
    password = new SimpleStringProperty("");
    error = new SimpleStringProperty("");
  }

  public void reset()
  {
    error.setValue("");
    email.setValue("");
    password.setValue("");
  }

  public int login()
  {
    try
    {
      if (email.get().equals("") || password.get().equals(""))
      {
        throw new IllegalArgumentException("Please fill out the fields");
      }

      Account loggedIn = model.login(email.get(), password.get());
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

  public StringProperty getEmail()
  {
    return email;
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
