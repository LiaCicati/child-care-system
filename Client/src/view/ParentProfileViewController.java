package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import model.Kid;
import viewmodel.KidListViewModel;
import viewmodel.KidViewModel;
import viewmodel.ParentProfileViewModel;
import viewmodel.ViewState;

import java.io.IOException;

public class ParentProfileViewController extends ViewController
{
  private ParentProfileViewModel viewModel;
  @FXML private Label firstName;
  @FXML private Label lastName;
  @FXML private Label username;
  @FXML private Label greetingName;
  @FXML private Label email;
  @FXML private Label hasPets;
  @FXML private Button editParentButton;

  private KidListViewModel kidListViewModel;
  @FXML private TableView<KidViewModel> kidTable;
  @FXML private TableColumn<KidViewModel, Number> idColumn;
  @FXML private TableColumn<KidViewModel, Number> ageColumn;
  @FXML private TableColumn<KidViewModel, String> genderColumn;
  @FXML private TableColumn<KidViewModel, String> healthConditionColumn;

  @FXML private Button editKidButton;

  @FXML private Label errorLabel;
  @FXML private Label nrOfKids;
  @FXML private VBox container;

  @Override protected void init()
  {
    viewModel = getViewModelFactory().getParentProfileViewModel();
    greetingName.textProperty().bind(viewModel.getFirstName());
    firstName.textProperty().bind(viewModel.getFirstName());
    lastName.textProperty().bindBidirectional(viewModel.getLastName());
    username.textProperty().bindBidirectional(viewModel.getUsername());
    email.textProperty().bindBidirectional(viewModel.getEmail());
    //    password.textProperty().bindBidirectional(viewModel.getPassword());
    hasPets.textProperty().bindBidirectional(viewModel.getHasPets());

    editParentButton.setDisable(true);

    // Kid tab
    kidListViewModel = getViewModelFactory().getKidListViewModel();
    ageColumn.setCellValueFactory(cellData -> cellData.getValue().getAge());
    genderColumn
        .setCellValueFactory(cellData -> cellData.getValue().getGender());
    healthConditionColumn.setCellValueFactory(
        cellData -> cellData.getValue().getHealthCondition());
    idColumn.setCellValueFactory(cellData -> cellData.getValue().getId());
    errorLabel.textProperty().bind(kidListViewModel.getError());
    kidTable.setItems(kidListViewModel.getKids());
    reset();
    kidTable.getSelectionModel().selectedItemProperty().addListener(
        (obs, oldValue, newValue) -> kidListViewModel.setSelectedKid(newValue));

    editKidButton.setDisable(true);
    reset();
  }

  @Override public void reset()
  {
    viewModel.reset();
    kidListViewModel.reset();

  }

  public void onHome()
  {
    getViewHandler().openView(View.BOOKING_BABYSITTERS_VIEW);
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
    kidListViewModel.onAdd();
    getViewHandler().openView(View.ADD_EDIT_KID_DATA_VIEW);
  }

  public void onEditKidData()
  {
    if (kidListViewModel.edit())
    {
      getViewHandler().openView(View.ADD_EDIT_KID_DATA_VIEW);
    }
  }
}
