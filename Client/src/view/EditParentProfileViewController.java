package view;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class EditParentProfileViewController extends ViewController
{
  @FXML private TextField firstName;
  @FXML private TextField lastName;
  @FXML private TextField username;
  @FXML private ComboBox<String> familyPets;

  @Override protected void init()
  {

  }

  @Override public void reset()
  {

  }

  public void onSave()
  {
    getViewHandler().openView(View.PARENT_PROFILE_VIEW);
  }

  public void onCancel()
  {
    getViewHandler().openView(View.PARENT_PROFILE_VIEW);
  }
}
