package viewmodel;

import model.Account;
import model.Babysitter;
import model.Kid;
import model.Parent;

public class ViewState
{
  private Account account;
  private Babysitter babysitter;
  private Parent parent;
  private Kid kid;

  public ViewState()
  {
    this.account = null;
    this.babysitter = null;
    this.parent = null;
    this.kid = null;
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

  public Kid getKid()
  {
    return kid;
  }

}
