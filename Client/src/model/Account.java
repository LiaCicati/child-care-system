package model;

import java.io.Serializable;
import java.util.regex.*;

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
    setUserName(userName);
    setPassword(password);
    setEmail(email);
    setFirstName(firstName);
    setLastName(lastName);
  }

  public String getPassword()
  {
    return password;
  }

  public String getUserName()
  {
    return userName;
  }

  public String getEmail()
  {
    return email;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public String getLastName()
  {
    return lastName;
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
    String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
    //TODO check for matching emails
  }

  public void setFirstName(String firstName)
  {
    if (firstName == null || firstName.equals(""))
    {
      throw new IllegalArgumentException("Please enter your first name");
    }
    this.firstName = firstName;
  }

  public void setLastName(String lastName)
  {
    if (lastName == null || lastName.equals(""))
    {
      throw new IllegalArgumentException("Please enter your last name");
    }
    this.lastName = lastName;
  }

  public void setUserName(String userName)
  {
    if (!validateUserName(userName))
    {
      throw new IllegalArgumentException(
          "The username can not have more than 16 characters or be empty");
    }
    this.userName = userName;
  }

  public void setEmail(String email)
  {
    if (!validateEmail(email))
    {
      throw new IllegalArgumentException("The email is not valid");
    }
    this.email = email;
  }

  public void setPassword(String password)
  {
    if (!validatePassword(password))
    {
      throw new IllegalArgumentException(
          "The password must contain from 8 to 16 characters");
    }
    this.password = password;
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
