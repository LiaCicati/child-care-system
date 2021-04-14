package Model;

import java.time.LocalDate;

public class Kid
{
    private int age;
    private boolean gender; //false = boy, true = girl
    private String healthConditions;
    private MyDateTime dateOfBirth;

    public Kid(int birthDay, int birthMonth, int birthYear, boolean gender, String healthConditions){
        this.setDateOfBirth(new MyDateTime(birthDay,birthMonth, birthYear));
        this.gender = gender;
        this.healthConditions = healthConditions;
    }

    public int getAge(MyDateTime dateOfBirth){
        int currentDay = LocalDate.now().getDayOfMonth();
        int currentMonth = LocalDate.now().getMonthValue();
        int currentYear = LocalDate.now().getYear();

        int birthDay = dateOfBirth.getDay();
        int birthMonth = dateOfBirth.getMonth();
        int birthYear = dateOfBirth.getYear();

        int age = currentYear - birthYear;

        int differenceInDays = currentDay - birthDay;
        int differenceInMonths = currentMonth - birthMonth;
        if (differenceInDays < 0){
            differenceInMonths = differenceInMonths -1;
        }
        if (differenceInMonths < 0){
            return age - 1;
        }
        else {
            return age;
        }
    }

    public MyDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(MyDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}






























/*
public class Kid
{
    private int age;
    private boolean gender; //false = boy, true = girl
    private String healthConditions;
    private MyDateTime dateOfBirth;

    public Kid(MyDateTime dateOfBirth, boolean gender, String healthConditions){
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.healthConditions = healthConditions;
    }

    public int getAge(MyDateTime dateOfBirth){
        int day = LocalDate.EPOCH.getDayOfMonth();
        return day;
    }
}*/
