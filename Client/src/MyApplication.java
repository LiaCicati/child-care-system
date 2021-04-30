import mediator.Client;
import model.Model;
import javafx.application.Application;
import javafx.stage.Stage;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

import java.io.IOException;
import java.rmi.NotBoundException;

public class MyApplication extends Application
{
  private Model model;

  @Override public void start(Stage stage) throws IOException, NotBoundException
  {

    Client client = new Client(model, "localhost");

//    Scanner input = new Scanner(System.in);
//
//    boolean check = true;
//    while (check)
//    {
//      System.out.print("To book a babysitter please type 1: ");
//      String line = input.nextLine();
//      if (line.equals("stop"))
//      {
//        check = false;
//      }
//      else if (line.equals("1"))
//      {
//        System.out.println("The booking was successfully made! ");
//      }
//
//    }

    ViewModelFactory viewModelFactory = new ViewModelFactory(model);
    ViewHandler view = new ViewHandler(viewModelFactory);
    view.start(stage);
  }
}
