package mediator;

import model.Booking;

import utility.observer.subject.RemoteSubject;
import java.rmi.RemoteException;

public interface RemoteModel extends RemoteSubject<Booking, Booking>
{
    void addBooking(Booking booking) throws RemoteException;
}
