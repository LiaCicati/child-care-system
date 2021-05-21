package model;

import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeAction;
import utility.observer.subject.PropertyChangeProxy;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.Period;
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

    private PropertyChangeAction<Booking, Booking> property;
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

        this.property = new PropertyChangeProxy<>(this);
        addDummyData();
    }

    private void addDummyData()
    {
        Account babysitter = new Babysitter("lia", "lialialia", "lia@mail.ru",
            "Lia", "Cicati");
        Account babysitter2 = new Babysitter("lori", "lialialia",
            "lori@mail.ru", "Loredana", "Cicati", new MyDateTime(13, 2, 2001),
            30, "English", 2, false);
        Parent parent1 = new Parent("ana", "password", "ana@gmail.com", "Ana",
            "Peters");
        Account parent2 = new Parent("lina", "password", "lina@gmail.com",
            "Lina", "Peters", true);
        Kid kid = new Kid(1, 13, 2, 2017, false, "nothing");
        accountList.addAccount(babysitter);
        accountList.addAccount(babysitter2);
        //    kids.add(kid);
        parent1.addKid(kid);
        System.out.println(getAllKids(parent1));
        System.out.println(parent1.getNrOfKids());
        accountList.addAccount(parent1);
        accountList.addAccount(parent2);
        babysitterList.addAccount(babysitter);
        babysitterList.addAccount(babysitter2);
        parentList.addAccount(parent1);
        parentList.addAccount(parent2);
    }

    @Override public void addBooking(Booking booking)
        throws IllegalArgumentException
    {
        bookingList.addBooking(booking);    //TODO
        //   property.firePropertyChange("add", null, booking);
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

    @Override public void registerBabysitter(String userName, String password,
        String email, String firstName, String lastName, LocalDate birthday,
        double paymentPerHour, String mainLanguage,
        double babysittingExperience, boolean hasFirstAidCertificate)
    {

        if (!accountList.containsUsername(userName) && !accountList
            .containsEmail(email))
        {
            Account account = new Babysitter(userName, password, email,
                firstName, lastName, new MyDateTime(birthday.getDayOfMonth(),
                birthday.getMonthValue(), birthday.getYear()), paymentPerHour,
                mainLanguage, babysittingExperience, hasFirstAidCertificate);
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
            Account account = new Babysitter(userName, password, email,
                firstName, lastName);
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

    @Override public void registerParent(String userName, String password,
        String email, String firstName, String lastName)
    {
        if (!accountList.containsUsername(userName) && !accountList
            .containsEmail(email))
        {
            Account account = new Parent(userName, password, email, firstName,
                lastName);
            accountList.addAccount(account);
            parentList.addAccount(account);
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

    @Override public void registerParent(String userName, String password,
        String email, String firstName, String lastName, boolean hasPets)
    {
        if (!accountList.containsUsername(userName) && !accountList
            .containsEmail(email))
        {
            Account account = new Parent(userName, password, email, firstName,
                lastName, hasPets);
            accountList.addAccount(account);
            parentList.addAccount(account);
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

    @Override public Parent getLoggedInParent()
    {
        ArrayList<Parent> parentLoggedIn = loggedInList.getAllParentAccounts();
        return parentLoggedIn.get(0);
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

    public BookingList getBookingList()
    {
        return bookingList;
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
        if (kid != null && kid.getId() != 0 && !kid.getHealthConditions()
            .isEmpty())
        {
            parent = (Parent) parentList.getByUserName(parent.getUserName());
            parent.addKid(kid);
        }
        else
        {
            throw new IllegalArgumentException("Please fill out all the data");
        }
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
