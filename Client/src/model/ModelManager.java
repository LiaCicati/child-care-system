package model;

import mediator.Client;
import mediator.RemoteModel;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.LocalListener;
import utility.observer.subject.PropertyChangeAction;
import utility.observer.subject.PropertyChangeProxy;

import java.rmi.RemoteException;

public class ModelManager implements Model, LocalListener<Account, Booking>
{
  private RemoteModel remoteModel;
  private PropertyChangeAction<Account, Booking> property;

  public ModelManager() throws RemoteException
  {
    try
    {
      this.remoteModel = new Client(this, "localhost");

    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    this.property = new PropertyChangeProxy<>(this);
    //    remoteModel.addListener(this);
  }

  @Override public void addBooking(Booking booking) throws RemoteException
  {
    remoteModel.addBooking(booking);
  }

  @Override public Account login(String email, String password)
      throws RemoteException
  {
    return remoteModel.login(email, password);
  }

  @Override public void registerBabysitter(String userName, String password,
      String email, String firstName, String lastName) throws RemoteException
  {
    remoteModel
        .registerBabysitter(userName, password, email, firstName, lastName);
  }

  @Override public void registerParent(String userName, String password,
      String email, String firstName, String lastName, boolean hasPets)
      throws RemoteException
  {
    remoteModel.registerParent(userName, password, email, firstName, lastName,
        hasPets);
  }

  @Override public void logout(Account user) throws RemoteException
  {
    remoteModel.logout(user);
  }

  @Override public AccountList getAccountList() throws RemoteException
  {
    return remoteModel.getAccountList();
  }

  @Override public AccountList getBabysitterList() throws RemoteException
  {
    return remoteModel.getBabysitterList();
  }

  @Override public AccountList getParentList() throws RemoteException
  {
    return remoteModel.getParentList();
  }

  @Override public boolean addListener(
      GeneralListener<Account, Booking> listener, String... propertyNames)
  {
    return property.addListener(listener, propertyNames);
  }

  @Override public boolean removeListener(
      GeneralListener<Account, Booking> listener, String... propertyNames)
  {
    return property.removeListener(listener, propertyNames);
  }

  @Override public void propertyChange(ObserverEvent<Account, Booking> event)
  {
    property.firePropertyChange(event);
  }
}
