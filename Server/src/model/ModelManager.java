package model;

import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeAction;
import utility.observer.subject.PropertyChangeProxy;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.Period;

public class ModelManager implements Model
{
  //    private BookingList bookingList;    //TODO
  //    private AccountList accountList; //TODO
  private AccountList accountList;
  private AccountList babysitterList;
  private AccountList loggedInList;
  private PropertyChangeAction<Booking, Booking> property;
  //    private PropertyChangeAction<Account, Account> accountProperty; //TODO incomment again when account class isimplemented

  public ModelManager()
  {
    //        this.bookingList = new BookingList();   //TODO
    //        this.accountList = new AccoubtList(); //TODO
    this.accountList = new AccountList();
    this.babysitterList = new AccountList();
    this.loggedInList = new AccountList();
    this.property = new PropertyChangeProxy<>(this);
  }

  @Override public void addBooking(Booking booking)
      throws IllegalArgumentException
  {
    //        bookingList.addBooking(booking);    //TODO
    //        property.firePropertyChange("add", null, booking);
  }

  @Override public boolean isPasswordCorrect(String userName, String password)
      throws RemoteException
  {
    return false;
/*        Account account = accountList.getAccountByUserName(userName);
        accountProperty.firePropertyChange("getByUserName", account, null);
        if (account.getPassword == password){
            return true;
        } else {
            return false;
        }*/
  }

  @Override public void close()
  {
    //        property.close();
  }

  @Override public Account login(String username, String password)
  {
    if (username.isEmpty() || password.isEmpty())
    {
      throw new IllegalArgumentException("Please fill out the fields");
    }
    Account account = accountList.getByUserName(username);

    if (accountList.getByUserName(username).getPassword().equals(password))
    {
      loggedInList.addAccount(account);
      return account;
    }
    else
    {
      throw new IllegalArgumentException(
          "The username or password is incorrect, please try again");
    }
  }

  @Override public void registerBabysitter(String userName, String password,
      String email, String firstName, String lastName, LocalDate dateBirth,
      double paymentPerHour, String mainLanguage, double babysittingExperience,
      boolean hasFirstAidCertificate)
  {

    int age = babysitterList.getAllBabysitterAccounts().get(0)
        .getAge(dateBirth);
    if (!accountList.containsUsername(userName) && !accountList
        .containsEmail(email))
    {
      Account account = new Babysitter(userName, password, email, firstName,
          lastName, age, paymentPerHour, mainLanguage, babysittingExperience,
          hasFirstAidCertificate);
      accountList.addAccount(account);
      babysitterList.addAccount(account);
    }
    else if (accountList.containsUsername(userName))
    {
      throw new IllegalStateException(
          "An user with this username is already registered in the system");
    }
    else if (accountList.containsEmail(email))
    {
      throw new IllegalStateException(
          "An user with this email is already registered in the system");
    }

  }

  @Override public void registerBabysitter(String userName, String password,
      String email, String firstName, String lastName)
  {
    if (!accountList.containsUsername(userName) && !accountList
        .containsEmail(email))
    {
      Account account = new Babysitter(userName, password, email, firstName,
          lastName);
      accountList.addAccount(account);
      babysitterList.addAccount(account);
    }
    else if (accountList.containsUsername(userName))
    {
      throw new IllegalStateException(
          "An user with this username is already registered in the system");
    }
    else if (accountList.containsEmail(email))
    {
      throw new IllegalStateException(
          "An user with this email is already registered in the system");
    }
  }

  @Override public AccountList getAccountList()
  {
    return accountList;
  }

  @Override public AccountList getBabysitterList()
  {
    return babysitterList;
  }

  @Override public boolean addListener(
      GeneralListener<Booking, Booking> listener, String... propertyNames)
  {
    return property.addListener(listener, propertyNames);
  }

  @Override public boolean removeListener(
      GeneralListener<Booking, Booking> listener, String... propertyNames)
  {
    return property.removeListener(listener, propertyNames);

  }
}
