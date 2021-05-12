package mediator;

import model.Booking;

import utility.observer.subject.RemoteSubject;
import java.rmi.RemoteException;

public interface RemoteModelServer extends RemoteSubject<Booking, Booking>
{
    void addBooking(Booking booking) throws RemoteException;
    boolean doesUserNameExist(String userName) throws RemoteException;
    boolean isPasswordCorrect(String userName, String password) throws RemoteException;

}
