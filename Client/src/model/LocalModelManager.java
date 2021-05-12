package model;

import mediator.Client;
import mediator.ClientModel;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.LocalListener;
import utility.observer.subject.PropertyChangeAction;
import utility.observer.subject.PropertyChangeProxy;

import java.rmi.RemoteException;

public class LocalModelManager implements LocalModel, LocalListener<String, String>
{
  private ClientModel serverModel;
  private PropertyChangeAction<String, String> property;

  public LocalModelManager()
  {
    try
    {
      this.serverModel = new Client(this, "localhost");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    this.property = new PropertyChangeProxy<>(this);
  }

  @Override public void addBooking(Booking booking) throws RemoteException
  {
    serverModel.addBooking(booking);
  }

  @Override
  public boolean isPasswordCorrect(String userName, String password) throws RemoteException {
    return serverModel.isPasswordCorrect(userName, password);
  }



  @Override public boolean addListener(GeneralListener<String, String> listener,
      String... propertyNames)
  {
    return property.addListener(listener, propertyNames);
  }

  @Override public boolean removeListener(
      GeneralListener<String, String> listener, String... propertyNames)
  {
    return property.removeListener(listener, propertyNames);
  }

  @Override public void propertyChange(ObserverEvent<String, String> event)
  {
    property.firePropertyChange(event);
  }
}
