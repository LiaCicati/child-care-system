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
    Babysitter babysitter2 = new Babysitter("Loredana", "Cicati", "lori",
        "lori@mail.ru", "lialialia", new MyDateTime(13, 2, 2001), 2, 30,
        "English", false);
    Parent parent1 = new Parent("Ana", "Peters", "ana", "ana@gmail.com",
        "password", false);
    this.booking = new Booking(
        new TimeInterval(new MyDateTime(22, 5, 2021, 12, 5),
            new MyDateTime(22, 5, 2021, 14, 5)), parent1, babysitter2, null);

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