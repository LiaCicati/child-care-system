package mediator;

import model.*;
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
import java.time.LocalDate;
import java.util.ArrayList;

public class Client implements ClientModel, RemoteListener<Account, Booking>
{
  private static final String HOST = "localhost";
  private String host;
  private LocalModel localModel;
  private RemoteModel remoteModel;
  private PropertyChangeAction<Account, Booking> property;

  public Client(LocalModel localModel, String host)
      throws RemoteException, NotBoundException, MalformedURLException
  {
    this.localModel = localModel;
    this.host = host;
    //    connect();
    this.remoteModel = (RemoteModel) Naming
        .lookup("rmi://" + host + ":1099/App");
    UnicastRemoteObject.exportObject(this, 0);
    this.remoteModel.addListener(this, "add");
    this.property = new PropertyChangeProxy<>(this, true);
  }

  public Client(LocalModel localModel)
      throws RemoteException, NotBoundException, MalformedURLException
  {
    this(localModel, HOST);
  }

/*   public void connect() throws RemoteException, MalformedURLException, NotBoundException {
    this.remoteModelClient = (RemoteModelClient) Naming
        .lookup("rmi://" + host + ":1099/App");
    UnicastRemoteObject.exportObject(this, 0);
  }*/

  @Override public void addBooking(Booking booking)
  {
    try
    {
      remoteModel.addBooking(booking);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public boolean isPasswordCorrect(String userName, String password)
  {
    try
    {
      return remoteModel.isPasswordCorrect(userName, password);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      return false;
    }
  }

  @Override public void close()
  {
    try
    {
      UnicastRemoteObject.unexportObject(this, true);
    }
    catch (Exception e)
    {
      throw new IllegalStateException("cannot unexport RMI object", e);
    }

  }

  @Override public Account login(String username, String password)
  {
    try
    {
      return remoteModel.login(username, password);
    }
    catch (RemoteException e)
    {
      throw new IllegalStateException(getExceptionMessage(e), e);
    }
  }

  @Override public void registerBabysitter(String firstName, String lastName,
      String userName, String email, String password, MyDateTime birthday,
      double babysittingExperience, double paymentPerHour, String mainLanguage,
      boolean hasFirstAidCertificate)

  {

    try
    {

      remoteModel
          .registerBabysitter(firstName, lastName, userName, email, password,
              birthday, babysittingExperience, paymentPerHour, mainLanguage,
              hasFirstAidCertificate);
    }
    catch (RemoteException e)
    {
      System.out.println(e.getMessage());
      throw new IllegalStateException(getExceptionMessage(e), e);
    }
  }

  /*@Override public void registerBabysitter(String userName, String password,
      String email, String firstName, String lastName)
  {
    try
    {
      remoteModel
          .registerBabysitter(userName, password, email, firstName, lastName);
    }
    catch (RemoteException e)
    {
      throw new IllegalStateException(getExceptionMessage(e), e);
    }
  }
*/
  //  @Override public void registerParent(String firstName, String lastName, String userName,String email, String password)
  //  {
  //    try
  //    {
  //      remoteModel
  //          .registerParent( firstName,  lastName,  userName, email,  password);
  //    }
  //    catch (RemoteException e)
  //    {
  //      throw new IllegalStateException(getExceptionMessage(e), e);
  //    }
  //  }

  @Override public void registerParent(String firstName, String lastName,
      String userName, String email, String password, boolean hasPets)
  {
    try
    {
      remoteModel.registerParent(firstName, lastName, userName, email, password,
          hasPets);
    }
    catch (RemoteException e)
    {
      throw new IllegalStateException(getExceptionMessage(e), e);
    }
  }

  @Override public AccountList getParentList()
  {
    try
    {
      return remoteModel.getParentList();
    }
    catch (RemoteException e)
    {
      throw new IllegalStateException(getExceptionMessage(e), e);
    }
  }

  @Override public Parent getParent(String username)
  {
    try
    {
      return remoteModel.getParent(username);
    }
    catch (RemoteException e)
    {
      throw new IllegalStateException(getExceptionMessage(e), e);
    }
  }

  @Override public AccountList getAccountList()
  {
    try
    {
      return remoteModel.getAccountList();
    }
    catch (RemoteException e)
    {
      throw new IllegalStateException(getExceptionMessage(e), e);
    }
  }

  @Override public AccountList getBabysitterList()
  {
    try
    {
      return remoteModel.getBabysitterList();
    }
    catch (RemoteException e)
    {
      throw new IllegalStateException(getExceptionMessage(e), e);
    }
  }

  @Override public Babysitter getBabysitter(String username)
  {
    try
    {
      return remoteModel.getBabysitter(username);
    }
    catch (RemoteException e)
    {
      throw new IllegalStateException(getExceptionMessage(e), e);
    }
  }

  @Override public void logout(Account account)
  {
    try
    {
      remoteModel.logout(account);
    }
    catch (RemoteException e)
    {
      throw new IllegalStateException(getExceptionMessage(e), e);
    }
  }

  @Override public Parent getLoggedInParent()
  {
    return remoteModel.getLoggedInParent();
  }

  @Override public BookingList getBookingList()
  {
    try
    {
      return remoteModel.getBookingList();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      throw new IllegalStateException(getExceptionMessage(e), e);
    }
  }

  @Override public ArrayList<Kid> getKidList()
  {
    try
    {
      return remoteModel.getKidList();
    }
    catch (RemoteException e)
    {
      throw new IllegalStateException(getExceptionMessage(e), e);
    }
  }

  @Override public void editKidData(Parent parent, int id, Kid kid)
  {
    try
    {
      remoteModel.editKidData(parent, id, kid);
    }
    catch (RemoteException e)
    {
      throw new IllegalStateException(getExceptionMessage(e), e);
    }
  }

  @Override public ArrayList<Kid> getAllKids(Parent parent)
  {
    try
    {
      return remoteModel.getAllKids(parent);
    }
    catch (RemoteException e)
    {
      throw new IllegalStateException(getExceptionMessage(e), e);
    }
  }

  @Override public void addKid(Parent parent, Kid kid)
  {
    try
    {
      remoteModel.addKid(parent, kid);
    }
    catch (RemoteException e)
    {
      throw new IllegalStateException(getExceptionMessage(e), e);
    }
  }

  @Override public Kid getKidById(int id)
  {
    try
    {
      return remoteModel.getKidById(id);
    }
    catch (RemoteException e)
    {
      throw new IllegalStateException(getExceptionMessage(e), e);
    }
  }

  @Override public Kid getKid(int index)
  {
    try
    {
      return remoteModel.getKid(index);
    }
    catch (RemoteException e)
    {
      throw new IllegalStateException(getExceptionMessage(e), e);

    }
  }

  @Override public ArrayList<Booking> getAllBookings(Babysitter babysitter)
  {
    try
    {
      return remoteModel.getAllBookings(babysitter);
    }
    catch (RemoteException e)
    {
      throw new IllegalStateException(getExceptionMessage(e), e);

    }
  }

  @Override public ArrayList<Booking> getAllBookings(Parent parent)
  {
    try
    {
      return remoteModel.getAllBookings(parent);
    }
    catch (RemoteException e)
    {
      throw new IllegalStateException(getExceptionMessage(e), e);

    }
  }

  @Override public Booking getBookingById(int id)
  {
    try
    {
      return remoteModel.getBookingById(id);
    }
    catch (RemoteException e)
    {
      throw new IllegalStateException(getExceptionMessage(e), e);

    }
  }

  @Override public void changeStatus(int id, String status)
  {
    try
    {
       remoteModel.changeStatus(id, status);
    }
    catch (RemoteException e)
    {
      throw new IllegalStateException(getExceptionMessage(e), e);

    }
  }

  @Override public int getNotifications(Parent parent)
  {
    try
    {
      return remoteModel.getNotifications(parent);
    }
    catch (RemoteException e)
    {
      throw new IllegalStateException(getExceptionMessage(e), e);

    }
  }

  @Override public int getBabysitterPendingBookings(Babysitter babysitter)
  {
    try
    {
      return remoteModel.getBabysitterPendingBookings(babysitter);
    }
    catch (RemoteException e)
    {
      throw new IllegalStateException(getExceptionMessage(e), e);

    }
  }

  @Override public void propertyChange(ObserverEvent<Account, Booking> event)
      throws RemoteException
  {
    property.firePropertyChange(event);
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

  private String getExceptionMessage(Exception e)
  {
    String message = e.getMessage();
    if (message != null)
      message = message.split(";")[0];
    return message;
  }
}

