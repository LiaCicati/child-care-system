package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class BookingTest
{
  private Booking booking;

  @BeforeEach void setUp()
  {
    System.out.println("--> setUp()");
    Babysitter babysitter = new Babysitter("Lia", 13, 2, 2001, 50, "English", 2,
        true);
    Parent parent = new Parent(true);
    this.booking = new Booking(new TimeInterval(new MyDateTime(13, 2, 2021),
        new MyDateTime(14, 2, 2001)), parent, babysitter);

  }

  @AfterEach void tearDown()
  {
    System.out.println("<-- tearDown()");
  }

  @Test void setZero()
  {
    booking.setDateTimeOfBooking(null);
    assertThrows(IllegalArgumentException.class, () -> {
      booking.setDateTimeOfBooking(null);
    });
  }

  @Test void setBookingDateTime()
  {
    booking.setDateTimeOfBooking(LocalDateTime.now());
    System.out.println(booking.getDateTimeOfBooking());
    System.out.println(booking);
    assertEquals(LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("dd/MM/yyyy, hh:mm")),
        booking.getDateTimeOfBooking());
  }

}