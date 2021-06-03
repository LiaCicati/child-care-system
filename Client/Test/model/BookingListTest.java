package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import static org.junit.jupiter.api.Assertions.*;

class BookingListTest
{
    private Parent parent;
    private Babysitter babysitter;
    private Booking booking1;
    private Booking booking2;
    private BookingList list;

    @BeforeEach void setUp()
    {
        System.out.println("--> setUp()");
        list = new BookingList();
        parent = new Parent("Parent", "Name", "name", "parent@gmail.com",
            "password", false);
        babysitter = new Babysitter("Anne", "Marie",
            "anne", "babysitter@gmail.com", "password",
            new MyDateTime(1, 5, 1999), 2, 40, "English", true);
        booking1 = new Booking(
            new TimeInterval(new MyDateTime(13, 3, 2021, 12, 30),
                new MyDateTime(13, 3, 2001, 19, 0)), parent, babysitter, null);
        booking2 = new Booking(
            new TimeInterval(new MyDateTime(15, 3, 2021, 17, 30),
                new MyDateTime(15, 3, 2001, 21, 45)), parent, babysitter, null);
        list.addBooking(booking1);
        list.addBooking(booking2);
    }

    @AfterEach void tearDown()
    {
        System.out.println("<-- tearDown()");
    }

    @Test void testAdd()
    {
        Booking testBooking = new Booking(
            new TimeInterval(new MyDateTime(17, 3, 2021, 16, 45),
                new MyDateTime(17, 3, 2001, 20, 15)), parent, babysitter, null);
        list.addBooking(testBooking);
        assertThat(testBooking.toString(), is(list.getBooking(2).toString()));
    }

    @Test void testRemove()
    {
        list.removeBooking(booking1);
        assertThat(booking2.toString(), is(list.getBooking(0).toString()));
    }

    @Test void testNumberOfBookings()
    {
        Booking testBooking = new Booking(
            new TimeInterval(new MyDateTime(17, 3, 2021, 16, 45),
                new MyDateTime(17, 3, 2001, 20, 15)), parent, babysitter, null);
        assertEquals(2, list.getNumberOfBookings());
        list.addBooking(testBooking);
        assertEquals(3, list.getNumberOfBookings());
        list.removeBooking(testBooking);
        assertEquals(2, list.getNumberOfBookings());
    }

    @Test void testOrder()
    {
        BookingList testTrue = new BookingList();
        BookingList testFalse = new BookingList();
        testTrue.addBooking(booking1);
        testTrue.addBooking(booking2);
        assertThat(list.toString(), is(testTrue.toString()));
        testFalse.addBooking(booking2);
        testFalse.addBooking(booking1);
        assertThat(list.toString(), is(not(testFalse.toString())));
    }
}