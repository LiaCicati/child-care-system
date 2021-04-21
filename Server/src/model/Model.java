package model;

import utility.observer.subject.LocalSubject;

public interface Model extends LocalSubject<Booking, Booking>
{
  void addBooking(Booking booking) throws IllegalArgumentException;

  void close();
}
