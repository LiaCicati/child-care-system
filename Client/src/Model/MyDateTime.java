package Model;

public class MyDateTime
{
  private int day;
  private int month;
  private int year;
  private int hour;
  private int minute;

  public MyDateTime(int day, int month, int year)
  {
    this.setDay(day);
    this.setMonth(month);
    this.setYear(year);

  }

  public MyDateTime(int day, int month, int year, int hour, int minute)
  {
    this.setDay(day);
    this.setMonth(month);
    this.setYear(year);
    setHour(hour);
    setMinute(minute);
  }

  public MyDateTime(int totalSeconds)
  {
    while (totalSeconds < 0)
    {
      totalSeconds += 86400;
    }
    while (totalSeconds >= 86400)
    {
      totalSeconds -= 86400;
    }
    this.hour = totalSeconds / 3600;
    this.minute = (totalSeconds % 3600) / 60;

  }

  public void set(int hour, int minute, int day, int month, int year)
  {
    this.hour = hour;
    this.minute = minute;
    this.day = day;
    this.month = month;
    this.year = year;
  }

  public int getDay()
  {
    return day;
  }

  public void setDay(int day)
  {
    this.day = day;
  }

  public int getMonth()
  {
    return month;
  }

  public void setMonth(int month)
  {
    this.month = month;
  }

  public int getYear()
  {
    return year;
  }

  public void setYear(int year)
  {
    this.year = year;
  }

  public int getHour()
  {
    return hour;
  }

  public void setHour(int hour)
  {
    this.hour = hour;
  }

  public int getMinute()
  {
    return minute;
  }

  public void setMinute(int minute)
  {
    this.minute = minute;
  }

  public int getTime()
  {
    return day + month + year + (hour * 60 * 60) + (minute * 60);
  }

  public boolean isBefore(MyDateTime time)
  {
    if (getTime() < time.getTime())
    {
      return true;
    }
    else
    {
      return false;
    }
  }



  public boolean equals(Object obj)
  {
    if (!(obj instanceof MyDateTime))
    {
      return false;
    }
    MyDateTime other = (MyDateTime) obj;
    return day == other.day && month == other.month && year == other.year
        && hour == other.hour && minute == other.minute;
  }

  public String toString()
  {
    String s = "";
    if (day < 10)
    {
      s += "0";
    }
    s += day + "/";
    if (month < 10)
    {
      s += "0";
    }
    if (hour == 0 && minute == 0)
    {
      s += month + "/" + year;
    }
    else
    {

      s += month + "/" + year + " " + hour + ":" + minute;
    }
    return s;
  }
}
