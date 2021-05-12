package model;

import utility.observer.subject.LocalSubject;

import java.rmi.RemoteException;

public interface LocalModel extends LocalSubject<String, String>
{
  void addBooking(Booking booking) throws RemoteException;
  boolean doesUserNameExist(String userName) throws RemoteException;
  boolean isPasswordCorrect(String userName, String password) throws RemoteException;

}
