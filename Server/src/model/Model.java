package model;

import utility.observer.subject.LocalSubject;

import java.rmi.RemoteException;
import java.time.LocalDate;

public interface Model extends LocalSubject<Booking, Booking>
{
  void addBooking(Booking booking) throws IllegalArgumentException;

  boolean isPasswordCorrect(String userName, String password)
      throws RemoteException;

  void close();
  Account login(String username, String password);
  void registerBabysitter(String userName, String password, String email,
      String firstName, String lastName, LocalDate dateBirth, double paymentPerHour, String mainLanguage,
      double babysittingExperience, boolean hasFirstAidCertificate);
  void registerBabysitter(String userName, String password, String email,
      String firstName, String lastName);
  AccountList getAccountList();
  AccountList getBabysitterList();
  Babysitter getBabysitter(String username);
  void logout(Account account);
}
