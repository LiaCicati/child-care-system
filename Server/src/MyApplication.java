import mediator.RemoteModel;
import mediator.Server;
import model.Model;
import model.ModelManager;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class MyApplication extends Application
{
  private Model model;
  private RemoteModel server;

  @Override public void start(Stage primaryStage)
      throws IOException, SQLException
  {
    model = new ModelManager();

    //  starting server
    try
    {
      server = new Server(model);
    }
    catch (RemoteException | MalformedURLException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void stop()
  {
    model.close();  //closing observer threads
    ((Server) server).close();   //closing server
  }
}
