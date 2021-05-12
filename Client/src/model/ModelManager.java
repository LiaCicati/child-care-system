package model;

import mediator.Client;
import mediator.RemoteModelClient;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.LocalListener;
import utility.observer.subject.PropertyChangeAction;
import utility.observer.subject.PropertyChangeProxy;

import java.rmi.RemoteException;

public class ModelManager implements Model, LocalListener<String, String>
{
  private RemoteModelClient remoteModelClient;
  private PropertyChangeAction<String, String> property;

  public ModelManager()
  {
    try
    {
      this.remoteModelClient = new Client(this, "localhost");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    this.property = new PropertyChangeProxy<>(this);
  }

  @Override public void addBooking(Booking booking) throws RemoteException
  {
    remoteModelClient.addBooking(booking);
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
