package mediator;

import model.*;

import utility.observer.subject.RemoteSubject;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface RemoteModel extends RemoteSubject<Account, Booking>
{
  void addBooking(Booking booking) throws RemoteException;
  boolean isPasswordCorrect(String userName, String password)
      throws RemoteException;
  Account login(String username, String password) throws RemoteException;
  void registerBabysitter(String userName, String password, String email,
      String firstName, String lastName, LocalDate birthday,
      double paymentPerHour, String mainLanguage, double babysittingExperience,
      boolean hasFirstAidCertificate) throws RemoteException;
  void registerBabysitter(String userName, String password, String email,
      String firstName, String lastName) throws RemoteException;
  void registerParent(String userName, String password, String email,
      String firstName, String lastName) throws RemoteException;
  void registerParent(String userName, String password, String email,
      String firstName, String lastName, boolean hasPets) throws RemoteException;
  AccountList getParentList() throws RemoteException;
  Parent getParent(String username) throws RemoteException;
  AccountList getAccountList() throws RemoteException;
  AccountList getBabysitterList() throws RemoteException;
  Babysitter getBabysitter(String username) throws RemoteException;
  void logout(Account account) throws RemoteException;

  Parent getLoggedInParent() throws RemoteException;
  BookingList getBookingList() throws RemoteException;

  ArrayList<Kid> getKidList() throws RemoteException;
  void editKidData(Parent parent, int id, Kid kid) throws RemoteException;
  ArrayList<Kid> getAllKids(Parent parent) throws RemoteException;
  void addKid(Parent parent, Kid kid) throws RemoteException;
  Kid getKidById(int id) throws RemoteException;
  Kid getKid(int index) throws RemoteException;
  ArrayList<Booking> getAllBookings(Babysitter babysitter) throws RemoteException;


}
