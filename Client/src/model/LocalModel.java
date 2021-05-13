package model;

import utility.observer.subject.LocalSubject;

import java.rmi.RemoteException;

public interface LocalModel extends LocalSubject<String, String>
{
  void addBooking(Booking booking) throws RemoteException;
  boolean isPasswordCorrect(String userName, String password) throws RemoteException;
public void close();
  Account login(String username, String password);
  void registerBabysitter(String userName, String password, String email,
      String firstName, String lastName, int birthDay, int birthMonth,
      int birthYear, double paymentPerHour, String mainLanguage,
      double babysittingExperience, boolean hasFirstAidCertificate);
  void registerBabysitter(String userName, String password, String email,
      String firstName, String lastName);
  AccountList getAccountList();
  AccountList getBabysitterList();
}
