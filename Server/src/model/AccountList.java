package model;

import java.io.Serializable;
import java.util.ArrayList;

public class AccountList implements Serializable
{
  private ArrayList<Account> accounts;

  public AccountList()
  {
    this.accounts = new ArrayList<>();
  }

  public void addAccount(Account account)
  {
    accounts.add(account);
  }

  public void removeAccount(Account account)
  {
    accounts.remove(account);
  }

  public Account getAccountByEmail(String email)
  {
    for (Account account : accounts)
    {
      if (account.getEmail().equals(email))
      {
        return account;
      }
    }
    return null;
  }

  public boolean contains(Account account)
  {
    for (int i = 0; i < accounts.size(); i++)
    {
      if (accounts.get(i).getEmail().equals(account.getEmail()))
      {
        return true;
      }
    }
    return false;
  }

  public boolean contains(String email)
  {
    for (int i = 0; i < accounts.size(); i++)
    {
      if (accounts.get(i).getEmail().equals(email))
      {
        return true;
      }
    }
    return false;
  }

  public String toString()
  {
    return "Size: " + accounts;
  }
}
