package viewmodel;

import javafx.beans.property.*;
import model.*;

public class BookingBabysitterTableRowData {
    private StringProperty name;
    private IntegerProperty age;
    private DoubleProperty babysittingExperience;
    private DoubleProperty paymentPerHour;

    private LocalModel model;

    public BookingBabysitterTableRowData(Babysitter babysitter)
    {
        babysitter = new Babysitter("superNanny", "password",
                "email@babysitter.com", "Lulu", "Lul", new MyDateTime(9,10,1996),
         125.5,  "danish", 2.3, true);
        String babysitterName = null;
        Integer babysitterAge = null;
        Double babysitterBabysittingExperience = null;
        Double babysitterPaymentPerHour = null;
        if (babysitter != null)
        {
            babysitterName = babysitter.getFirstName();
            babysitterAge = babysitter.getAge();
            babysitterBabysittingExperience = babysitter.getBabysittingExperience();
            babysitterPaymentPerHour = babysitter.getPaymentPerHour();

        }

        setName(new SimpleStringProperty(babysitterName));
        setAge(new SimpleIntegerProperty(babysitterAge));
        setBabysittingExperience(new SimpleDoubleProperty(babysitterBabysittingExperience));
        setPaymentPerHour(new SimpleDoubleProperty(babysitterPaymentPerHour));


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


}
