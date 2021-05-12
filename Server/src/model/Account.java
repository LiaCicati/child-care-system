package model;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Account implements Serializable
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
      throw new IllegalArgumentException("Username is not valid");
    }
    this.userName = userName;

    if (!validatePassword(password))
    {
      throw new IllegalArgumentException("Password is not valid");
    }
    this.password = password;

    if (!validateEmail(email))
    {
      throw new IllegalArgumentException("Email is not valid");
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

  public String getEmail()
  {
    return email;
  }

  public String getPassword()
  {
    return password;
  }

  @Override public boolean equals(Object obj)
  {
    if (!(obj instanceof Account))
    {
      return false;
    }
    Account other = (Account) obj;
    return userName.equals(other.userName) && password.equals(other.password)
        && email.equals(other.email) && firstName.equals(other.firstName)
        && lastName.equals(other.lastName);
  }

  @Override public String toString()
  {
    return "Username: " + userName + "\n" + "Password: " + password + "\n"
        + "Email: " + email + "\n" + "First name: " + firstName + "\n"
        + "Last name: " + lastName;
  }
}