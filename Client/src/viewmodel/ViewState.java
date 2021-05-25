package viewmodel;

import model.*;

public class ViewState
{
  private Account account;
  private Babysitter babysitter;
  private Parent parent;
  private Booking booking;
  private Kid kid;
  private Kid selectedKid;
  private int selectedChild;
  private int selectedParent;
  private int selectedBooking;

  public ViewState()
  {
    this.account = null;
    this.babysitter = null;
    this.parent = null;
    this.kid = null;
    this.booking = null;
    this.selectedKid = null;
    this.selectedParent = -1;
    this.selectedChild = -1;
    this.selectedBooking = -1;
  }

  public Account getAccount()
  {
    return account;
  }

  public void setAccount(Account account)
  {
    this.account = account;
  }

  public void removeAccount()
  {
    account = null;
  }

  public Babysitter getBabysitter()
  {
    return babysitter;
  }

  public Parent getParent()
  {
    return parent;
  }

  public void setBabysitter(Babysitter babysitter)
  {
    this.babysitter = babysitter;
  }

  public void setParent(Parent parent)
  {
    this.parent = parent;
  }

  public Kid getKid()
  {
    return kid;
  }

  public Kid getSelectedKid()
  {
    return selectedKid;
  }

  public void setSelectedKid(Kid kid)
  {
    this.kid = kid;
  }

  public void setSelectedChild(int id)
  {
    this.selectedChild = id;
  }

  public int getSelectedParent()
  {
    return selectedParent;
  }

  public int getSelectedChild()
  {
    return selectedChild;
  }

  public void setSelectedParent(int id)
  {
    this.selectedParent = id;
  }

  public void removeSelectedKid()
  {
    selectedChild = -1;
  }
  public int getSelectedBooking()
  {
    return selectedBooking;
  }

  public void setSelectedBooking(int selectedBooking)
  {
    this.selectedBooking = selectedBooking;
  }
  public void removeSelectedBooking()
  {
    selectedBooking = -1;
  }

  public Booking getBooking()
  {
    return booking;
  }

  public void setBooking(Booking booking)
  {
    this.booking = booking;
  }

}
