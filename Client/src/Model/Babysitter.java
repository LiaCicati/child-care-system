package Model;

import java.util.ArrayList;

public class Babysitter
{
  private int age;
  private double paymentPerHour;
  private ArrayList<String> language;
  private double babysittingExperience;
  private boolean hasFirstAidCertificate;
  private String userName;
  private MyDateTime dateOfBirth;

  public Babysitter(String userName, MyDateTime dateOfBirth,
      double paymentPerHour, ArrayList<String> language,
      double babysittingExperience, boolean hasFirstAidCertificate)
  {

    setUserName(userName);
    setDateOfBirth(dateOfBirth);
    setPaymentPerHour(paymentPerHour);
    setBabysittingExperience(babysittingExperience);
    setFirstAidCertificate(hasFirstAidCertificate);

  }

  public MyDateTime getAge()
  {
    return dateOfBirth;
  }

  public double getPaymentPerHour()
  {
    return paymentPerHour;
  }

  public void setPaymentPerHour(double paymentPerHour)
  {
    this.paymentPerHour = paymentPerHour;
  }

  public ArrayList<String> getLanguage()
  {
    return language;
  }

  public void addLanguage(String language)
  {
///


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
    this.babysittingExperience = babysittingExperience;
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
    return age == other.age && paymentPerHour == other.paymentPerHour
        && language.equals(other.language)
        && babysittingExperience == other.babysittingExperience
        && hasFirstAidCertificate == other.hasFirstAidCertificate && userName
        .equals(other.userName) && dateOfBirth.equals(other.dateOfBirth);
  }

  @Override public String toString()
  {
    return "Username: " + userName + "\n" + "Age: " + age + "\n"
        + "Payment per hour: " + paymentPerHour + "\n" + "Language: " + language
        + "\n" + "Years of experience: " + babysittingExperience + "\n"
        + "Has CPR certificate: " + hasFirstAidCertificate + "\n" + "Birthday: "
        + dateOfBirth;
  }
}

