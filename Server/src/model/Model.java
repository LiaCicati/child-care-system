package model;

import utility.observer.subject.LocalSubject;

public interface Model extends UserModel, LocalSubject<Account, Booking>
{
  void addBooking(Booking booking) throws IllegalArgumentException;

  void close();
}
