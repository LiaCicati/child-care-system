import mediator.RemoteModel;
import mediator.RemoteModelManager;
import model.Model;
import model.ModelManager;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class MyApplication extends Application
{
//    private Model model;
//    private RemoteModel server;

     public void start(Stage primaryStage) throws IOException
    {
        Model model = new ModelManager();
        RemoteModel remoteModel = new RemoteModelManager(model);
//        ViewModelFactory viewModelFactory = new ViewModelFactory(model);
//        ViewHandler view = new ViewHandler(viewModelFactory);
//
//        view.start(primaryStage);

        //  starting server

    }

//    @Override public void stop()
//    {
//        model.close();  //closing observer threads
//        ((RemoteModelManager)server).close();   //closing server
//    }
}
