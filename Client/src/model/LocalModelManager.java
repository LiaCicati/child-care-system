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
import java.util.ArrayList;

public class LocalModelManager
    implements LocalModel, LocalListener<Account, Booking>
{
  private ClientModel serverModel;
  private PropertyChangeAction<Account, Booking> property;

  public LocalModelManager()
  {
    try
    {
      this.serverModel = new Client(this);
      serverModel.addListener(this, "add");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    this.property = new PropertyChangeProxy<>(this, true);

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
            property.close();
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

  @Override public void registerBabysitter(String userName, String password,
      String email, String firstName, String lastName, MyDateTime birthday,
      double paymentPerHour, String mainLanguage, double babysittingExperience,
      boolean hasFirstAidCertificate)
  {
    serverModel
        .registerBabysitter(userName, password, email, firstName, lastName,
            birthday, paymentPerHour, mainLanguage, babysittingExperience,
            hasFirstAidCertificate);
  }

  @Override public void registerBabysitter(String userName, String password,
      String email, String firstName, String lastName)
  {
    serverModel
        .registerBabysitter(userName, password, email, firstName, lastName);
  }

  @Override public void registerParent(String userName, String password,
      String email, String firstName, String lastName)
  {
    serverModel.registerParent(userName, password, email, firstName, lastName);
  }

  @Override public void registerParent(String userName, String password,
      String email, String firstName, String lastName, boolean hasPets)
  {
    serverModel.registerParent(userName, password, email, firstName, lastName,
        hasPets);
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
  public BookingList getBookingList()
  {
    return serverModel.getBookingList();
  }

  @Override public ArrayList<Kid> getKidList()
  {
    return serverModel.getKidList();
  }

  @Override public void editKidData(Parent parent, int id, Kid kid)
  {
    serverModel.editKidData(parent, id, kid);
  }

  @Override public ArrayList<Kid> getAllKids(Parent parent)
  {
    return serverModel.getAllKids(parent);
  }
  

  @Override public void addKid(Parent parent, Kid kid)
  {
    serverModel.addKid(parent, kid);
  }

  @Override public Kid getKidById(int id)
  {
    return serverModel.getKidById(id);
  }

  @Override public Kid getKid(int index)
  {
    return serverModel.getKid(index);

  }

  @Override public ArrayList<Booking> getAllBookings(Babysitter babysitter)
  {
    return serverModel.getAllBookings(babysitter);
  }

  @Override public boolean addListener(GeneralListener<Account, Booking> listener,
      String... propertyNames)
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

    property.firePropertyChange("add", event.getValue1(), event.getValue2());
  }
}
