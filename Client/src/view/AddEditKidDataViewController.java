package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AddEditKidDataViewController extends ViewController
{
  @FXML private DatePicker age;
  @FXML private TextArea healthCondition;
  @FXML private ComboBox<String> gender;
  @FXML private Label errorLabel;

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
