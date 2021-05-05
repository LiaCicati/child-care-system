package model;

import java.util.regex.*;

public abstract class Account
{
    private String userName;
    private String password;
    private String email;
    private String firstName;
    private String lastName;

    public Account(String userName, String password, String email,
        String firstName, String lastName)
    {
        if (!validateUserName(userName))
        {
            System.out.println("Username is not valid!");
        }
        this.userName = userName;

        if (!validatePassword(password))
        {
            System.out.println("Password is not valid!");
        }
        this.password = password;

        if (!validateEmail(email))
        {
            System.out.println("Email is not valid!");
        }
        this.email = email;

        this.firstName = firstName;
        this.lastName = lastName;
    }

    public boolean validateUserName(String userName)
    {
        return userName.length() <= 16 && !userName.isEmpty();
        //TODO check for matching usernames
    }

    public boolean validatePassword(String password)
    {
        return password.length() >= 8 && password.length() <= 16;
    }

    //https://www.javatpoint.com/java-email-validation
    public boolean validateEmail(String email)
    {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
        //TODO check for matching emails
    }

    @Override public boolean equals(Object obj)
    {
        if (!(obj instanceof Account))
        {
            return false;
        }
        Account other = (Account) obj;
        return userName.equals(other.userName) && password
            .equals(other.password) && email.equals(other.email) && firstName
            .equals(other.firstName) && lastName.equals(other.lastName);
    }

    @Override public String toString()
    {
        return "Username: " + userName + "\n" + "Password: " + password + "\n"
            + "Email: " + email + "\n" + "First name: " + firstName + "\n"
            + "Last name: " + lastName;
    }
}
