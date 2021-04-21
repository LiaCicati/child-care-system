package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.lang.String.valueOf;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MyDateTimeTest
{
    private MyDateTime myDateTime;

    @BeforeEach void setUp()
    {
        System.out.println("--> setUp()");
        myDateTime = new MyDateTime(2, 2, 2021, 12, 30);
    }

    @AfterEach void tearDown()
    {
        System.out.println("<-- tearDown()");
    }

    @Test void set()
    {
        myDateTime.set(1, 1, 1, 1, 2022);
        assertEquals("01:01:2022:01:01", valueOf(myDateTime));
    }

    @Test void setDay()
    {
        myDateTime.setDay(10);
        assertEquals("10", valueOf(myDateTime.getDay()));
    }

    @Test void setMonth()
    {
        myDateTime.setMonth(10);
        assertEquals("10", valueOf(myDateTime.getMonth()));
    }

    @Test void setYear()
    {
        myDateTime.setYear(2022);
        assertEquals("2022", valueOf(myDateTime.getYear()));
    }

    @Test void setHour()
    {
        myDateTime.setHour(1);
        assertEquals("1", valueOf(myDateTime.getHour()));
    }

    @Test void setMinute()
    {
        myDateTime.setMinute(1);
        assertEquals("1", valueOf(myDateTime.getMinute()));
    }

    @Test void isBefore()
    {
    }

    @Test void testEquals()
    {
    }

    @Test void testToString()
    {
    }
}