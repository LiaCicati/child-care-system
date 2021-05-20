package viewmodel;

import model.Account;
import model.Babysitter;
import model.Parent;

public class ViewState
{
  private Account account;
  private Babysitter babysitter;
  private Parent parent;

  public ViewState()
  {
    this.account = null;
    this.babysitter = null;
    this.parent = null;
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

}
