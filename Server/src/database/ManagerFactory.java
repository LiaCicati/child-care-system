package database;

import model.Babysitter;

public class ManagerFactory
{
  private ParentManager parentManager;
  private BabysitterManager babysitterManager;
  private KidManager kidManager;
  private BookingManager bookingManager;
  private AccountManager accountManager;

  public ManagerFactory(){
    parentManager = new ParentManager();
    babysitterManager = new BabysitterManager();
    kidManager = new KidManager();
    accountManager = new AccountManager(parentManager,babysitterManager,kidManager);
    bookingManager = new BookingManager(accountManager);

  }
  public ParentManager getParentManager()
  {
    return parentManager;
  }

  public BabysitterManager getBabysitterManager()
  {
    return babysitterManager;
  }

  public KidManager getKidManager()
  {
    return kidManager;
  }
  public BookingManager getBookingManager()
  {
    return bookingManager;
  }

  public AccountManager getAccountManager()
  {
    return accountManager;
  }
}
