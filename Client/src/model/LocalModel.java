package model;

import utility.observer.subject.LocalSubject;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface LocalModel extends LocalSubject<String, String>
{
  void addBooking(Booking booking) throws RemoteException;
  boolean isPasswordCorrect(String userName, String password)
      throws RemoteException;
  public void close();
  Account login(String username, String password);
  void registerBabysitter(String userName, String password, String email,
      String firstName, String lastName, LocalDate birthday,
      double paymentPerHour, String mainLanguage, double babysittingExperience,
      boolean hasFirstAidCertificate);
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
  ArrayList<Kid> getKidList();
  void editKidData(String username, int id, Kid kid);
  ArrayList<Kid> getAllKids(Parent parent);
  void addKid( Kid kid);
  ArrayList<Kid> getKids(Parent parent);
  ArrayList<Parent> getAllParents();
  Account getParentByUsername(String username);
  void addKid(Parent parent, Kid kid);
}
