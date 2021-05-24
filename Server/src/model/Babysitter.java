package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Babysitter extends Account
{
  private double paymentPerHour;
  private ArrayList<String> languages;
  private double babysittingExperience;
  private boolean hasFirstAidCertificate;
  private MyDateTime dateOfBirth;

  public Babysitter(String firstName, String lastName, String userName,String email, String password,
                    MyDateTime dateOfBirth, double babysittingExperience,
                    double paymentPerHour, String mainLanguage,
                    boolean hasFirstAidCertificate)
  {
    super(firstName, lastName, userName, email, password );
    setPaymentPerHour(paymentPerHour);
    setBabysittingExperience(babysittingExperience);
    setFirstAidCertificate(hasFirstAidCertificate);
    setDateOfBirth(dateOfBirth);

    this.languages = new ArrayList<>();
    addLanguage(mainLanguage);
    setDateOfBirth(dateOfBirth);

  }

/*  public Babysitter(String userName, String password, String email, String firstName, String lastName)
  {
    super(userName, password, email, firstName, lastName);

  }*/

  public int getAge()
  {
    LocalDate localDate = LocalDate.now();
    MyDateTime date = new MyDateTime(localDate.getDayOfMonth(),
            localDate.getMonthValue(), localDate.getYear());
    if (dateOfBirth == null)
    {
      return 0;
    }
    else
    {
      return date.yearsBetween(this.dateOfBirth);
    }
  }


  public MyDateTime getDateOfBirth()
  {
    return dateOfBirth;
  }

  public String getMainLanguage()
  {
    if (languages == null)
    {
      return "";
    }
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

  public double getBabysittingExperience()
  {
    return babysittingExperience;
  }

  public void setBabysittingExperience(double babysittingExperience)
  {
    if (babysittingExperience < 0)
    {
      double positiveBabysittingExperience = Math.abs(babysittingExperience);
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
    LocalDate today = LocalDate.now();
    MyDateTime legalBirthDate = new MyDateTime(today.getDayOfMonth(),
            today.getMonthValue(), today.getYear()-13);
    if (dateOfBirth == null)
    {
      throw new IllegalArgumentException("Please enter your date of birth");
    }else if (dateOfBirth.isAfterDateTime(legalBirthDate)){
      throw new IllegalArgumentException("You have got to be over the age of 13 to sign up for Kinder");
    }
    this.dateOfBirth = dateOfBirth;

  }

  public boolean equals(Object obj)
  {
    if (!(obj instanceof Babysitter))
    {
      return false;
    }
    Babysitter other = (Babysitter) obj;
    return super.equals(obj) && paymentPerHour == other.paymentPerHour
            && babysittingExperience == other.babysittingExperience
            && hasFirstAidCertificate == other.hasFirstAidCertificate;
  }

  @Override public String toString()
  {
    return super.toString() + "\n" + "Payment per hour: " + paymentPerHour
            + "\n" + "Main language: " + getMainLanguage() + "\n" + "Languages: "
            + languages + "\n" + "Years of experience: " + babysittingExperience
            + "\n" + "Has CPR certificate: " + hasFirstAidCertificate + "\n"
            + "Birthday: " + dateOfBirth;
  }
}
