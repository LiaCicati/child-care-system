package Model;

import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeAction;
import utility.observer.subject.PropertyChangeProxy;

import java.rmi.RemoteException;

public class ModelManager implements Model
{
  private PropertyChangeAction<String, String> property;

  public ModelManager()
  {
    this.property = new PropertyChangeProxy<>(this);
  }
  @Override public void addBooking(Booking booking) throws RemoteException
  {

  }

  @Override public boolean addListener(GeneralListener<String, String> listener,
      String... propertyNames)
  {
    return property.addListener(listener, propertyNames);
  }

  @Override public boolean removeListener(
      GeneralListener<String, String> listener, String... propertyNames)
  {
    return property.addListener(listener, propertyNames);
  }
}
