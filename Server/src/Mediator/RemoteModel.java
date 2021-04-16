package Mediator;

import Model.Booking;
import utility.observer.subject.RemoteSubject;

import java.rmi.RemoteException;

public interface RemoteModel extends RemoteSubject<String, String>
{
  void addBooking(Booking booking) throws RemoteException;
}
