package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import viewmodel.ParentProfileViewModel;

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
    reset();
  }

  @Override public void reset()
  {
    viewModel.reset();
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
    getViewHandler().openView(View.ADD_EDIT_KID_DATA_VIEW);
  }

  public void onEditKidData()
  {
    getViewHandler().openView(View.ADD_EDIT_KID_DATA_VIEW);
  }
}
