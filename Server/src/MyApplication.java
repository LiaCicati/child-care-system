import mediator.RemoteModelServer;
import mediator.Server;
import model.Model;
import model.ModelManager;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class MyApplication extends Application
{
    private Model model;
    private RemoteModelServer server;

    @Override public void start(Stage primaryStage) throws IOException
    {
        model = new ModelManager();
//        ViewModelFactory viewModelFactory = new ViewModelFactory(model);
//        ViewHandler view = new ViewHandler(viewModelFactory);
//
//        view.start(primaryStage);

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
        ((Server)server).close();   //closing server
    }
}
