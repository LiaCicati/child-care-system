package model;

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

    public void set(int day, int month, int year, int hour, int minute)
    {
        if (year < 0)
        {
            year = -year;
        }
        if (month < 1)
        {
            month = 1;
        }
        else if (month > 12)
        {
            month = 12;
        }
        this.year = year;
        this.month = month;
        if (day < 1)
        {
            day = 1;
        }
        else if (day > numberOfDaysInMonth())
        {
            day = numberOfDaysInMonth();
        }
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    /**
     * A boolean method checking if it's a leap year
     *
     * @return true if it is a leap year and false if not
     */
    public boolean isLeapYear()
    {
        return ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0));
    }

    /**
     * Getting the number of days in a month
     *
     * @return the number of days
     */
    public int numberOfDaysInMonth()
    {
        switch (month)
        {
            case 2:
                if (isLeapYear())
                {
                    return 29;
                }
                return 28;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            default:
                return 31;
        }
    }

    /**
     * Getting the name of the month
     *
     * @return the month name
     */
    public String getMonthName()
    {
        switch (month)
        {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
            default:
                return "Error";
        }
    }

    /**
     * Getting the month number
     *
     * @param monthName the month name
     * @return the month number
     */
    public int convertToMonthNumber(String monthName)
    {
        switch (monthName)
        {
            case "January":
                return 1;
            case "February":
                return 2;
            case "March":
                return 3;
            case "April":
                return 4;
            case "May":
                return 5;
            case "June":
                return 6;
            case "July":
                return 7;
            case "August":
                return 8;
            case "September":
                return 9;
            case "October":
                return 10;
            case "November":
                return 11;
            case "December":
                return 12;
            default:
                return 0;
        }
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
