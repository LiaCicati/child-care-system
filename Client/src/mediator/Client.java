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

public class Client implements ClientModel, RemoteListener<String, String>
{
  private static final String HOST = "localhost";
  private String host;
  private LocalModel localModel;
  private RemoteModel remoteModel;
  private PropertyChangeAction<String, String> property;

  public Client(LocalModel localModel, String host)
      throws RemoteException, NotBoundException, MalformedURLException
  {
    this.localModel = localModel;
    this.host = host;
    //    connect();
    this.remoteModel = (RemoteModel) Naming
        .lookup("rmi://" + host + ":1099/App");
    UnicastRemoteObject.exportObject(this, 0);
    this.property = new PropertyChangeProxy<>(this);
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

  @Override public void registerBabysitter(String userName, String password, String email,
      String firstName, String lastName, LocalDate dateBirth, double paymentPerHour, String mainLanguage,
      double babysittingExperience, boolean hasFirstAidCertificate)
  {
    try
    {

      remoteModel
          .registerBabysitter(userName, password, email, firstName, lastName,
              dateBirth, paymentPerHour, mainLanguage,
              babysittingExperience, hasFirstAidCertificate);
    }
    catch (RemoteException e)
    {
      throw new IllegalStateException(getExceptionMessage(e), e);
    }
  }

  @Override public void registerBabysitter(String userName, String password,
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

  @Override public void propertyChange(ObserverEvent<String, String> event)
      throws RemoteException
  {
    property.firePropertyChange(event);
  }

  @Override public boolean addListener(GeneralListener<String, String> listener,
      String... propertyNames)
  {
    return property.addListener(listener, propertyNames);
  }

  @Override public boolean removeListener(
      GeneralListener<String, String> listener, String... propertyNames)
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
