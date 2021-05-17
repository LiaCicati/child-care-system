package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EditParentProfileViewController extends ViewController
{
  @FXML private TextField firstName;
  @FXML private TextField lastName;
  @FXML private TextField username;
  @FXML private RadioButton hasPets;
  @FXML private ToggleGroup groupToggle;
  @FXML private RadioButton doesNotHavePets;
  @FXML private Label errorLabel;

  @Override protected void init()
  {
    hasPets.setToggleGroup(groupToggle);
    doesNotHavePets.setToggleGroup(groupToggle);

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
