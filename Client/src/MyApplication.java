import mediator.Client;
import model.LocalModel;
import javafx.application.Application;
import javafx.stage.Stage;
import model.LocalModelManager;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

import java.io.IOException;
import java.rmi.NotBoundException;

public class MyApplication extends Application
{
  private LocalModel localModel;

  @Override public void start(Stage stage)
  {

//    Client client = new Client(localModel, "localhost");
    localModel = new LocalModelManager();
    ViewModelFactory viewModelFactory = new ViewModelFactory(localModel);
    ViewHandler view = new ViewHandler(viewModelFactory);
    view.start(stage);
  }

  @Override public void stop(){
    localModel.close();
  }
}
