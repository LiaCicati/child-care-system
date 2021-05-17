package viewmodel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Kid;
import model.LocalModel;
import view.View;

public class AddEditKidViewModel
{
  private LocalModel model;
  private ViewState viewState;
  private IntegerProperty age;
  private StringProperty gender;
  private StringProperty healthCondition;
  private StringProperty error;

  public AddEditKidViewModel(LocalModel model, ViewState viewState)
  {
    this.model = model;
    this.viewState = viewState;
    this.age = new SimpleIntegerProperty();
    this.gender = new SimpleStringProperty();
    this.healthCondition = new SimpleStringProperty();
    this.error = new SimpleStringProperty();
  }

  public void reset()
  {
    error.set("");
  }

  public void editData()
  {
    error.set("");
//    try {
////      Kid kid =
//    }
  }
}
