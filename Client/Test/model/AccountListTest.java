package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import static org.junit.jupiter.api.Assertions.*;

class AccountListTest
{
    private AccountList list;
    private Account parent;
    private Account babysitter;

    @BeforeEach void setUp()
    {
        System.out.println("--> setUp()");
        list = new AccountList();
        parent = new Parent("Parent", "password", "parent@gmail.com", "Santa",
            "Clause", false);
        babysitter = new Babysitter("Anne", "Marie",
            "anne", "babysitter@gmail.com", "password",
            new MyDateTime(1, 5, 1999), 2, 40, "English", true);
        list.addAccount(parent);
        list.addAccount(babysitter);
    }

    @AfterEach void tearDown()
    {
        System.out.println("<-- tearDown()");
    }

    @Test void testAdd()
    {
        Parent testParent = new Parent("Test", "password", "test@gmail.com",
            "Testing", "Tester", true);
        list.addAccount(testParent);
        assertThat(testParent.toString(),
            is(list.getAllAccounts().get(2).toString()));
    }

    @Test void testRemove()
    {
        list.removeAccount(parent);
        assertThat(babysitter.toString(),
            is(list.getAllAccounts().get(0).toString()));
    }

    @Test void testNumberOfAccounts()
    {
        Parent testParent = new Parent("Test", "password", "test@gmail.com",
            "Testing", "Tester", true);
        assertEquals(2, list.getNumberOfAccounts());
        list.addAccount(testParent);
        assertEquals(3, list.getNumberOfAccounts());
        list.removeAccount(testParent);
        assertEquals(2, list.getNumberOfAccounts());
    }

    @Test void testNumberOfParents()
    {
        assertEquals(1, list.getNumberOfParentAccounts());
    }

    @Test void testNumberOfBabysitters()
    {
        assertEquals(1, list.getNumberOfBabysitterAccounts());
    }

    @Test void testOrder()
    {
        AccountList testTrue = new AccountList();
        AccountList testFalse = new AccountList();
        testTrue.addAccount(parent);
        testTrue.addAccount(babysitter);
        assertThat(list.toString(), is(testTrue.toString()));
        testFalse.addAccount(babysitter);
        testFalse.addAccount(parent);
        assertThat(list.toString(), is(not(testFalse.toString())));
    }
}