package model;

import java.util.ArrayList;

public class AccountList
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
}
