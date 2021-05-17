package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.LocalModel;
import view.View;

public class ParentProfileViewModel
{
  private LocalModel model;
  private ViewState viewState;
  private StringProperty firstName;
  private StringProperty lastName;
  private StringProperty username;
  private StringProperty email;
  private StringProperty password;
  private StringProperty hasPets;
  private StringProperty errorLabel;

  public ParentProfileViewModel(LocalModel model, ViewState viewState)
  {
    this.model = model;
    this.viewState = viewState;
    firstName = new SimpleStringProperty();
    lastName = new SimpleStringProperty();
    username = new SimpleStringProperty();
    email = new SimpleStringProperty();
    password = new SimpleStringProperty();
    hasPets = new SimpleStringProperty();
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
//    hasPets.set(viewState.getParent().hasPets() + "");
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

  public StringProperty getHasPets()
  {
    return hasPets;
  }

  public StringProperty getError()
  {
    return errorLabel;
  }
}
