package model;

import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeAction;
import utility.observer.subject.PropertyChangeProxy;

public class ModelManager implements Model
{
  private AccountList userList;
  private AccountList babysitterList;
  private AccountList parentList;
  private AccountList loggedInList;
  //    private BookingList bookingList;    //TODO
  private PropertyChangeAction<Account, Booking> property;

  public ModelManager()
  {
    loggedInList = new AccountList();
    userList = new AccountList();
    babysitterList = new AccountList();
    parentList = new AccountList();
    //        this.bookingList = new BookingList();   //TODO
    this.property = new PropertyChangeProxy<>(this);
  }

  @Override public void addBooking(Booking booking)
      throws IllegalArgumentException
  {
    //        bookingList.addBooking(booking);    //TODO
    //        property.firePropertyChange("add", null, booking);
  }

  @Override public void close()
  {
    //        property.close();
  }

  @Override public boolean addListener(
      GeneralListener<Account, Booking> listener, String... propertyNames)
  {
    return property.addListener(listener, propertyNames);
  }

  @Override public boolean removeListener(
      GeneralListener<Account, Booking> listener, String... propertyNames)
  {
    return property.removeListener(listener, propertyNames);

  }

  @Override public Account login(String email, String password)
  {

    Account account = userList.getAccountByEmail(email);

    if (userList.getAccountByEmail(email).getPassword().equals(password))
    {
      loggedInList.addAccount(account);
      return account;
    }
     if(email.equals("") || password.equals(""))
    {
      throw new IllegalArgumentException("Please fill out the fields");
    }
    else
    {
      throw new IllegalArgumentException(
          "The email or password is incorrect, please try again");
    }
  }

  @Override public void registerBabysitter(String userName, String password,
      String email, String firstName, String lastName)
  {

    if (!userList.contains(email))
    {
      Account account = new Babysitter(userName, password, email, firstName,
          lastName);
      userList.addAccount(account);
      babysitterList.addAccount(account);
    }
    else if (userName.equals("") || password.equals("") || email.equals("")
        || firstName.equals("") || lastName.equals(""))
    {
      throw new IllegalArgumentException("Fill out all the required fields");
    }
    else if (userList.contains(email))
    {
      throw new IllegalStateException(
          "An user with this email is already registered in the system");
    }
  }

  @Override public void registerParent(String userName, String password,
      String email, String firstName, String lastName, boolean hasPets)
  {
    if (!userList.contains(email))
    {
      Account account = new Parent(userName, password, email, firstName,
          lastName, hasPets);
      userList.addAccount(account);
      parentList.addAccount(account);
    }
    else
    {
      throw new IllegalStateException(
          "An user with this email is already registered in the system");
    }

  }

  @Override public void logout(Account account)
  {
    if (userList.contains(account))
    {
      if (loggedInList.contains(account))
      {
        loggedInList.removeAccount(account);
      }
      else
      {
        throw new IllegalArgumentException(
            "The specified user is not logged in");
      }
    }
    else
    {
      throw new IllegalArgumentException("No such user found");
    }

  }

  @Override public AccountList getAccountList()
  {
    return userList;
  }

  @Override public AccountList getBabysitterList()
  {
    return babysitterList;
  }

  @Override public AccountList getParentList()
  {
    return parentList;
  }

}
