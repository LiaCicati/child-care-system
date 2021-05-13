package mediator;

import model.Account;
import model.AccountList;
import model.Booking;
import utility.observer.subject.RemoteSubject;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public interface RemoteModel extends RemoteSubject<String, String>
{

 void addBooking(Booking booking) throws RemoteException;
 boolean isPasswordCorrect(String userName, String password) throws RemoteException;

/*  void connect() throws RemoteException, MalformedURLException, NotBoundException;
  void addBooking(Booking booking) throws RemoteException;
  boolean doesUserNameExist(String userName) throws RemoteException;
  boolean isPasswordCorrect(String userName, String password) throws RemoteException;*/
Account login(String username, String password) throws RemoteException;
 void registerBabysitter(String userName, String password, String email,
     String firstName, String lastName, int birthDay, int birthMonth,
     int birthYear, double paymentPerHour, String mainLanguage,
     double babysittingExperience, boolean hasFirstAidCertificate) throws RemoteException;
 void registerBabysitter(String userName, String password, String email,
     String firstName, String lastName) throws RemoteException;
 AccountList getAccountList() throws RemoteException;
 AccountList getBabysitterList() throws RemoteException;
}
