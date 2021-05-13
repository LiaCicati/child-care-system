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

public class Server implements RemoteModel, LocalListener<Booking, Booking>
{
  private Model model;
  private PropertyChangeAction<Booking, Booking> property;

  public Server(Model model) throws RemoteException, MalformedURLException
  {
    this.property = new PropertyChangeProxy<>(this, true);
    this.model = model;
    //        this.model.addListener(this, "add");
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

  @Override public boolean isPasswordCorrect(String userName, String password)
      throws RemoteException
  {
    return model.isPasswordCorrect(userName, password);
  }

  @Override public Account login(String username, String password)
      throws RemoteException
  {
    return model.login(username, password);
  }

  @Override public void registerBabysitter(String userName, String password,
      String email, String firstName, String lastName, int birthDay,
      int birthMonth, int birthYear, double paymentPerHour, String mainLanguage,
      double babysittingExperience, boolean hasFirstAidCertificate)
      throws RemoteException
  {
    model.registerBabysitter(userName, password, email, firstName, lastName,
        birthDay, birthMonth, birthYear, paymentPerHour, mainLanguage,
        babysittingExperience, hasFirstAidCertificate);
  }

  @Override public void registerBabysitter(String userName, String password,
      String email, String firstName, String lastName) throws RemoteException
  {
    model.registerBabysitter(userName, password, email, firstName, lastName);
  }

  @Override public AccountList getAccountList() throws RemoteException
  {
    return model.getAccountList();
  }

  @Override public AccountList getBabysitterList() throws RemoteException
  {
    return model.getBabysitterList();
  }

  @Override public void propertyChange(ObserverEvent<Booking, Booking> event)
  {
    property
        .firePropertyChange(event.getPropertyName(), null, event.getValue2());
  }

  @Override public boolean addListener(
      GeneralListener<Booking, Booking> listener, String... propertyNames)
      throws RemoteException
  {
    boolean check = property.addListener(listener, propertyNames);
    return check;
  }

  @Override public boolean removeListener(
      GeneralListener<Booking, Booking> listener, String... propertyNames)
      throws RemoteException
  {
    return property.removeListener(listener, propertyNames);
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
}
