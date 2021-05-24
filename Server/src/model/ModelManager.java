package model;

import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeAction;
import utility.observer.subject.PropertyChangeHandler;
import utility.observer.subject.PropertyChangeProxy;

import java.rmi.RemoteException;
import java.util.ArrayList;

import java.util.InputMismatchException;

public class ModelManager implements Model
{

  private AccountList accountList;
  private AccountList babysitterList;
  private AccountList parentList;
  private ArrayList<Parent> parents;
  private AccountList loggedInList;

  private BookingList bookingList;

  ArrayList<Kid> kids;

  private PropertyChangeHandler<Account, Booking> property;
  //    private PropertyChangeAction<Account, Account> accountProperty; //TODO incomment again when account class isimplemented

  public ModelManager()
  {
    this.accountList = new AccountList();
    this.babysitterList = new AccountList();
    this.parentList = new AccountList();
    this.loggedInList = new AccountList();

    this.bookingList = new BookingList();

    this.kids = new ArrayList<>();
    this.parents = new ArrayList<>();

    this.property = new PropertyChangeHandler<>(this);
    addDummyData();
  }

  private void addDummyData()
  {
    Account babysitter2 = new Babysitter("Loredana", "Cicati", "lori",
        "lori@mail.ru", "lialialia", new MyDateTime(13, 2, 2001), 2, 30,
        "English", false);
    Account parent1 = new Parent("Ana", "Peters", "ana", "ana@gmail.com",
        "password", false);
    Account parent2 = new Parent("Lina", "Peters", "lina", "lina@gmail.com",
        "password", true);
    //accountList.addAccount(babysitter);
    accountList.addAccount(babysitter2);
    accountList.addAccount(parent1);
    accountList.addAccount(parent2);
    //babysitterList.addAccount(babysitter);
    babysitterList.addAccount(babysitter2);
    parentList.addAccount(parent1);
    parentList.addAccount(parent2);
  }

  @Override public void addBooking(Booking booking)
      throws IllegalArgumentException
  {
    bookingList.addBooking(booking);    //TODO
    property.firePropertyChange("add", booking.getBabysitter(), booking);
  }

  @Override public boolean isPasswordCorrect(String userName, String password)
      throws RemoteException
  {
    return false;
  }

  @Override public void close()
  {
    //            property.close();
  }

  @Override public Account login(String username, String password)
  {
    if (username.isEmpty() || password.isEmpty())
    {
      throw new IllegalArgumentException("Please fill out the fields");
    }
    Account account = accountList.getByUserName(username);

    if (!accountList.containsUsername(username))
    {
      throw new IllegalArgumentException(
          "The username or password is incorrect, please try again");
    }

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

  //    @Override public void registerBabysitter(String userName, String password,
  //        String email, String firstName, String lastName)
  //    {
  //      if (!accountList.containsUsername(userName) && !accountList.containsEmail(email))
  //      {
  //        Account account = new Babysitter(userName, password, email, firstName,
  //            lastName);
  //        accountList.addAccount(account);
  //        babysitterList.addAccount(account);
  //      }
  //      else if (accountList.containsUsername(userName))
  //      {
  //        throw new IllegalStateException(
  //            "An user with this username is already registered in the system");
  //      }
  //      else if (accountList.containsEmail(email))
  //      {
  //        throw new IllegalStateException(
  //            "An user with this email is already registered in the system");
  //      }
  //    }

  @Override public void registerBabysitter(String firstName, String lastName,
      String userName, String email, String password, MyDateTime birthday,
      double babysittingExperience, double paymentPerHour, String mainLanguage,
      boolean hasFirstAidCertificate)
  {

    if (!accountList.containsUsername(userName) && !accountList
        .containsEmail(email))
    {
      Account account = new Babysitter(firstName, lastName, userName, email,
          password, birthday, babysittingExperience, paymentPerHour,
          mainLanguage, hasFirstAidCertificate);
      accountList.addAccount(account);
      babysitterList.addAccount(account);

    }
  }
  //    @Override public void registerParent(String userName, String password,
  //        String email, String firstName, String lastName)
  //    {
  //        if (!accountList.containsUsername(userName) && !accountList
  //            .containsEmail(email))
  //        {
  //            Account account = new Parent(userName, password, email, firstName,
  //                lastName);
  //            accountList.addAccount(account);
  //            parentList.addAccount(account);
  //        }
  //        else if (accountList.containsUsername(userName))
  //        {
  //            throw new IllegalStateException(
  //                "An user with this username is already registered in the system");
  //        }
  //        else if (accountList.containsEmail(email))
  //        {
  //            throw new IllegalStateException(
  //                "An user with this email is already registered in the system");
  //        }
  //    }

  @Override public AccountList getParentList()
  {
    return parentList;
  }

  @Override public Parent getParent(String username)
  {
    if (parentList.containsUsername(username))
    {
      return (Parent) parentList.getByUserName(username);
    }
    else
    {
      return null;
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

  @Override public Babysitter getBabysitter(String username)
  {
    if (babysitterList.containsUsername(username))
    {
      return (Babysitter) babysitterList.getByUserName(username);
    }
    else
    {
      return null;
    }
  }

  @Override public void logout(Account account)
  {
    if (accountList.contains(account))
    {
      if (loggedInList.contains(account))
      {
        loggedInList.removeAccount(account);
      }
      else
      {
        throw new IllegalArgumentException("error");
      }
    }
    else
    {
      throw new IllegalArgumentException("Nonexistent account");
    }
  }

  @Override public void registerParent(String firstName, String lastName,
      String userName, String email, String password, boolean hasPets)
  {
    if (!accountList.containsUsername(userName) && !accountList
        .containsEmail(email))
    {
      Account account = new Parent(firstName, lastName, userName, email,
          password, hasPets);
      accountList.addAccount(account);
      parentList.addAccount(account);

    }
  }

  @Override public ArrayList<Kid> getKidList()
  {
    return kids;
  }

  @Override public void editKidData(Parent parent, int id, Kid kid)
  {

    parent = (Parent) parentList.getByUserName(parent.getUserName());
    parent.getKidByID(id)
        .editData(kid.getId(), kid.getDateOfBirth().getDayOfMonth(),
            kid.getDateOfBirth().getMonthValue(),
            kid.getDateOfBirth().getYear(), kid.getGender(),
            kid.getHealthConditions());
  }

  @Override public ArrayList<Kid> getAllKids(Parent parent)
  {
    return parent.getKids();
  }

  @Override public ArrayList<Parent> getAllParents()
  {
    return parents;
  }

  @Override public void addKid(Parent parent, Kid kid)
  {
    if (kid != null && kid.getId() != 0 && !kid.getHealthConditions().isEmpty())
    {
      parent = (Parent) parentList.getByUserName(parent.getUserName());
      parent.addKid(kid);
    }
    else
    {
      throw new IllegalArgumentException("Please fill out all the data");
    }
  }

  @Override public Parent getLoggedInParent()
  {
    ArrayList<Parent> parentLoggedIn = loggedInList.getAllParentAccounts();
    return parentLoggedIn.get(0);
  }

  @Override public Kid getKidById(int id)
  {
    try
    {
      for (int i = 0; i < kids.size(); i++)
      {
        if (kids.get(i).getId() == id)
        {
          return kids.get(i);
        }
      }
    }
    catch (InputMismatchException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  @Override public Kid getKid(int index)
  {
    return kids.get(index);

  }

  public BookingList getBookingList()
  {
    return bookingList;
  }

  @Override public ArrayList<Booking> getAllBookings(Babysitter babysitter)
  {
    return bookingList.getBabysitterBookings(babysitter);
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
}
