package mediator;

import model.*;

import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.LocalListener;
import utility.observer.subject.PropertyChangeAction;
import utility.observer.subject.PropertyChangeHandler;
import utility.observer.subject.PropertyChangeProxy;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.ArrayList;

public class Server implements RemoteModel, LocalListener<Account, Booking>
{
  private Model model;
  private PropertyChangeHandler<Account, Booking> property;

  public Server(Model model) throws RemoteException, MalformedURLException
  {
    this.property = new PropertyChangeHandler<>(this, true);
    this.model = model;
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
//    property.close();
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
     property.firePropertyChange("add", booking.getBabysitter(), booking);
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


  @Override public void registerBabysitter(String firstName, String lastName, String userName,String email, String password,
                                           MyDateTime birthday, double babysittingExperience,
                                           double paymentPerHour, String mainLanguage,
                                           boolean hasFirstAidCertificate) throws RemoteException

  {
    model.registerBabysitter(firstName, lastName,userName,email, password,
            birthday,  babysittingExperience,paymentPerHour, mainLanguage,
            hasFirstAidCertificate);
  }

/*  @Override public void registerBabysitter(String userName, String password,
      String email, String firstName, String lastName) throws RemoteException
  {
    model.registerBabysitter(userName, password, email, firstName, lastName);
  }*/

//  @Override public void registerParent(String firstName, String lastName, String userName,String email, String password) throws RemoteException
//  {
//    model.registerParent( firstName,  lastName,  userName, email,  password);
//  }

  @Override public void registerParent(String firstName, String lastName, String userName,String email, String password, boolean hasPets)
      throws RemoteException
  {
    model.registerParent(firstName, lastName, userName, email, password, hasPets);
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

  @Override public Parent getLoggedInParent() throws RemoteException
  {
    return model.getLoggedInParent();
  }

  @Override public BookingList getBookingList() throws RemoteException
  {
    return model.getBookingList();
  }

    @Override public ArrayList<Kid> getKidList () throws RemoteException {
    return model.getKidList();
  }

    @Override public void editKidData (Parent parent,int id, Kid kid)
      throws RemoteException {
    model.editKidData(parent, id, kid);
  }

    @Override public ArrayList<Kid> getAllKids (Parent parent)
    {
      return model.getAllKids(parent);
    }

    @Override public void addKid (Parent parent, Kid kid) throws RemoteException
    {
      model.addKid(parent, kid);
    }

    @Override public Kid getKidById ( int id)
    {
      return model.getKidById(id);
    }

    @Override public Kid getKid ( int index) throws RemoteException {
    return model.getKid(index);

  }

  @Override public ArrayList<Booking> getAllBookings(Babysitter babysitter)
      throws RemoteException
  {
    return model.getAllBookings(babysitter);
  }

  @Override public void propertyChange (ObserverEvent <Account, Booking> event)
    {
      property.firePropertyChange(event.getPropertyName(), event.getValue1(), event.getValue2());
    }

    @Override public boolean addListener (GeneralListener <Account, Booking> listener, String...propertyNames)
      throws RemoteException {
    boolean check = property.addListener(listener, propertyNames);
    return check;
  }

    @Override public boolean removeListener
    (GeneralListener <Account, Booking> listener, String...propertyNames)
      throws RemoteException {
    return property.removeListener(listener, propertyNames);
  }

    private void startRegistry () throws RemoteException {
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

