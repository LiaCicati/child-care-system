package mediator;

import model.Account;
import model.AccountList;
import model.Booking;
import utility.observer.subject.RemoteSubject;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public interface RemoteModel extends RemoteSubject<Account, Booking>
{
  void connect()
      throws RemoteException, MalformedURLException, NotBoundException;
  void addBooking(Booking booking) throws RemoteException;
  Account login(String email, String password) throws RemoteException;
  void registerBabysitter(String userName, String password, String email,
      String firstName, String lastName) throws RemoteException;
  void registerParent(String userName, String password, String email,
      String firstName, String lastName, boolean hasPets)
      throws RemoteException;
  void logout(Account user) throws RemoteException;
  AccountList getAccountList() throws RemoteException;
  AccountList getBabysitterList() throws RemoteException;
  AccountList getParentList() throws RemoteException;
}
