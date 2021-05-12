package model;

import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeAction;
import utility.observer.subject.PropertyChangeProxy;

import java.rmi.RemoteException;

public class ModelManager implements Model
{
//    private BookingList bookingList;    //TODO
    private PropertyChangeAction<Booking, Booking> property;

    public ModelManager()
    {
//        this.bookingList = new BookingList();   //TODO
        this.property = new PropertyChangeProxy<>(this);
    }

    @Override public void addBooking(Booking booking)
        throws IllegalArgumentException
    {
//        bookingList.addBooking(booking);    //TODO
//        property.firePropertyChange("add", null, booking);
    }

    @Override
    public boolean doesUserNameExist(String userName) throws RemoteException {
        return false;
        //der skal være noget med at kigge i account list
    }

    @Override
    public boolean isPasswordCorrect(String userName, String password) throws RemoteException {
        return false;
        //der skal være noget med at kigge i account list

    }

    @Override public void close()
    {
//        property.close();
    }

    @Override public boolean addListener(
        GeneralListener<Booking, Booking> listener, String... propertyNames)
    {
        return property.addListener(listener, propertyNames);
    }

    @Override public boolean removeListener(
        GeneralListener<Booking, Booking> listener, String... propertyNames)
    {
        return property.removeListener(listener, propertyNames);

    }
}
