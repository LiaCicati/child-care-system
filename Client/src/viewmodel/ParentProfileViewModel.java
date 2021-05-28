package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Account;
import model.Booking;
import model.Kid;
import model.LocalModel;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;
import view.View;

import java.time.LocalDate;
import java.util.ArrayList;

public class ParentProfileViewModel implements LocalListener<Account, Booking>
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
  private StringProperty changedStatus;
  private int notificationUpdate;

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
    changedStatus = new SimpleStringProperty();
    model.addListener(this, "add");
    notificationUpdate = 0;
  }

  public void reset()
  {
    loadData();
    errorLabel.set("");
    changedStatus.set(
        String.valueOf(model.getNotifications(viewState.getParent()) - getNotificationUpdate()));
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

    if (viewState.getParent().hasPets())
    {
      hasPets.set("yes");
    }
    else
    {
      hasPets.set("no");
    }

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

  public StringProperty getChangedStatus()
  {
    return changedStatus;
  }

  public int getNotificationUpdate()
  {
    return notificationUpdate;
  }

  public void updateNotifications()
  {
    notificationUpdate = model.getNotifications(viewState.getParent());
  }

  @Override public void propertyChange(ObserverEvent<Account, Booking> event)
  {

    Platform.runLater(() -> {
      if (event.getValue1().equals(viewState.getParent()))
      {
      }
    });
  }
}
