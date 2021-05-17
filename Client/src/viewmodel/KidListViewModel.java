package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.LocalModel;

public class KidListViewModel
{
  private ObservableList<KidViewModel> kids;
  private LocalModel model;
  private ViewState viewState;
  private StringProperty error;

  public KidListViewModel(LocalModel model, ViewState viewState)
  {
    this.model = model;
    this.viewState = viewState;
    this.kids = FXCollections.observableArrayList();
    this.error = new SimpleStringProperty();
  }

  public void reset()
  {
    error.set("");
    kids.clear();

  }

  public ObservableList<KidViewModel> getKids()
  {
    return kids;
  }

  public StringProperty getError()
  {
    return error;
  }

}
