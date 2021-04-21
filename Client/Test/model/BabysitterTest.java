package model;

import model.Babysitter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BabysitterTest {

    private Babysitter babysitter;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        System.out.println("--> setUp()");
        babysitter = new Babysitter(null,9,10,1996,0,null,0,false);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        System.out.println("<-- tearDown()");
    }

    @org.junit.jupiter.api.Test
    void setZero() {
        babysitter.setUserName(null);
        assertEquals(null,babysitter.getUserName());

        babysitter.setPaymentPerHour(0);
        assertEquals(0,babysitter.getPaymentPerHour());

        babysitter.setBabysittingExperience(0);
        assertEquals(0, babysitter.getBabysittingExperience());

        babysitter.setFirstAidCertificate(false);
        assertEquals(false, babysitter.hasFirstAidCertificate());

        babysitter.addLanguage(null);
        assertEquals(null, babysitter.getLanguages());
    }

    @Test void setOne(){
        babysitter.setUserName(null);
        assertEquals(null,babysitter.getUserName());

        babysitter.setPaymentPerHour(1);
        assertEquals(1,babysitter.getPaymentPerHour());

        babysitter.setBabysittingExperience(1);
        assertEquals(1, babysitter.getBabysittingExperience());

        babysitter.setFirstAidCertificate(true);
        assertEquals(true, babysitter.hasFirstAidCertificate());

        babysitter.addLanguage(null);
        assertEquals(null, babysitter.getLanguages());
    }

    @Test void setMany(){
        babysitter.setUserName(null);
        assertEquals(null,babysitter.getUserName());

        babysitter.setPaymentPerHour(6.3);
        assertEquals(6.3,babysitter.getPaymentPerHour());

        babysitter.setPaymentPerHour(500.75);
        assertEquals(500,babysitter.getPaymentPerHour());

        babysitter.setBabysittingExperience(20);
        assertEquals(20, babysitter.getBabysittingExperience());

        babysitter.setBabysittingExperience(4.7);
        assertEquals(4.7, babysitter.getBabysittingExperience());

        babysitter.addLanguage(null);
        assertEquals(null, babysitter.getLanguages());
    }

    @Test void setBoundary(){
        //PAYMENT
        //lower left: 4.94065645841248E-324
        assertThrows(IllegalArgumentException.class, () -> {
            babysitter.setPaymentPerHour(4.94065645841248E-324);
        });

        //lower right: 4.94065645841247E-324
        babysitter.setPaymentPerHour(4.94065645841247E-324);
        assertEquals(4.94065645841247E-324, babysitter.getPaymentPerHour());

        //upper left: 3.4028235E38
        babysitter.setPaymentPerHour(3.4028235E38);
        assertEquals(3.4028235E38, babysitter.getPaymentPerHour());

        //upper right: 3.4028236E38
        assertThrows(IllegalArgumentException.class, () -> {
            babysitter.setPaymentPerHour(3.4028236E38);
        });

        //EXPERIENCE
        //lower left: 4.94065645841248E-324
        assertThrows(IllegalArgumentException.class, () -> {
            babysitter.setBabysittingExperience(4.94065645841248E-324);
        });

        //lower right: 4.94065645841247E-324
        babysitter.setBabysittingExperience(4.94065645841247E-324);
        assertEquals(4.94065645841247E-324, babysitter.getBabysittingExperience());

        //upper left: 3.4028235E38
        babysitter.setBabysittingExperience(3.4028235E38);
        assertEquals(3.4028235E38, babysitter.getBabysittingExperience());

        //upper right: 3.4028236E38
        assertThrows(IllegalArgumentException.class, () -> {
            babysitter.setBabysittingExperience(3.4028236E38);
        });
    }
}