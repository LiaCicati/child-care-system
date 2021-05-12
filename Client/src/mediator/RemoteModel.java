package mediator;

import model.Booking;
import utility.observer.subject.RemoteSubject;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public interface RemoteModel extends RemoteSubject<String, String>
{

 void addBooking(Booking booking) throws RemoteException;
 boolean isPasswordCorrect(String userName, String password) throws RemoteException;

/*  void connect() throws RemoteException, MalformedURLException, NotBoundException;
  void addBooking(Booking booking) throws RemoteException;
  boolean doesUserNameExist(String userName) throws RemoteException;
  boolean isPasswordCorrect(String userName, String password) throws RemoteException;*/
}
