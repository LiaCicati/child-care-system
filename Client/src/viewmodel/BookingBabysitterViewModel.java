package viewmodel;

import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import model.*;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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


  private ObservableList<BookingBabysitterTableRowData> babysitters;

  private ObservableList<BookingBabysitterTableRowData> bookings;
  ArrayList<Babysitter> bookedBabysitters;


  private String errorMessage;

  int selectedYear = 0;
  int selectedMonth = 0;
  int selectedDay = 0;
  int selectedHour = 0;
  int selectedMinute = 0;
  int selectedDurationHour = 0;
  int getSelectedDurationMinute = 0;


  public BookingBabysitterViewModel(LocalModel model)
  {
    this.model = model;
    hour = new SimpleStringProperty();
    minute = new SimpleStringProperty();
    date = new SimpleObjectProperty<>();
    errorLabel = new SimpleStringProperty("");
    this.babysitters = FXCollections.observableArrayList();
    this.bookings = FXCollections.observableArrayList();
    durationHours = new SimpleStringProperty();
    durationMinutes = new SimpleStringProperty();
    this.bookedBabysitters = new ArrayList<>();
    reset();

  }

  public void reset()
  {
    updateBabysitters();
    hour.set(null);
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

      selectedYear = Integer.parseInt(selectedDateString.substring(0, 4));
      selectedMonth = Integer.parseInt(selectedDateString.substring(5, 7));
      selectedDay = Integer.parseInt(selectedDateString.substring(8, 10));
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
      getSelectedDurationMinute = Integer.parseInt(durationMinutes.getValue());
    }
    catch (Exception e) {
      errorLabel.set("unintended " + e.getMessage());
    }
  }

  public MyDateTime getStartTime(){
    if ((selectedDay == 0)&&(selectedDay == 0)&&(selectedMinute == 0)){
      errorMessage = "Please specify when you need a babysitter";
      errorLabel.set(errorMessage);
    }else if (selectedDay == 0){
      errorMessage = "Please select date you need a babysitter";
      errorLabel.set(errorMessage);
    } else if (selectedHour == 0){
      errorMessage = "Please select time you need a babysitter (hour)";
      errorLabel.set(errorMessage);
    }else if (selectedMinute == 0){
      errorMessage = "Please select time you need a babysitter (minute)";
      errorLabel.set(errorMessage);
    }
    MyDateTime startTime = new MyDateTime(selectedDay, selectedMonth ,selectedYear , selectedHour , selectedMinute);
    return startTime;
  }

  public MyDateTime getEndTime(){
    if (selectedDurationHour==0){
      errorMessage = "Please select for how log you want you child babysat";
      errorLabel.set(errorMessage);
    }else if (getSelectedDurationMinute==0){
      errorMessage = "Please select for how long you want you child babysat";
      errorLabel.set(errorMessage);
    }
    MyDateTime endTime = new MyDateTime(selectedDay,selectedMonth,selectedYear,selectedHour+selectedDurationHour,selectedMinute+getSelectedDurationMinute);
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

  public void setBabysitters(
      ObservableList<BookingBabysitterTableRowData> babysitters)
  {
    this.babysitters = babysitters;
  }

  public void createBooking(String babysitter, Label label){
    try {
      if (model.getBabysitter(babysitter) == null){
        errorMessage = "Please pick a babysitter";
        errorLabel.set(errorMessage);
      }else if (getStartTime().getTime()!=0){
        reset();
        Booking myBooking = new Booking(new TimeInterval(getStartTime(), getEndTime()), model.getLoggedInParent(), model.getBabysitter(babysitter));
        model.addBooking(myBooking);
        errorMessage = "Booking of babysitter completed!";
        label.setTextFill(Color.web("black"));
        errorLabel.set(errorMessage);
      }
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }


  public void getBookedBabysitters(){
    bookedBabysitters.clear();
    ArrayList<Booking> bookings = new ArrayList<>();
    bookings.clear();
    bookings = model.getBookingList().getAllBookings();
    System.out.println("fra bookings: "+ bookings);
    for (int i=0; i<bookings.size();i++) {
      MyDateTime startOfBooking = bookings.get(i).getTime().getStartTime();
      System.out.println("det her er start of booking: " + startOfBooking + " det her er start på den ønskede booking: "+ getStartTime());
      System.out.println("er før? "+startOfBooking.isBeforeDate(getStartTime()) + " er efter? "+startOfBooking.isAfterDate(getStartTime()));
      MyDateTime endOfBooking = bookings.get(i).getTime().getEndTime();
      System.out.println("det her er end of booking: " + endOfBooking + " det her er end på den ønskede booking: "+ getEndTime());
      if ((startOfBooking.isAfterDate(getStartTime())==false && startOfBooking.isBeforeDate(getStartTime())==false)
              && (getStartTime().isAfterDateTime(startOfBooking) && getEndTime().isBeforeDateTime(endOfBooking))) {
        bookedBabysitters.add(bookings.get(i).getBabysitter());
        System.out.println(1);
      } else if ((startOfBooking.isAfterDate(getStartTime())==false && startOfBooking.isBeforeDate(getStartTime())==false)
              && (getStartTime().isBeforeDateTime(endOfBooking) && getEndTime().isAfterDateTime(endOfBooking))) {
        bookedBabysitters.add(bookings.get(i).getBabysitter());
        System.out.println(2);
      } else if ((startOfBooking.isAfterDate(getStartTime())==false && startOfBooking.isBeforeDate(getStartTime())==false)
              && (getStartTime().isBeforeDateTime(endOfBooking) && getEndTime().isBeforeDateTime(endOfBooking))) {
        bookedBabysitters.add(bookings.get(i).getBabysitter());
        System.out.println(3);
      } else if ((startOfBooking.isAfterDate(getStartTime())==false && startOfBooking.isBeforeDate(getStartTime())==false)
              && (getStartTime().isBeforeDateTime(endOfBooking) && getEndTime().isAfterDateTime(endOfBooking))) {
        bookedBabysitters.add(bookings.get(i).getBabysitter());
        System.out.println(4);
      } else if ((startOfBooking.isAfterDate(getStartTime())==false && startOfBooking.isBeforeDate(getStartTime())==false)
              && (getStartTime().isBeforeDateTime(startOfBooking) && getEndTime().isAfterDateTime(startOfBooking))) {
        bookedBabysitters.add(bookings.get(i).getBabysitter());
        System.out.println(5);
      } else if ((startOfBooking.isAfterDate(getStartTime())==false && startOfBooking.isBeforeDate(getStartTime())==false)
              && (getStartTime().isBeforeDateTime(startOfBooking) && getEndTime().isAfterDateTime(endOfBooking))) {
        bookedBabysitters.add(bookings.get(i).getBabysitter());
        System.out.println(6);
      }
    }
    System.out.println("fra booked babysitters: " + bookedBabysitters);

   /* System.out.println("is the requested booking before the end of booking? " + getStartTime().isBefore(endOfBooking));
    System.out.println("is the requested booking after the start of booking?" + getStartTime().isAfter(startOfBooking));
    System.out.println(bookings);
    System.out.println("\n"  + bookings.size());
    System.out.println("the time im getting; " + bookings.get(0).getTime().getStartTime());
    System.out.println("the time im getting; " + bookings.get(0).getTime().getEndTime());*/





  }

  public void updateBabysitters() {
    babysitters.clear();
    for (int i = 0; i < model.getBabysitterList().getNumberOfAccounts(); i++) {
      babysitters.add(new BookingBabysitterTableRowData(
          model.getBabysitterList().getAllBabysitterAccounts().get(i)));
    }

  }

  public StringProperty getDurationHours() {
    return durationHours;
  }

  public StringProperty getDurationMinutes() {
    return durationMinutes;
  }
}

