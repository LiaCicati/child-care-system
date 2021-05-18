package mediator;

import model.*;

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
import java.time.LocalDate;
import java.util.ArrayList;

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
      String email, String firstName, String lastName, LocalDate birthday,
      double paymentPerHour, String mainLanguage, double babysittingExperience,
      boolean hasFirstAidCertificate) throws RemoteException
  {
    model.registerBabysitter(userName, password, email, firstName, lastName,
        birthday, paymentPerHour, mainLanguage, babysittingExperience,
        hasFirstAidCertificate);
  }

  @Override public void registerBabysitter(String userName, String password,
      String email, String firstName, String lastName) throws RemoteException
  {
    model.registerBabysitter(userName, password, email, firstName, lastName);
  }

  @Override public void registerParent(String userName, String password,
      String email, String firstName, String lastName) throws RemoteException
  {
    model.registerParent(userName, password, email, firstName, lastName);
  }

  @Override public void registerParent(String userName, String password,
      String email, String firstName, String lastName, boolean hasPets)
      throws RemoteException
  {
    model.registerParent(userName, password, email, firstName, lastName,
        hasPets);
  }

  @Override public AccountList getParentList() throws RemoteException
  {
    return model.getParentList();
  }

  @Override public Parent getParent(String username)
  {
    return model.getParent(username);
  }

  @Override public AccountList getAccountList() throws RemoteException
  {
    return model.getAccountList();
  }

  @Override public AccountList getBabysitterList() throws RemoteException
  {
    return model.getBabysitterList();
  }

  @Override public Babysitter getBabysitter(String username)
      throws RemoteException
  {
    return model.getBabysitter(username);
  }

  @Override public void logout(Account account) throws RemoteException
  {
    model.logout(account);
  }

  @Override public ArrayList<Kid> getKidList() throws RemoteException
  {
    return model.getKidList();
  }

  @Override public void editKidData(String username, int id, Kid kid)
      throws RemoteException
  {
    model.editKidData(username, id, kid);
  }

  @Override public ArrayList<Kid> getAllKids(Parent parent)
  {
    return model.getAllKids(parent);
  }


  @Override public void addKid(Parent parent, Kid kid) throws RemoteException
  {
     model.addKid(parent, kid);
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
