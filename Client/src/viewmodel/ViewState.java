package viewmodel;

import model.Account;
import model.Babysitter;

public class ViewState
{
  private Account account;
  private Babysitter babysitter;

  public ViewState()
  {
    this.account = null;
    this.babysitter = null;
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

  public void setBabysitter(Babysitter babysitter)
  {
    this.babysitter = babysitter;
  }

}
