import Mediator.RemoteModel;
import Mediator.Server;
import Model.Model;
import Model.ModelManager;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class MyApplication extends Application
{
  private RemoteModel server;

  @Override public void start(Stage stage) throws IOException
  {
    Model model = new ModelManager();
    try
    {
      server = new Server(model);
    }
    catch (RemoteException | MalformedURLException e)
    {
      e.printStackTrace();
    }
  }
}
