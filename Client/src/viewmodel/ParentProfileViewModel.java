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
    notificationUpdate = 0;
    model.addListener(this, "status");

  }

  public void reset()
  {
    loadData();
    errorLabel.set("");
    changedStatus.set(String.valueOf(
        model.getNotifications(viewState.getParent())
            - getNotificationUpdate()));
  }

  public void logout()
  {
    model.logout(viewState.getAccount());
    viewState.removeAccount();

  }

  private void loadData()
  {

    firstName.set(viewState.getParent().getFirstName());
    lastName.set(viewState.getParent().getLastName());
    username.set(viewState.getParent().getUserName());
    email.set(viewState.getParent().getEmail());

    if (viewState.getParent().hasPets())
    {
      hasPets.set("yes");
    }
    else
    {
      hasPets.set("no");
    }

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
      changedStatus.set(String.valueOf(
          model.getNotifications(viewState.getParent())
              - getNotificationUpdate()));
    });

  }
}
