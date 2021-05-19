package viewmodel;

import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

  private String errorMessage;

  int selectedYear = 0;
  int selectedMonth = 0;
  int selectedDay = 0;
  int selectedHour = 0;
  int selectedMinute = 0;

  public BookingBabysitterViewModel(LocalModel model)
  {
    this.model = model;
    hour = new SimpleStringProperty();
    minute = new SimpleStringProperty();
    date = new SimpleObjectProperty<>();
    errorLabel = new SimpleStringProperty("");
    this.babysitters = FXCollections.observableArrayList();
    durationHours = new SimpleStringProperty();
    durationMinutes = new SimpleStringProperty();
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
      System.out.println(selectedDay+" "+selectedMonth+" "+selectedYear);
    }
    catch (Exception e) {
      errorLabel.set("unintended " + e.getMessage());
      System.out.println("date exception");
    }
  }

  public void hour() throws RemoteException {
    try {

      selectedHour = Integer.parseInt(hour.getValue());
      System.out.println(selectedHour);
    }
    catch (Exception e) {
      errorLabel.set("unintended " + e.getMessage());
    }
  }

  public void minute() throws RemoteException {
    try {
      selectedMinute = Integer.parseInt(minute.getValue());
      System.out.println(selectedMinute);
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

  public void createBooking(String babysitter){
    try {
      if (model.getBabysitter(babysitter) == null){
        errorMessage = "Please pick a babysitter";
        errorLabel.set(errorMessage);
      }else if (getStartTime().getHour()==0) {
        System.out.println("vælg tid");
      }else if (getStartTime().getTime()!=0){
        reset();
        Booking myBooking = new Booking(new TimeInterval(getStartTime(), new MyDateTime(24, 9, 2021, 18, 00)), model.getLoggedInParent(), model.getBabysitter(babysitter));
        model.addBooking(myBooking);
        System.out.println(myBooking);
      }
      System.out.println(model.getLoggedInParent());
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }

  public void updateBabysitters() {
    babysitters.clear();
    for (int i = 0; i < model.getBabysitterList().getNumberOfAccounts(); i++)
    {
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

