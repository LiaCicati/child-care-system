package Mediator;

import Model.Booking;
import Model.Model;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.RemoteListener;
import utility.observer.subject.PropertyChangeAction;
import utility.observer.subject.PropertyChangeProxy;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Client implements RemoteModel, RemoteListener<String, String>
{
  private String host;
  private Model model;
  private RemoteModel remoteModel;
  private PropertyChangeAction<String, String> property;

  public Client(Model model, String host)
      throws RemoteException, NotBoundException, MalformedURLException
  {
    this.model = model;
    this.host = host;
    connect();
    this.property = new PropertyChangeProxy<>(this);
  }

  @Override public void connect()
      throws RemoteException, MalformedURLException, NotBoundException
  {
    this.remoteModel = (RemoteModel) Naming
        .lookup("rmi://" + host + ":1099/App");
    UnicastRemoteObject.exportObject(this, 0);
  }

  @Override public void addBooking(Booking booking) throws RemoteException
  {
    remoteModel.addBooking(booking);
  }

  @Override public void propertyChange(ObserverEvent<String, String> event)
      throws RemoteException
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
