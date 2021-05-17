package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.AccountList;
import model.Kid;
import model.LocalModel;

import java.util.ArrayList;

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
    update();

  }

  public void update()
  {
    kids.clear();
    ArrayList<Kid> list;
    list = model.getKidList();
    for (int i = 0; i < list.size(); i++)
    {
      kids.add(new KidViewModel(list.get(i)));
    }
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
