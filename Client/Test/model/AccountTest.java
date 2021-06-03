package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest
{
  private Account babysitter;
  private Account parent;
  private AccountList list;

  @BeforeEach void setUp()
  {
    System.out.println("--> setUp()");
    list = new AccountList();
    parent = new Parent("Ana", "Peters", "ana", "ana@gmail.com", "password",
        true);
    babysitter = new Babysitter("Lia", "Cicati", "ciocarlia","ciocarlia@gmail.com", "password",
        new MyDateTime(13, 2, 2001), 1,30, "English", true);
  }

  @AfterEach void tearDown()
  {

    System.out.println("<-- tearDown()");
  }

  @Test void isAccountValid()
  {
    list.addAccount(parent);
    list.addAccount(babysitter);
    assertEquals(2, list.getNumberOfAccounts());

  }

  @Test void validateEmailWhenInvalidData()
  {
    assertThrows(IllegalArgumentException.class, () -> {
      parent.setEmail(".username@yahoo.com");
    });
    assertThrows(IllegalArgumentException.class, () -> {
      parent.setEmail("username@yahoo.com.");
    });
    assertThrows(IllegalArgumentException.class, () -> {
      parent.setEmail("username@yahoo.c");
    });
    assertThrows(IllegalArgumentException.class, () -> {
      parent.setEmail("username@yahoo..com");
    });

    assertThrows(IllegalArgumentException.class, () -> {
      babysitter.setEmail("username@yahoo.corporate");
    });

    assertThrows(IllegalArgumentException.class, () -> {
      babysitter.setEmail("username@gmail");
    });

    assertThrows(IllegalArgumentException.class, () -> {
      babysitter.setEmail("username.gmail.com");
    });

    assertThrows(IllegalArgumentException.class, () -> {
      babysitter.setEmail("username@gmail");
    });

    assertThrows(IllegalArgumentException.class, () -> {
      babysitter.setEmail("username");
    });
  }

  @Test void isEmailValidWhenValid()
  {
    babysitter.setEmail("ciocarlia@gmail.com");
    assertTrue(babysitter.validateEmail(babysitter.getEmail()));
  }

  @Test void isFirstNameValidWhenEmpty()
  {
    assertThrows(IllegalArgumentException.class, () -> {
      parent.setFirstName("");
    });
  }

  @Test void isFirstNameValidWhenNull()
  {
    assertThrows(IllegalArgumentException.class, () -> {
      parent.setFirstName(null);
    });
  }

  @Test void isLastNameValidWhenEmpty()
  {
    assertThrows(IllegalArgumentException.class, () -> {
      babysitter.setLastName("");
    });
  }

  @Test void isLastNameValidWhenNull()
  {
    assertThrows(IllegalArgumentException.class, () -> {
      babysitter.setLastName(null);
    });
  }

  @Test void isUsernameValidWhenEmpty()
  {
    assertThrows(IllegalArgumentException.class, () -> {
      babysitter.setUserName("");
    });
  }

  @Test void isUsernameValidWhenNull()
  {
    assertThrows(NullPointerException.class, () -> {
      babysitter.setUserName(null);
    });
  }

  @Test void isUsernameValidWhenTooLong()
  {
    assertThrows(IllegalArgumentException.class, () -> {
      babysitter.setUserName("anaaaaaaaaaaaaaaa");
    });

    assertThrows(IllegalArgumentException.class, () -> {
      parent.setUserName("anaaaaaaaaaaaaaaa");
    });
  }

  @Test void isPasswordValidWhenTooShort()
  {
    assertThrows(IllegalArgumentException.class, () -> {
      parent.setPassword("ana");
    });
  }

  @Test void isPasswordValidWhenTooLong()
  {
    assertThrows(IllegalArgumentException.class, () -> {
      parent.setPassword("anaaaaaaaaaaaaaaaaa");
    });
  }

  @Test void isPasswordValidWhenEmpty()
  {
    assertThrows(IllegalArgumentException.class, () -> {
      parent.setPassword("");
    });
  }

  @Test void isPasswordValidWhenNull()
  {
    assertThrows(NullPointerException.class, () -> {
      parent.setPassword(null);
    });
  }

  @Test void isPasswordValidWhenValid()
  {
    assertTrue(babysitter.validatePassword(babysitter.getPassword()));
  }
}