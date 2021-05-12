package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Babysitter extends Account
{
  private double paymentPerHour;
  private ArrayList<String> languages;
  private double babysittingExperience;
  private boolean hasFirstAidCertificate;
  private String userName;
  private MyDateTime dateOfBirth;

  public Babysitter(String userName, String password, String email,
      String firstName, String lastName, int birthDay, int birthMonth,
      int birthYear, double paymentPerHour, String mainLanguage,
      double babysittingExperience, boolean hasFirstAidCertificate)
  {
    super(userName, password, email, firstName, lastName);
    setPaymentPerHour(paymentPerHour);
    setBabysittingExperience(babysittingExperience);
    setFirstAidCertificate(hasFirstAidCertificate);

    this.languages = new ArrayList<>();
    addLanguage(mainLanguage);

    setDateOfBirth(new MyDateTime(birthDay, birthMonth, birthYear));

  }

  public int getAge(MyDateTime dateOfBirth)
  {
    int currentDay = LocalDate.now().getDayOfMonth();
    int currentMonth = LocalDate.now().getMonthValue();
    int currentYear = LocalDate.now().getYear();

    int birthDay = dateOfBirth.getDay();
    int birthMonth = dateOfBirth.getMonth();
    int birthYear = dateOfBirth.getYear();

    int age = currentYear - birthYear;

    int differenceInDays = currentDay - birthDay;
    int differenceInMonths = currentMonth - birthMonth;
    if (differenceInDays < 0)
    {
      differenceInMonths = differenceInMonths - 1;
    }
    if (differenceInMonths < 0)
    {
      return age - 1;
    }
    else
    {
      return age;
    }
  }

  public MyDateTime getDateOfBirth()
  {
    return dateOfBirth;
  }

  public String getMainLanguage()
  {
    return languages.get(0);
  }

  public double getPaymentPerHour()
  {
    return paymentPerHour;
  }

  public void setPaymentPerHour(double paymentPerHour)
  {
    if (paymentPerHour < 0)
    {
      double positivePaymentPerHour = Math.abs(paymentPerHour);
      this.paymentPerHour = positivePaymentPerHour;
    }
    else if (paymentPerHour > 500)
    {
      this.paymentPerHour = 500;
    }
    else
    {
      this.paymentPerHour = paymentPerHour;
    }
  }

  public ArrayList<String> getLanguages()
  {
    return languages;
  }

  public void addLanguage(String language)
  {
    languages.add(language);
  }

  public String getUserName()
  {
    return userName;
  }

  public void setUserName(String userName)
  {
    this.userName = userName;
  }

  public double getBabysittingExperience()
  {
    return babysittingExperience;
  }

  public void setBabysittingExperience(double babysittingExperience)
  {
    if (babysittingExperience < 0)
    {
      double positiveBabysittingExperience = Math
          .abs(babysittingExperience);
      this.babysittingExperience = positiveBabysittingExperience;
    }
/*    else if  (babysittingExperience>getAge(getDateOfBirth())){
      this.babysittingExperience = getAge(getDateOfBirth());
    }*/
    else if (babysittingExperience > 70)
    {
      this.babysittingExperience = 70;
    }
    else
    {
      this.babysittingExperience = babysittingExperience;
    }
  }

  public boolean hasFirstAidCertificate()
  {
    return hasFirstAidCertificate;
  }

  public void setFirstAidCertificate(boolean hasFirstAidCertificate)
  {
    this.hasFirstAidCertificate = hasFirstAidCertificate;
  }

  public void setDateOfBirth(MyDateTime dateOfBirth)
  {
    this.dateOfBirth = dateOfBirth;
  }

  public boolean equals(Object obj)
  {
    if (!(obj instanceof Babysitter))
    {
      return false;
    }
    Babysitter other = (Babysitter) obj;
    return super.equals(obj) && paymentPerHour == other.paymentPerHour && getMainLanguage()
        .equals(other.getMainLanguage())
        && babysittingExperience == other.babysittingExperience
        && hasFirstAidCertificate == other.hasFirstAidCertificate
        && userName.equals(other.userName) && dateOfBirth
        .equals(other.dateOfBirth);
  }

  @Override public String toString()
  {
    return super.toString() + "\n" + "Payment per hour: "
        + paymentPerHour + "\n" + "Main language: " + getMainLanguage()
        + "\n" + "Languages: " + languages + "\n" + "Years of experience: "
        + babysittingExperience + "\n" + "Has CPR certificate: "
        + hasFirstAidCertificate + "\n" + "Birthday: " + dateOfBirth;
  }
}

