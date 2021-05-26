package viewmodel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import model.*;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class BookingBabysitterViewModel
{

  private LocalModel model;
  private StringProperty hour;
  private StringProperty minute;
  private ObjectProperty<LocalDate> date;
  private StringProperty errorLabel;
  private StringProperty durationHours;
  private StringProperty durationMinutes;
  private StringProperty description;

  private ObservableList<BookingBabysitterTableRowData> babysitters;

  private ObservableList<BookingBabysitterTableRowData> bookings;
  ArrayList<Babysitter> bookedBabysitters;
  private ViewState viewState;

  private String errorMessage;

  int selectedYear = 0;
  int selectedMonth = 0;
  int selectedDay = 0;
  int selectedHour = 0;
  int selectedMinute = 0;
  int selectedDurationHour = 0;
  int SelectedDurationMinute = 0;

  MyDateTime startTime = new MyDateTime(0,0,0,0,0);
  MyDateTime endTime = new MyDateTime(0,0,0,0,0);


  public BookingBabysitterViewModel(LocalModel model, ViewState viewState)
  {
    this.model = model;
    this.viewState = viewState;
    hour = new SimpleStringProperty();
    minute = new SimpleStringProperty();
    date = new SimpleObjectProperty<>();
    errorLabel = new SimpleStringProperty("");
    this.babysitters = FXCollections.observableArrayList();
    this.bookings = FXCollections.observableArrayList();
    durationHours = new SimpleStringProperty();
    durationMinutes = new SimpleStringProperty();
    this.bookedBabysitters = new ArrayList<>();
    this.description = new SimpleStringProperty();

  }

  public void reset()
  {
    hour.set("");
    minute.set("");
    date.set(null);
    durationHours.set("");
    durationMinutes.set("");
    description.set("");
    updateBabysitters();
   // hour.set(null);
    errorLabel.set("");
  }

  public void resetLabel(Label label){
    label.setTextFill(Color.web("red"));
    errorLabel.set("");
  }

  public StringProperty getError()
  {
    return errorLabel;
  }

  public void date() throws RemoteException {
    try {

      LocalDate selectedDate = getDate().get();

      String selectedDateString = String.valueOf(selectedDate);
      System.out.println("the string date: "+selectedDateString);

      selectedYear = Integer.parseInt(selectedDateString.substring(0, 4));
      System.out.println("year: "+selectedYear);

      selectedMonth = Integer.parseInt(selectedDateString.substring(5, 7));
      System.out.println("month: "+ selectedMonth );
      selectedDay = Integer.parseInt(selectedDateString.substring(8, 10));
      System.out.println("day; " + selectedDay);
    }
    catch (Exception e) {
      errorLabel.set("unintended " + e.getMessage());
    }
  }

  public void hour() throws RemoteException {
    try {
      selectedHour = Integer.parseInt(hour.getValue());
    }
    catch (Exception e) {
      errorLabel.set("unintended " + e.getMessage());
    }
  }

  public void minute() throws RemoteException {
    try {
      selectedMinute = Integer.parseInt(minute.getValue());
    }
    catch (Exception e) {
      errorLabel.set("unintended " + e.getMessage());
    }
  }

  public void durationHour() throws RemoteException {
    try {

      selectedDurationHour = Integer.parseInt(durationHours.getValue());
    }
    catch (Exception e) {
      errorLabel.set("unintended " + e.getMessage());
    }
  }

  public void durationMinute() throws RemoteException {
    try {
      SelectedDurationMinute = Integer.parseInt(durationMinutes.getValue());
    }
    catch (Exception e) {
      errorLabel.set("unintended " + e.getMessage());
    }
  }

  public MyDateTime getStartTime(){

    startTime.set(selectedDay, selectedMonth ,selectedYear , selectedHour , selectedMinute);
    return startTime;
  }

  public MyDateTime getEndTime(){

    endTime.set(selectedDay, selectedMonth, selectedYear, selectedHour + selectedDurationHour, (selectedMinute + SelectedDurationMinute));
    return endTime;
  }

  public StringProperty getHour()
  {
    return hour;
  }

  public StringProperty getMinute()
  {
    return minute;
  }

  public ObjectProperty<LocalDate> getDate()
  {
    return date;
  }

  public ObservableList<BookingBabysitterTableRowData> getBabysitters()
  {
    return babysitters;
  }

  public StringProperty getDescription()
  {
    return description;
  }

  public void setBabysitters(
      ObservableList<BookingBabysitterTableRowData> babysitters)
  {
    this.babysitters = babysitters;
  }

  public void createBooking(String babysitter, Label label){
    try {
      int thisDay = LocalDate.now().getDayOfMonth();
      int thisMonth = LocalDate.now().getMonthValue();
      int thisYear = LocalDate.now().getYear();
      int thisHour = LocalTime.now().getHour();
      int thisMinute = LocalTime.now().getMinute();
      MyDateTime today = new MyDateTime(thisDay, thisMonth, thisYear);
      getEndTime();
      getStartTime();
      if (selectedYear == 0&&selectedMonth == 0&& selectedDay == 0&&selectedHour == 0&&selectedMinute == 0&& selectedDurationHour == 0&&SelectedDurationMinute == 0) {
        errorMessage = "Please specify information about booking";
        errorLabel.set(errorMessage);
      }else if (endTime.equals(startTime)){
          errorMessage = "Please select for how long you want you child babysat";
          errorLabel.set(errorMessage);
      }else if (startTime.equals(new MyDateTime(0,0,0,0,0))){
          errorMessage = "Please specify when you need a babysitter";
          errorLabel.set(errorMessage);
      } else if (new MyDateTime(startTime.getDay(), startTime.getMonth(), startTime.getYear()).isBeforeDate(today)) {
        errorMessage = "Selected date must be in the future";
        errorLabel.set(errorMessage);
        System.out.println("this: "+ new MyDateTime(startTime.getDay(), startTime.getMonth(), startTime.getYear())+" is before this: "+today+" : "+new MyDateTime(startTime.getDay(), startTime.getMonth(), startTime.getYear()).isBeforeDate(today));
      } else if (new MyDateTime(startTime.getDay(), startTime.getMonth(), startTime.getYear()).equals(today) && selectedHour < thisHour ==true) {
        errorMessage = "Selected time must be in the future";
        errorLabel.set(errorMessage);
      }else if (new MyDateTime(startTime.getDay(), startTime.getMonth(), startTime.getYear()).equals(today) && (selectedHour == thisHour) && selectedMinute<thisMinute==true) {
        errorMessage = "Selected time must be in the future";
        errorLabel.set(errorMessage);
      }else if (selectedDay == 0){
          errorMessage = "Please select date you need a babysitter";
          errorLabel.set(errorMessage);
      } else if (selectedHour == 0){
          errorMessage = "Please select time you need a babysitter (hour)";
          errorLabel.set(errorMessage);
      }else if (model.getBabysitter(babysitter) == null){
          errorMessage = "Please pick a babysitter";
          errorLabel.set(errorMessage);
      }else if (getStartTime().getTime()!=0){
//        reset();
        Booking myBooking = new Booking(new TimeInterval(getStartTime(), getEndTime()),viewState.getParent() , model.getBabysitter(babysitter), getDescription().get());
        model.addBooking(myBooking);
        
        errorMessage = "Booking of babysitter completed!";
        label.setTextFill(Color.web("black"));
        errorLabel.set(errorMessage);
      }
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }


  public void getBookedBabysitters() {
    bookedBabysitters.clear();
    ArrayList<Booking> bookingList;
//    bookings.clear();
    bookingList = model.getBookingList().getAllBookings();
    System.out.println(bookings);
    if (selectedHour==0){

    }else {
      for (int i = 0; i < bookingList.size(); i++) {
        MyDateTime startOfBooking = bookingList.get(i).getTime().getStartTime();
        MyDateTime endOfBooking = bookingList.get(i).getTime().getEndTime();
        System.out.println(endOfBooking);
        endOfBooking.setMinute(endOfBooking.getMinute()+30);
        System.out.println(endOfBooking);
        if ((startOfBooking.isAfterDate(getStartTime()) == false && startOfBooking.isBeforeDate(getStartTime()) == false)
                && (getStartTime().isBeforeDateTime(startOfBooking)==true && getEndTime().isAfterDateTime(endOfBooking)==true)) {
          bookedBabysitters.add(bookingList.get(i).getBabysitter());
        } else if ((startOfBooking.isAfterDate(getStartTime()) == false && startOfBooking.isBeforeDate(getStartTime()) == false)
                && (getStartTime().isBeforeDateTime(startOfBooking)==true
                && (getEndTime().isBeforeDateTime(endOfBooking)==true&&getEndTime().isAfterDateTime(startOfBooking)==true))) {
          bookedBabysitters.add(bookingList.get(i).getBabysitter());
        } else if ((startOfBooking.isAfterDate(getStartTime()) == false && startOfBooking.isBeforeDate(getStartTime()) == false)
                && ((getStartTime().isAfterDateTime(startOfBooking)==true && getStartTime().isBeforeDateTime(endOfBooking)==true)
                && (getEndTime().isBeforeDateTime(endOfBooking)==true&&getEndTime().isAfterDateTime(startOfBooking)==true))) {
          bookedBabysitters.add(bookingList.get(i).getBabysitter());
        } else if ((startOfBooking.isAfterDate(getStartTime()) == false && startOfBooking.isBeforeDate(getStartTime()) == false)
                && ((getStartTime().isAfterDateTime(startOfBooking)==true&& getStartTime().isBeforeDateTime(endOfBooking)==true)
                && getEndTime().isAfterDateTime(endOfBooking)==true)) {
          bookedBabysitters.add(bookingList.get(i).getBabysitter());
        }
      }
    }
    updateBabysitters();
  }


  public void updateBabysitters() {
    babysitters.clear();
    for (int i = 0; i < model.getBabysitterList().getNumberOfAccounts(); i++) {
      if (bookedBabysitters.size()>0) {
        for (Babysitter bookedBabysitter : bookedBabysitters) {
          if (!model.getBabysitterList().getAllBabysitterAccounts().get(i).equals(bookedBabysitter))
            babysitters.add(new BookingBabysitterTableRowData(
                    model.getBabysitterList().getAllBabysitterAccounts().get(i)));

        }
      }else{
        babysitters.add(new BookingBabysitterTableRowData(
                model.getBabysitterList().getAllBabysitterAccounts().get(i)));
      }
    }
  }

  public StringProperty getDurationHours() {
    return durationHours;
  }

  public StringProperty getDurationMinutes() {
    return durationMinutes;
  }
}

