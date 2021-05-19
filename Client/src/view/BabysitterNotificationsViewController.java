package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class BabysitterNotificationsViewController extends ViewController
{
  @FXML private ListView<String> list;

  @Override protected void init()
  {

  }

  @Override public void reset()
  {

  }

  public void onProfile()
  {
    getViewHandler().openView(View.BABYSITTER_PROFILE_VIEW);
  }

  public void onAccept()
  {
  }

  public void onReject()
  {
  }
}
