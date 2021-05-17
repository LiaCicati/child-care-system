package viewmodel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Kid;

public class KidViewModel
{
  private IntegerProperty age;
  private StringProperty gender;
  private StringProperty healthCondition;

  public KidViewModel(Kid kid)
  {
    age = new SimpleIntegerProperty(kid.getAge());
    gender = new SimpleStringProperty(kid.getGender() ? "girl" : "boy");
    healthCondition = new SimpleStringProperty(kid.getHealthConditions());
  }

  public IntegerProperty getAge()
  {
    return age;
  }

  public StringProperty getGender()
  {
    return gender;
  }

  public StringProperty getHealthCondition()
  {
    return healthCondition;
  }

}
