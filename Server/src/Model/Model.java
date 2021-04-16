package Model;

import utility.observer.subject.LocalSubject;

import java.rmi.RemoteException;

public interface Model extends LocalSubject<String, String>
{
  void addBooking(Booking booking) throws RemoteException;
}
