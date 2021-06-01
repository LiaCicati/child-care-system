import model.LocalModel;
import javafx.application.Application;
import javafx.stage.Stage;
import model.LocalModelManager;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

public class MyApplication extends Application
{
  private LocalModel localModel;

  @Override public void start(Stage stage)
  {

    localModel = new LocalModelManager();
    ViewModelFactory viewModelFactory = new ViewModelFactory(localModel);
    ViewHandler view = new ViewHandler(viewModelFactory);
    view.start(stage);
  }

  @Override public void stop()
  {
    localModel.close();
  }
}
