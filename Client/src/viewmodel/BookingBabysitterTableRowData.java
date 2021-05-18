package viewmodel;

import javafx.beans.property.*;
import model.*;

public class BookingBabysitterTableRowData {
    private StringProperty name;
    private IntegerProperty age;
    private DoubleProperty babysittingExperience;
    private DoubleProperty paymentPerHour;
    private StringProperty userName;

    private LocalModel model;

    public BookingBabysitterTableRowData(Babysitter babysitter) {
    name = new SimpleStringProperty(babysitter.getFirstName());
    age = new SimpleIntegerProperty(babysitter.getAge());
    babysittingExperience = new SimpleDoubleProperty(babysitter.getBabysittingExperience());
    paymentPerHour = new SimpleDoubleProperty(babysitter.getPaymentPerHour());
    userName = new SimpleStringProperty(babysitter.getUserName());

    }

    public StringProperty getName() {
        return name;
    }

    public void setName(StringProperty name) {
        this.name = name;
    }

    public IntegerProperty getAge() {
        return age;
    }

    public void setAge(SimpleIntegerProperty age) {
        this.age = age;
    }

    public DoubleProperty getBabysittingExperience() {
        return babysittingExperience;
    }

    public void setBabysittingExperience(DoubleProperty babysittingExperience) {
        this.babysittingExperience = babysittingExperience;
    }

    public DoubleProperty getPaymentPerHour() {
        return paymentPerHour;
    }

    public void setPaymentPerHour(DoubleProperty paymentPerHour) {
        this.paymentPerHour = paymentPerHour;
    }


    public void allBabysitters(){
     AccountList newList = model.getBabysitterList();
    }

    public void allBookings(){
        model.getBookingList();
    }


    public String toString()
    {
        return name.get() + " " + age.get() + " " + babysittingExperience.get() + " " + paymentPerHour.get() +" " + userName.get();
    }
}
