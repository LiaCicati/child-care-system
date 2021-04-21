package model;

import model.Babysitter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeIntervalTest {

    private TimeInterval timeInterval;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        System.out.println("--> setUp()");
        timeInterval = new TimeInterval(new MyDateTime(0,0,0,0,0),new MyDateTime(0,0,0,0,0));
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        System.out.println("<-- tearDown()");
    }

    @org.junit.jupiter.api.Test
    void testToString(){
        timeInterval.setStartTime(new MyDateTime(28,7,2021, 15,30));
        timeInterval.setEndTime(new MyDateTime(28,7,2021,20,0));
        assertEquals("Start time: 28/07/2021 15:30 End time: 28/07/2021 20:00", timeInterval.toString());
    }
}