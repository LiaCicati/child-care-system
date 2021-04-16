package Mediator;

import Model.Booking;
import Model.Model;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.LocalListener;
import utility.observer.subject.PropertyChangeAction;
import utility.observer.subject.PropertyChangeProxy;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server implements RemoteModel, LocalListener<String, String>
{

  private Model model;
  private PropertyChangeAction<String, String> property;

  public Server(Model model) throws RemoteException, MalformedURLException
  {
    this.model = model;
    this.property = new PropertyChangeProxy<>(this);
    startRegistry();
    startServer();
  }

  private void startRegistry() throws RemoteException
  {
    try
    {
      Registry reg = LocateRegistry.createRegistry(1099);
      System.out.println("Registry started... ");
    }
    catch (java.rmi.server.ExportException e)
    {
      System.out.println("Registry already started?" + " " + e.getMessage());
    }
  }

  private void startServer() throws RemoteException, MalformedURLException
  {
    UnicastRemoteObject.exportObject(this, 0);
    Naming.rebind("App", this);
    System.out.println("Server started");
  }

  public void close()
  {
    try
    {
      UnicastRemoteObject.unexportObject(this, true);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  @Override public void addBooking(Booking booking) throws RemoteException
  {
    model.addBooking(booking);
  }

  @Override public void propertyChange(ObserverEvent<String, String> event)
  {
    property.firePropertyChange(event);
  }

  @Override public boolean addListener(GeneralListener<String, String> listener,
      String... propertyNames) throws RemoteException
  {
    return property.addListener(listener, propertyNames);
  }

  @Override public boolean removeListener(
      GeneralListener<String, String> listener, String... propertyNames)
      throws RemoteException
  {
    return property.removeListener(listener, propertyNames);
  }
}
