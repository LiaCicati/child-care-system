package mediator;

import model.*;
import utility.observer.subject.RemoteSubject;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface RemoteModel extends RemoteSubject<Account, Booking>
{

 void addBooking(Booking booking, Parent parent, Babysitter babysitter) throws RemoteException;

 Account login(String username, String password) throws RemoteException;
 void registerBabysitter(String firstName, String lastName, String userName,String email, String password,
     MyDateTime birthday, double babysittingExperience,
     double paymentPerHour, String mainLanguage,
     boolean hasFirstAidCertificate) throws RemoteException;
 //void registerBabysitter(String userName, String password, String email,String firstName, String lastName) throws RemoteException;
 // void registerParent(String firstName, String lastName, String userName,String email, String password)
 //         throws RemoteException;
 void registerParent(String firstName, String lastName, String userName,String email, String password, boolean hasPets) throws RemoteException;
 AccountList getParentList() throws RemoteException;
 Parent getParent(String username) throws RemoteException;
 AccountList getAccountList() throws RemoteException;
 AccountList getBabysitterList() throws RemoteException;
 Babysitter getBabysitter(String username) throws RemoteException;
 void logout(Account account) throws RemoteException;
 public Parent getLoggedInParent();

 BookingList getBookingList() throws RemoteException;

 ArrayList<Kid> getKidList() throws RemoteException;
 void editKidData(Parent parent,  int id, Kid kid) throws RemoteException;
 ArrayList<Kid> getAllKids(Parent parent) throws RemoteException;
 void addKid(Parent parent, Kid kid) throws RemoteException;
 Kid getKidById(int id) throws RemoteException;
 Kid getKid(int index) throws RemoteException;
 ArrayList<Booking> getAllBookings(Babysitter babysitter) throws RemoteException;
 ArrayList<Booking> getAllBookings(Parent parent) throws RemoteException;
 Booking getBookingById(int id) throws RemoteException;
 void changeStatus(int id, String status) throws RemoteException;
 int getNotifications(Parent parent) throws RemoteException;
 int getBabysitterPendingBookings(Babysitter babysitter) throws RemoteException;
 void cancelBooking(Booking booking) throws RemoteException;
}
