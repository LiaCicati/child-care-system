package viewmodel;

import javafx.beans.property.*;
import model.Kid;
import model.LocalModel;

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
  }

  public void reset()
  {
    addData();
  }

  public void addData()
  {
    System.out.println(viewState.getSelectedChild());
    //    if (viewState.getSelectedChild() > -1) // edit state
    //    {
    //
    //      Kid kid = viewState.getParent().getKidByID(viewState.getSelectedChild());
    ////      Kid kid = model.getAllKids(model.getParentList().getByUserName(viewState.getParent().getUserName()));
    ////      Kid kid = model.getAllKids(model.getParent(viewState.getParent().getUserName()));
    //      id.setValue(kid.getId());
    //      gender.set(kid.getGender());
    //      healthCondition.set(kid.getHealthConditions());
    //      age.setValue(kid.getDateOfBirth());
    //    }
    //    else // add
    //    {
    id.setValue(0);
    age.setValue(LocalDate.now());
    gender.setValue(true);
    healthCondition.set("");
    error.set("");
    //    }

  }

  public boolean onSave()
  {
    try
    {

      Kid kid = new Kid(id.get(), age.get().getDayOfYear(),
          age.get().getMonthValue(), age.get().getYear(), gender.get(),
          healthCondition.get());
      //    if (viewState.getSelectedChild() > -1)
      //    {
      //      model.editKidData(viewState.getParent(), viewState.getSelectedChild(),
      //          kid);
      //    }
      if (age.get().isAfter(LocalDate.now()))
        throw new IllegalArgumentException(
            "Date of birth cannot be a future date.");

      model.addKid(viewState.getParent(), kid);

      return true;
    }
    catch (Exception e)
    {
      error.set(e.getMessage());
      return false;
    }

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

  public StringProperty getError()
  {
    return error;
  }
}
