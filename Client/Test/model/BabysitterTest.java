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

       /* babysitter.addLanguage(null);
        assertEquals(null, babysitter.getLanguages());*/
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

     /*   babysitter.addLanguage(null);
        assertEquals(null, babysitter.getLanguages());*/
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

 /*       babysitter.addLanguage(null);
        assertEquals(null, babysitter.getLanguages());*/
    }

    @Test void setBoundary(){
        //PAYMENT
        //lower left: -1
        babysitter.setPaymentPerHour(-1);
        assertEquals(1, babysitter.getPaymentPerHour());

        //lower right: 0
        babysitter.setPaymentPerHour(0);
        assertEquals(0, babysitter.getPaymentPerHour());

        //upper left: 500
        babysitter.setPaymentPerHour(500);
        assertEquals(500, babysitter.getPaymentPerHour());

        //upper right: 501
        babysitter.setPaymentPerHour(501);
        assertEquals(500, babysitter.getPaymentPerHour());

        //EXPERIENCE
        //lower left:-1
        babysitter.setBabysittingExperience(-1);
        assertEquals(1, babysitter.getBabysittingExperience());

        //lower right: 0
        babysitter.setBabysittingExperience(0);
        assertEquals(0, babysitter.getBabysittingExperience());

        //upper left: 70
        babysitter.setBabysittingExperience(70);
        assertEquals(70, babysitter.getBabysittingExperience());

        //upper right: 71
        babysitter.setBabysittingExperience(71);
        assertEquals(70, babysitter.getBabysittingExperience());
    }

    @Test void setException(){
        //values too high
        assertThrows(java.lang.IllegalArgumentException.class, () ->{
            babysitter.setPaymentPerHour(735.99);
        });
        //the above fails because the code just sets it to 500, so no exception will be thrown, i dont know if it
        // should just be deleted.

        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            babysitter.setBabysittingExperience(90);
        });

        //values too low
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            babysitter.setPaymentPerHour(-885.95);
        });
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            babysitter.setBabysittingExperience(-89.7);
        });

    }
}