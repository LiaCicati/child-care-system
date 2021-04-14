package Model;

import java.util.ArrayList;

public class Babysitter
{
  private int age;
  private double paymentPerHour;
  private ArrayList<String> languages;
  private double babysittingExperience;
  private boolean hasFirstAidCertificate;
  private String userName;
  private MyDateTime dateOfBirth;

  //  public Babysitter(String userName, MyDateTime dateOfBirth,
  //      double paymentPerHour, ArrayList<String> languages,
  //      double babysittingExperience, boolean hasFirstAidCertificate)
  //  {
  //
  //    setUserName(userName);
  //    setDateOfBirth(dateOfBirth);
  //    setPaymentPerHour(paymentPerHour);
  //    setBabysittingExperience(babysittingExperience);
  //    setFirstAidCertificate(hasFirstAidCertificate);
  //  for (int i = 0; i < languages.size(); i++)
  //  {
  //    addLanguage(languages.get(i));
  //  }
  //
  //  }

  public Babysitter(String userName, MyDateTime dateOfBirth,
      double paymentPerHour, String mainLanguage, double babysittingExperience,
      boolean hasFirstAidCertificate)
  {

    setUserName(userName);
    setDateOfBirth(dateOfBirth);
    setPaymentPerHour(paymentPerHour);
    setBabysittingExperience(babysittingExperience);
    setFirstAidCertificate(hasFirstAidCertificate);
    this.languages = new ArrayList<>();
    addLanguage(mainLanguage);

  }

  public MyDateTime getAge()
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
    this.paymentPerHour = paymentPerHour;
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
        && languages.equals(other.languages)
        && babysittingExperience == other.babysittingExperience
        && hasFirstAidCertificate == other.hasFirstAidCertificate && userName
        .equals(other.userName) && dateOfBirth.equals(other.dateOfBirth);
  }

  @Override public String toString()
  {
    return "Username: " + userName + "\n" + "Age: " + age + "\n"
        + "Payment per hour: " + paymentPerHour + "\n" + "Main language: "
        + getMainLanguage() + "\n" + "Languages: " + languages + "\n"
        + "Years of experience: " + babysittingExperience + "\n"
        + "Has CPR certificate: " + hasFirstAidCertificate + "\n" + "Birthday: "
        + dateOfBirth;
  }
}

