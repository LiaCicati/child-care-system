package mediator;

import model.*;
import utility.observer.subject.LocalSubject;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface ClientModel extends LocalSubject<Booking, Booking> {
    void addBooking(Booking booking);

    boolean isPasswordCorrect(String userName, String password);
void close();
    Account login(String username, String password);
    void registerBabysitter(String userName, String password, String email,
        String firstName, String lastName, LocalDate birthday, double paymentPerHour, String mainLanguage,
        double babysittingExperience, boolean hasFirstAidCertificate);
    void registerBabysitter(String userName, String password, String email,
        String firstName, String lastName);
    void registerParent(String userName, String password, String email,
        String firstName, String lastName);
    void registerParent(String userName, String password, String email,
        String firstName, String lastName, boolean hasPets);
    AccountList getParentList();
    Parent getParent(String username);
    AccountList getAccountList();
    AccountList getBabysitterList();
    Babysitter getBabysitter(String username);
    void logout(Account account);

    public Parent getLoggedInParent();

    BookingList getBookingList();

    ArrayList<Kid> getKidList();
    void editKidData(Parent parent,  int id, Kid kid);
    ArrayList<Kid> getAllKids(Parent parent);
    void addKid(Parent parent, Kid kid);
    Kid getKidById(int id);
    Kid getKid(int index);
    ArrayList<Booking> getAllBookings(Babysitter babysitter);

}
