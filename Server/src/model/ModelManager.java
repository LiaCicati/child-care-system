package model;

import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeAction;
import utility.observer.subject.PropertyChangeProxy;

import java.rmi.RemoteException;

public class ModelManager implements Model
{
//    private BookingList bookingList;    //TODO
//    private AccountList accountList; //TODO
    private AccountList accountList;
    private AccountList babysitterList;
    private PropertyChangeAction<Booking, Booking> property;
//    private PropertyChangeAction<Account, Account> accountProperty; //TODO incomment again when account class isimplemented

    public ModelManager()
    {
//        this.bookingList = new BookingList();   //TODO
//        this.accountList = new AccoubtList(); //TODO
        this.accountList = new AccountList();
        this.babysitterList = new AccountList();
        this.property = new PropertyChangeProxy<>(this);
    }

    @Override public void addBooking(Booking booking)
        throws IllegalArgumentException
    {
//        bookingList.addBooking(booking);    //TODO
//        property.firePropertyChange("add", null, booking);
    }


    @Override
    public boolean isPasswordCorrect(String userName, String password) throws RemoteException {
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
        return null;
    }

    @Override public void registerBabysitter(String userName, String password,
        String email, String firstName, String lastName, int birthDay,
        int birthMonth, int birthYear, double paymentPerHour,
        String mainLanguage, double babysittingExperience,
        boolean hasFirstAidCertificate)
    {

    }

    @Override public void registerBabysitter(String userName, String password,
        String email, String firstName, String lastName)
    {
        if (!accountList.contains(userName))
        {
            Account account = new Babysitter(userName, password, email, firstName,
                lastName);
            accountList.addAccount(account);
            babysitterList.addAccount(account);
        }
        else if (userName.equals("") || password.equals("") || email.equals("")
            || firstName.equals("") || lastName.equals(""))
        {
            throw new IllegalArgumentException("Fill out all the required fields");
        }
        else if (accountList.contains(email))
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
