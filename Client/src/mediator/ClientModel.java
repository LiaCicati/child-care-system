package mediator;

import model.*;
import utility.observer.subject.LocalSubject;

import java.rmi.RemoteException;
import java.time.LocalDate;

public interface ClientModel extends LocalSubject<String, String> {
    void addBooking(Booking booking);

    boolean isPasswordCorrect(String userName, String password);
void close();
    Account login(String username, String password);
    void registerBabysitter(String firstName, String lastName, String userName,String email, String password,
                            MyDateTime birthday, double babysittingExperience,
                            double paymentPerHour, String mainLanguage,
                            boolean hasFirstAidCertificate);
//    void registerBabysitter(String userName, String password, String email,String firstName, String lastName);
//    void registerParent(String firstName, String lastName, String userName,String email, String password);
    void registerParent(String firstName, String lastName, String userName,String email, String password, boolean hasPets);
    AccountList getParentList();
    Parent getParent(String username);
    AccountList getAccountList();
    AccountList getBabysitterList();
    Babysitter getBabysitter(String username);
    void logout(Account account);
    public Parent getLoggedInParent();

    BookingList getBookingList();
}
