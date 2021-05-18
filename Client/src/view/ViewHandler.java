package view;

import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewmodel.ViewModelFactory;

public class ViewHandler extends ViewCreator
{
  private Stage primaryStage;
  private Scene currentScene;
  private ViewModelFactory viewModelFactory;

  public ViewHandler(ViewModelFactory viewModelFactory)
  {
    super();
    this.viewModelFactory = viewModelFactory;

  }

  public void start(Stage primaryStage)
  {
    this.primaryStage = primaryStage;
    this.currentScene = new Scene(new Region());
  //  openView(View.ACCOUNT_TYPE_VIEW);

    openView(View.BOOKING_BABYSITTERS_VIEW);
  }

  public void openView(View view)
  {
    ViewController viewController = getViewController(view);
    Region root = viewController.getRoot();

    currentScene.setRoot(root);

    String title = "";
    if (root.getUserData() != null)
    {
      title += root.getUserData();
    }
    primaryStage.setTitle(title);
    primaryStage.setScene(currentScene);
    primaryStage.setMinWidth(414);
    primaryStage.setMinHeight(750);
    primaryStage.show();
  }

  public void closeView()
  {
    primaryStage.close();
  }

  @Override protected void initViewController(ViewController controller,
      Region root)
  {
    controller.init(this, viewModelFactory, root);
  }
}
