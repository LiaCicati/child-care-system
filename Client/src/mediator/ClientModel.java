package mediator;

import model.Booking;
import utility.observer.subject.LocalSubject;

import java.rmi.RemoteException;

public interface ClientModel extends LocalSubject<String, String> {
    void addBooking(Booking booking);

    boolean isPasswordCorrect(String userName, String password);

}
