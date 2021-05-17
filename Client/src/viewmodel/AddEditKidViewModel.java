package viewmodel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Kid;
import model.LocalModel;
import model.Parent;
import view.View;

public class AddEditKidViewModel
{
  private LocalModel model;
  private ViewState viewState;
  private IntegerProperty id;
  private IntegerProperty age;
  private StringProperty gender;
  private StringProperty healthCondition;
  private StringProperty error;

  public AddEditKidViewModel(LocalModel model, ViewState viewState)
  {
    this.model = model;
    this.viewState = viewState;
    this.id = new SimpleIntegerProperty();
    this.age = new SimpleIntegerProperty();
    this.gender = new SimpleStringProperty();
    this.healthCondition = new SimpleStringProperty();
    this.error = new SimpleStringProperty();
    reset();
  }

  public void reset()
  {
    error.set("");
    addData();
  }

  public void addData()
  {
    if (viewState.getSelectedChild() > -1)
    {
      id.setValue(viewState.getSelectedKid().getId());
      age.setValue(viewState.getSelectedKid().getAge());
      gender.set(viewState.getSelectedKid().getGender() + "");
      healthCondition.set(viewState.getSelectedKid().getHealthConditions());
    }

  }

  public void editData()
  {
    //    error.set("");
    //    try {
    //      Kid kid = model.editKidData(viewState.getParent().getUserName(), );
    //    }
  }

  public IntegerProperty getId()
  {
    return id;
  }

  public IntegerProperty getAge()
  {
    return age;
  }

  public StringProperty getGender()
  {
    return gender;
  }

  private StringProperty getHealthCondition()
  {
    return healthCondition;
  }
}
