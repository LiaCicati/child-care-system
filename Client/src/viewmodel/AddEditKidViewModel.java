package viewmodel;

import javafx.beans.property.*;
import model.Kid;
import model.LocalModel;
import model.Parent;
import view.View;

import java.time.LocalDate;

public class AddEditKidViewModel
{
  private LocalModel model;
  private ViewState viewState;
  private IntegerProperty id;
  private ObjectProperty<LocalDate> age;
  private BooleanProperty gender;
  private StringProperty healthCondition;
  private StringProperty error;

  public AddEditKidViewModel(LocalModel model, ViewState viewState)
  {
    this.model = model;
    this.viewState = viewState;
    this.id = new SimpleIntegerProperty();
    this.age = new SimpleObjectProperty<>();
    this.gender = new SimpleBooleanProperty();
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
      //      age.setValue(viewState.getSelectedKid().getAge());
      gender.set(viewState.getSelectedKid().getGender());
      healthCondition.set(viewState.getSelectedKid().getHealthConditions());
    }
    else
    {
      id.setValue(0);
      age.setValue(null);
      gender.setValue(null);
      healthCondition.set("");
    }

  }

  public void onSave()
  {
    Kid kid = new Kid(id.get(), age.get().getDayOfYear(),
        age.get().getMonthValue(), age.get().getYear(), gender.get(),
        healthCondition.get());
    model.addKid(kid);
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

  public ObjectProperty<LocalDate> getAge()
  {
    return age;
  }

  public BooleanProperty getGender()
  {
    return gender;
  }

  public StringProperty getHealthCondition()
  {
    return healthCondition;
  }
}
