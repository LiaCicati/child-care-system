package model;

import utility.observer.subject.LocalSubject;

import java.rmi.RemoteException;

public interface Model extends LocalSubject<Account, Booking>
{
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
