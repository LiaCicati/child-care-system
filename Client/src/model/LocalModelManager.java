package model;

import mediator.Client;
import mediator.ClientModel;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.LocalListener;
import utility.observer.subject.PropertyChangeAction;
import utility.observer.subject.PropertyChangeProxy;

import java.rmi.RemoteException;
import java.time.LocalDate;

public class LocalModelManager implements LocalModel, LocalListener<String, String>
{
  private ClientModel serverModel;
  private PropertyChangeAction<String, String> property;

  public LocalModelManager()
  {
    try
    {
      this.serverModel = new Client(this);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    this.property = new PropertyChangeProxy<>(this);
  }

  @Override public void addBooking(Booking booking) throws RemoteException
  {
    serverModel.addBooking(booking);
  }

  @Override public boolean isPasswordCorrect(String userName, String password)
      throws RemoteException
  {
    return serverModel.isPasswordCorrect(userName, password);
  }

  @Override public void close()
  {
    try
    {
      serverModel.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  @Override public Account login(String username, String password)
  {
    return serverModel.login(username, password);
  }

  @Override public void registerBabysitter(String firstName, String lastName, String userName,String email,
                                           String password, MyDateTime birthday, double babysittingExperience,
                                           double paymentPerHour, String mainLanguage, boolean hasFirstAidCertificate)
  {
    serverModel.registerBabysitter(firstName, lastName,userName,email, password, birthday, babysittingExperience,
            paymentPerHour, mainLanguage, hasFirstAidCertificate);
  }

  /*@Override public void registerBabysitter(String userName, String password, String email, String firstName,
                                           String lastName)
  {
    serverModel.registerBabysitter(userName, password, email, firstName, lastName);
  }*/

//  @Override public void registerParent(String firstName, String lastName, String userName,String email, String password)
//  {
//    serverModel.registerParent( firstName,  lastName,  userName, email,  password);
//  }

  @Override public void registerParent(String firstName, String lastName, String userName,String email, String password, boolean hasPets)
  {
    serverModel.registerParent(firstName, lastName, userName, email, password, hasPets);
  }

  @Override public AccountList getParentList()
  {
    return serverModel.getParentList();
  }

  @Override public Parent getParent(String username)
  {
    return serverModel.getParent(username);
  }

  @Override public AccountList getAccountList()
  {
    return serverModel.getAccountList();
  }

  @Override public AccountList getBabysitterList()
  {
    return serverModel.getBabysitterList();
  }

  @Override public Babysitter getBabysitter(String username)
  {
    return serverModel.getBabysitter(username);
  }

  @Override public void logout(Account account)
  {
    serverModel.logout(account);
  }

  @Override
  public Parent getLoggedInParent() {
    return serverModel.getLoggedInParent();
  }

  @Override
  public BookingList getBookingList() {
    return serverModel.getBookingList();
  }

  @Override public boolean addListener(GeneralListener<String, String> listener, String... propertyNames)
  {
    return property.addListener(listener, propertyNames);
  }

  @Override public boolean removeListener(GeneralListener<String, String> listener, String... propertyNames)
  {
    return property.removeListener(listener, propertyNames);
  }

  @Override public void propertyChange(ObserverEvent<String, String> event)
  {
    property.firePropertyChange(event);
  }
}
