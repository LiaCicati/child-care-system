package viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.AccountList;
import model.Kid;
import model.LocalModel;
import model.Parent;
import view.View;

import java.util.ArrayList;

public class KidListViewModel
{
  private ObservableList<KidViewModel> kids;
  private ObjectProperty<KidViewModel> selectedKid;
  private LocalModel model;
  private ViewState viewState;
  private StringProperty error;

  public KidListViewModel(LocalModel model, ViewState viewState)
  {
    this.model = model;
    this.viewState = viewState;
    this.kids = FXCollections.observableArrayList();
    this.selectedKid = new SimpleObjectProperty<>();
    this.error = new SimpleStringProperty();
  }

  public void reset()
  {

    error.set("");
    //    kids.clear();
    update();

  }

  public void onAdd()
  {
    viewState.removeSelectedKid();
    //  if(selectedKid.get() != null) {
    //    viewState.setSelectedKid(viewState.getKid());
    //  }
  }

  public void update()
  {
    kids.clear();
    for (int i = 0; i < model.getAllKids((Parent) model.getParentList()
        .getByUserName(viewState.getParent().getUserName())).size(); i++)
    {
      kids.add(new KidViewModel(model.getAllKids((Parent) model.getParentList()
          .getByUserName(viewState.getParent().getUserName())).get(i)));
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

  public void setSelectedKid(KidViewModel selectedKid)
  {
    this.selectedKid.set(selectedKid);
  }

  public boolean edit()
  {

    if (selectedKid.get() != null)
    {
      viewState.setSelectedChild(selectedKid.get().getId().get());
      return true;
    }
    else
    {
      viewState.removeSelectedKid();
      error.set("Please select a kid first");
      return false;
    }
  }
}
