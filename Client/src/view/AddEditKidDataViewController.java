package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AddEditKidDataViewController extends ViewController
{
  @FXML private TextField id;
  @FXML private DatePicker age;
  @FXML private TextArea healthCondition;
  @FXML private RadioButton girl;
  @FXML private RadioButton boy;
  @FXML private ToggleGroup groupToggle;
  @FXML private Label errorLabel;

  @Override protected void init()
  {
    girl.setToggleGroup(groupToggle);
    boy.setToggleGroup(groupToggle);
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
