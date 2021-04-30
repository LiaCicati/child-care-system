package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ParentProfileViewController extends ViewController
{
  @FXML private Label firstName;
  @FXML private Label lastName;
  @FXML private Label username;
  @FXML private Label hasPets;
  @FXML private Label nrOfKids;
  @FXML private VBox container;

  @Override protected void init()
  {

  }

  @Override public void reset()
  {

  }


  public void onHome()
  {
  }

  public void onLogOut()
  {
    getViewHandler().openView(View.ACCOUNT_TYPE_VIEW);
  }


  public void onEditParentData()
  {
    getViewHandler().openView(View.EDIT_PARENT_DATA_VIEW);

  }

  public void onAddKidData()
  {
    getViewHandler().openView(View.EDIT_KID_DATA_VIEW);
  }

  public void onEditKidData()
  {
    getViewHandler().openView(View.EDIT_KID_DATA_VIEW);
  }
}
