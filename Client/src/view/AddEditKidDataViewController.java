package view;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import viewmodel.AddEditKidViewModel;
import viewmodel.DoubleStringConverter;
import viewmodel.StringIntegerConverter;

public class AddEditKidDataViewController extends ViewController
{
  @FXML private TextField id;
  @FXML private DatePicker age;
  @FXML private TextArea healthCondition;
  @FXML private RadioButton girl;
  @FXML private RadioButton boy;
  @FXML private ToggleGroup groupToggle;
  @FXML private Label errorLabel;

  private AddEditKidViewModel viewModel;

  @Override protected void init()
  {

    viewModel = getViewModelFactory().getAddEditKidViewModel();
    girl.setToggleGroup(groupToggle);
    boy.setToggleGroup(groupToggle);
    Bindings.bindBidirectional(id.textProperty(), viewModel.getId(),
        new StringIntegerConverter(0));
    age.valueProperty().bindBidirectional(viewModel.getAge());
    healthCondition.textProperty()
        .bindBidirectional(viewModel.getHealthCondition());
    errorLabel.textProperty().bind(viewModel.getError());
    reset();
  }

  @Override public void reset()
  {
    viewModel.reset();

  }

  public void onSave()
  {
    viewModel.onSave();
    getViewHandler().openView(View.PARENT_PROFILE_VIEW);
  }

  public void onCancel()
  {
    getViewHandler().openView(View.PARENT_PROFILE_VIEW);
  }

}
