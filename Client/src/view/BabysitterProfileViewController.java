package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BabysitterProfileViewController extends ViewController
{
  @FXML private Label firstName;
  @FXML private Label lastName;
  @FXML private Label username;
  @FXML private Label age;
  @FXML private Label paymentPerHour;
  @FXML private Label spokenLanguages;
  @FXML private Label firstAidCertificate;

  @Override protected void init()
  {

  }

  @Override public void reset()
  {

  }

  public void onEdit()
  {
    getViewHandler().openView(View.EDIT_BABYSITTER_PROFILE_VIEW);
  }

  public void onHome()
  {

  }

  public void onLogOut()
  {
    getViewHandler().openView(View.ACCOUNT_TYPE_VIEW);
  }
}
