package model;

import utility.observer.subject.LocalSubject;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface Model extends LocalSubject<Account, Booking>
{
  void addBooking(Booking booking) throws IllegalArgumentException;

  boolean isPasswordCorrect(String userName, String password)
      throws RemoteException;

  void close();
  Account login(String username, String password);
  void registerBabysitter(String firstName, String lastName, String userName,String email, String password,
      MyDateTime birthday, double babysittingExperience,
      double paymentPerHour, String mainLanguage,
      boolean hasFirstAidCertificate);
  // void registerBabysitter(String userName, String password, String email, String firstName, String lastName);
  //  void registerParent(String firstName, String lastName, String userName,String email, String password);
  void registerParent(String firstName, String lastName, String userName,String email, String password, boolean hasPets);
  AccountList getParentList();
  Parent getParent(String username);
  AccountList getAccountList();
  AccountList getBabysitterList();
  Babysitter getBabysitter(String username);
  void logout(Account account);
  BookingList getBookingList();
  public Parent getLoggedInParent();

  ArrayList<Kid> getKidList();
  void editKidData(Parent parent, int id, Kid kid);
  ArrayList<Kid> getAllKids(Parent parent);
  ArrayList<Parent> getAllParents();
  void addKid(Parent parent, Kid kid);
  Kid getKidById(int id);
  Kid getKid(int index);
ArrayList<Booking> getAllBookings(Babysitter babysitter);
ArrayList<Booking> getAllBookings(Parent parent);
Booking getBookingById(int id);
void changeStatus(int id, String status);
}
