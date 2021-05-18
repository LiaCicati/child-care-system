package viewmodel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.rmi.RemoteException;
import java.time.LocalDate;

public class BookingBabysitterViewModel
{

  private LocalModel model;
  private StringProperty hour;
  private StringProperty minute;
  private ObjectProperty<LocalDate> date;
  private StringProperty errorLabel;
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

  public void date() throws RemoteException
  {
    try
    {
      if ((selectedHour == 0) && (selectedMinute == 0))
      {
        errorMessage += "Select time you need a babysitter";
        errorLabel.set(errorMessage);
        System.out.println("date første if");
      }
      else if (selectedHour == 0)
      {
        errorMessage += "Select time you need a babysitter (hour)";
        errorLabel.set(errorMessage);
        System.out.println("date anden if ");
      }
      else if (selectedMinute == 0)
      {
        errorMessage += "Select time you need a babysitter (minute)";
        errorLabel.set(errorMessage);
        System.out.println("date tredje if");
      }
      selectedYear = Integer.parseInt(errorMessage.substring(4, 8));
      selectedMonth = Integer.parseInt(errorMessage.substring(9, 11));
      selectedDay = Integer.parseInt(errorMessage.substring(12, 14));
      System.out.println(errorMessage);
      System.out
          .println(selectedYear + " " + selectedMonth + " " + selectedDay);
    }
    catch (Exception e)
    {
      errorLabel.set("unintended " + e.getMessage());
      System.out.println("date exception");
    }
  }

  public void hour() throws RemoteException
  {
    try
    {
      if (selectedDay == 0)
      {
        errorMessage += "Select date your need a babysitter";
        errorLabel.set(errorMessage);
        System.out.println("vælg dag");
      }
      errorMessage = hour.getValue();
      selectedHour = Integer.parseInt(errorMessage);
      System.out.println(getHour());
      System.out.println(selectedHour);
    }
    catch (Exception e)
    {
      errorLabel.set("unintended " + e.getMessage());
    }
  }

  public void minute() throws RemoteException
  {
    try
    {
      if (selectedHour == 0)
      {
        errorMessage += "Select time you need a babysitter (hour)";
        errorLabel.set(errorMessage);
      }
      selectedMinute = Integer.parseInt(errorMessage);
      System.out.println(minute.getValue());
      System.out.println(selectedMinute);
    }
    catch (Exception e)
    {
      errorLabel.set("unintended " + e.getMessage());
    }
  }

  public void findAvailableBabysitters() throws RemoteException
  {
    if (getDate() == null)
    {
      errorLabel.set("Select a date");
    }
    else if (getHour().equals(null) || getHour() == null)
    {
      errorLabel.set("Select a time (hour)");
    }
    else if (getMinute() == null)
    {
      errorLabel.set("Select a time (minute)");
    }
    try
    {

    }
    catch (Exception e)
    {
      errorLabel.set(e.getMessage());
    }
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

  public void createBooking(){
    // model.addBooking(new Booking(new TimeInterval(new MyDateTime(24,9,2021,15,30),new MyDateTime(24,9,2021,18,00)),model.getParent("lina"), model.getBabysitter("lori")));
    System.out.println(model.getBabysitter("lori"));
    System.out.println(model.getParent("ana"));
  }

  public void updateBabysitters() {
    babysitters.clear();
    for (int i = 0; i < model.getBabysitterList().getNumberOfAccounts(); i++)
    {
      babysitters.add(new BookingBabysitterTableRowData(
          model.getBabysitterList().getAllBabysitterAccounts().get(i)));
    }

  }
}

