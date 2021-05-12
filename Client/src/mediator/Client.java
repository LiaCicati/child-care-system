package mediator;

import model.Account;
import model.AccountList;
import model.Booking;
import model.Model;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.RemoteListener;
import utility.observer.subject.PropertyChangeAction;
import utility.observer.subject.PropertyChangeProxy;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Client implements RemoteModel, RemoteListener<Account, Booking>
{
  private String host;
  private Model model;
  private RemoteModel remoteModel;
  private PropertyChangeAction<Account, Booking> property;

  public Client(Model model, String host)
      throws RemoteException, NotBoundException, MalformedURLException
  {
    this.model = model;
    this.host = host;
    connect();
    this.property = new PropertyChangeProxy<>(this);
    //    remoteModel.addListener(this);
  }

  @Override public void connect()
      throws RemoteException, MalformedURLException, NotBoundException
  {
    this.remoteModel = (RemoteModel) Naming
        .lookup("rmi://" + host + ":1099/App");
    UnicastRemoteObject.exportObject(this, 0);
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
      throws RemoteException
  {
    return property.addListener(listener, propertyNames);
  }

  @Override public boolean removeListener(
      GeneralListener<Account, Booking> listener, String... propertyNames)
      throws RemoteException
  {
    return property.removeListener(listener, propertyNames);
  }

  @Override public void propertyChange(ObserverEvent<Account, Booking> event)
      throws RemoteException
  {
    property.firePropertyChange(event);
  }
}
