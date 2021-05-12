package mediator;

import model.Account;
import model.AccountList;
import model.Booking;
import model.Model;

import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.LocalListener;
import utility.observer.subject.PropertyChangeAction;
import utility.observer.subject.PropertyChangeProxy;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RemoteModelManager
    implements RemoteModel, LocalListener<Account, Booking>
{
  private Model model;
  private PropertyChangeAction<Account, Booking> property;

  public RemoteModelManager(Model model)
      throws RemoteException, MalformedURLException
  {
    this.property = new PropertyChangeProxy<>(this, true);
    this.model = model;
//    model.addListener(this);
    startRegistry();
    startServer();
  }

  private void startServer() throws RemoteException, MalformedURLException
  {
    UnicastRemoteObject.exportObject(this, 0);
    Naming.rebind("App", this);
    System.out.println("Server started...");
  }

  public void close()
  {
    property.close();
    try
    {
      UnicastRemoteObject.unexportObject(this, true);
    }
    catch (Exception e)
    {
      //  Nothing?
    }
  }

  @Override public void addBooking(Booking booking) throws RemoteException
  {
    model.addBooking(booking);
    property.firePropertyChange("add", null, booking);
  }

  @Override public Account login(String email, String password)
      throws RemoteException
  {
    return model.login(email, password);
  }

  @Override public void registerBabysitter(String userName, String password,
      String email, String firstName, String lastName)
      throws RemoteException
  {
    model.registerBabysitter(userName, password, email, firstName, lastName);
  }

  @Override public void registerParent(String userName, String password,
      String email, String firstName, String lastName, boolean hasPets)
      throws RemoteException
  {
    model.registerParent(userName, password, email, firstName, lastName,
        hasPets);
  }

  @Override public void logout(Account user) throws RemoteException
  {
    model.logout(user);
  }

  @Override public AccountList getAccountList() throws RemoteException
  {
    return model.getAccountList();
  }

  @Override public AccountList getBabysitterList() throws RemoteException
  {
    return model.getBabysitterList();
  }

  @Override public AccountList getParentList() throws RemoteException
  {
    return model.getParentList();
  }

  private void startRegistry() throws RemoteException
  {
    try
    {
      Registry reg = LocateRegistry.createRegistry(1099);
      System.out.println("Registry started...");
    }
    catch (java.rmi.server.ExportException ex)
    {
      System.out.println("Registry already started?" + " " + ex.getMessage());
    }
  }

  @Override public void propertyChange(ObserverEvent<Account, Booking> event)
  {
    property.firePropertyChange(event.getPropertyName(), event.getValue1(),
        event.getValue2());
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
}
