package mediator;

import model.Account;
import model.AccountList;
import model.Booking;
import model.MyDateTime;
import utility.observer.subject.LocalSubject;

import java.rmi.RemoteException;
import java.time.LocalDate;

public interface ClientModel extends LocalSubject<String, String> {
    void addBooking(Booking booking);

    boolean isPasswordCorrect(String userName, String password);
void close();
    Account login(String username, String password);
    void registerBabysitter(String userName, String password, String email,
        String firstName, String lastName, LocalDate dateBirth, double paymentPerHour, String mainLanguage,
        double babysittingExperience, boolean hasFirstAidCertificate);
    void registerBabysitter(String userName, String password, String email,
        String firstName, String lastName);
    AccountList getAccountList();
    AccountList getBabysitterList();
}
