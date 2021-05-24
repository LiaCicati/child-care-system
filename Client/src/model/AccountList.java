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

    public int getNumberOfAccounts()
    {
        return accounts.size();
    }

    public ArrayList<Account> getAllAccounts()
    {
        return accounts;
    }

    public int getNumberOfParentAccounts()
    {
        int check = 0;
        for (Account account : accounts)
        {
            if (account instanceof Parent)
            {
                check++;
            }
        }
        return check;
    }

    public int getNumberOfBabysitterAccounts()
    {
        int check = 0;
        for (Account account : accounts)
        {
            if (account instanceof Babysitter)
            {
                check++;
            }
        }
        return check;
    }

    public ArrayList<Parent> getAllParentAccounts()
    {
        ArrayList<Parent> temp = new ArrayList<>();
        for (Account account : accounts)
        {
            if (account instanceof Parent)
            {
                temp.add((Parent) account);
            }
        }
        return temp;
    }

    public ArrayList<Babysitter> getAllBabysitterAccounts()
    {
        ArrayList<Babysitter> temp = new ArrayList<>();
        for (Account account : accounts)
        {
            if (account instanceof Babysitter)
            {
                temp.add((Babysitter) account);
            }
        }
        return temp;
    }

    public ArrayList<Babysitter> getByPayRate(int payPerHour)
    {
        ArrayList<Babysitter> temp = new ArrayList<>();
        for (Account account : accounts)
        {
            if (account instanceof Babysitter)
            {
                if (payPerHour >= ((Babysitter) account).getPaymentPerHour())
                {
                    temp.add((Babysitter) account);
                }
            }
        }
        return temp;
    }

    public ArrayList<Babysitter> getWithFirstAid()
    {
        ArrayList<Babysitter> temp = new ArrayList<>();
        for (Account account : accounts)
        {
            if (account instanceof Babysitter)
            {
                if (((Babysitter) account).hasFirstAidCertificate())
                {
                    temp.add((Babysitter) account);
                }
            }
        }
        return temp;
    }

    public ArrayList<Babysitter> getWithLanguage(String language)
    {
        ArrayList<Babysitter> temp = new ArrayList<>();
        for (Account account : accounts)
        {
            if (account instanceof Babysitter)
            {
                if (((Babysitter) account).getLanguages().contains(language))
                {
                    temp.add((Babysitter) account);
                }
            }
        }
        return temp;
    }

    //    public ArrayList<Parent> getWithPets()
    //    {
    //        ArrayList<Parent> temp = new ArrayList<>();
    //        for (Account account : accounts)
    //        {
    //            if (account instanceof Parent)
    //            {
    //                if (((Parent) account).hasPets())
    //                {
    //                    temp.add((Parent) account);
    //                }
    //            }
    //        }
    //        return temp;
    //    }

    //    public ArrayList<Parent> getWithoutPets()
    //    {
    //        ArrayList<Parent> temp = new ArrayList<>();
    //        for (Account account : accounts)
    //        {
    //            if (account instanceof Parent)
    //            {
    //                if (!((Parent) account).hasPets())
    //                {
    //                    temp.add((Parent) account);
    //                }
    //            }
    //        }
    //        return temp;
    //    }

    public Account getByUserName(String userName)
    {
        for (Account account : accounts)
        {
            if (account.getUserName().equals(userName))
            {
                return account;
            }
        }
        return null;
    }

    public Account getByEmail(String email)
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

    public Account getByFirstName(String firstName)
    {
        for (Account account : accounts)
        {
            if (account.getFirstName().equals(firstName))
            {
                return account;
            }
        }
        return null;
    }

    public Account getByLastName(String lastName)
    {
        for (Account account : accounts)
        {
            if (account.getLastName().equals(lastName))
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
            if (accounts.get(i).getUserName().equals(account.getUserName()))
            {
                return true;
            }
        }
        return false;
    }

    public boolean containsUsername(String username)
    {
        for (int i = 0; i < accounts.size(); i++)
        {
            if (accounts.get(i).getUserName().equals(username))
            {
                return true;
            }
        }
        return false;
    }

    public boolean containsEmail(String email)
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

    public Account getAccount(int index) {
        return accounts.get(index);
    }

    @Override public String toString()
    {
        return "Accounts : " + "\n" + accounts;
    }
}