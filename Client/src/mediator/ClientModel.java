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
    void registerBabysitter(String userName, String password, String email,
        String firstName, String lastName, LocalDate birthday, double paymentPerHour, String mainLanguage,
        double babysittingExperience, boolean hasFirstAidCertificate);
    void registerBabysitter(String userName, String password, String email,
        String firstName, String lastName);
    AccountList getAccountList();
    AccountList getBabysitterList();
    Babysitter getBabysitter(String username);
    void logout(Account account);
}
