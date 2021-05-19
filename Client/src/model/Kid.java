package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Kid implements Serializable
{
  private int age;
  private int id;
  private boolean gender; //false = boy, true = girl
  private String healthConditions;
  private MyDateTime dateOfBirth;

  public Kid(int id, int birthDay, int birthMonth, int birthYear,
      boolean gender, String healthConditions)
  {
    setDateOfBirth(new MyDateTime(birthDay, birthMonth, birthYear));
    setGender(gender);
    setHealthConditions(healthConditions);
    setId(id);
  }

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }
  //  public int getAge(MyDateTime dateOfBirth)
  //  {
  //    int currentDay = LocalDate.now().getDayOfMonth();
  //    int currentMonth = LocalDate.now().getMonthValue();
  //    int currentYear = LocalDate.now().getYear();
  //
  //    int birthDay = dateOfBirth.getDay();
  //    int birthMonth = dateOfBirth.getMonth();
  //    int birthYear = dateOfBirth.getYear();
  //
  //    int age = currentYear - birthYear;
  //
  //    int differenceInDays = currentDay - birthDay;
  //    int differenceInMonths = currentMonth - birthMonth;
  //    if (differenceInDays < 0)
  //    {
  //      differenceInMonths = differenceInMonths - 1;
  //    }
  //    if (differenceInMonths < 0)
  //    {
  //      return age - 1;
  //    }
  //    else
  //    {
  //      return age;
  //    }
  //  }

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

  public LocalDate getDateOfBirth()
  {
    return LocalDate.of(dateOfBirth.getYear(), dateOfBirth.getMonth(), dateOfBirth.getDay());
  }

  public void setDateOfBirth(MyDateTime dateOfBirth)
  {
    this.dateOfBirth = dateOfBirth;
  }

  public String getHealthConditions()
  {
    return healthConditions;
  }

  public void setHealthConditions(String healthConditions)
  {
    if (healthConditions == null || healthConditions.equals(""))
    {
      throw new IllegalArgumentException(
          "Please specify the health condition of your child");
    }

    this.healthConditions = healthConditions;
  }

  public boolean getGender()
  {
    return gender;
  }

  public void setGender(boolean gender)
  {
    this.gender = gender;
  }

  public void editData(int id, int birthDay, int birthMonth, int birthYear,
      boolean gender, String healthConditions)
  {
    setId(id);
    setDateOfBirth(new MyDateTime(birthDay, birthMonth, birthYear));
    setGender(gender);
    setHealthConditions(healthConditions);

  }

  public boolean equals(Object obj)
  {
    if (!(obj instanceof Kid))
    {
      return false;
    }
    Kid other = (Kid) obj;
    return age == other.age && gender == other.gender && healthConditions
        .equals(other.healthConditions) && dateOfBirth
        .equals(other.dateOfBirth);
  }

  public String toString()
  {
    return "Age: " + age + "\n" + "Gender: " + gender + "\n"
        + "Health conditions: " + healthConditions + "\n" + "Date of birth: "
        + dateOfBirth;
  }

}