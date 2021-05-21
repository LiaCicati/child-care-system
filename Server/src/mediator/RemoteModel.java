package mediator;

import model.*;

import utility.observer.subject.RemoteSubject;

import java.rmi.RemoteException;
import java.time.LocalDate;

public interface RemoteModel extends RemoteSubject<Booking, Booking>
{
  void addBooking(Booking booking) throws RemoteException;
  boolean isPasswordCorrect(String userName, String password) throws RemoteException;
  Account login(String username, String password) throws RemoteException;
  void registerBabysitter(String firstName, String lastName, String userName,String email, String password,
                          MyDateTime birthday, double babysittingExperience,
                          double paymentPerHour, String mainLanguage,
                          boolean hasFirstAidCertificate) throws RemoteException;
 // void registerBabysitter(String userName, String password, String email, String firstName, String lastName) throws RemoteException;
  void registerParent(String userName, String password, String email, String firstName, String lastName)
          throws RemoteException;
  void registerParent(String userName, String password, String email, String firstName, String lastName,
                      boolean hasPets) throws RemoteException;
  AccountList getParentList() throws RemoteException;
  Parent getParent(String username) throws RemoteException;
  AccountList getAccountList() throws RemoteException;
  AccountList getBabysitterList() throws RemoteException;
  Babysitter getBabysitter(String username) throws RemoteException;
  void logout(Account account) throws RemoteException;
  Parent getLoggedInParent() throws RemoteException;
  BookingList getBookingList() throws RemoteException;

}
