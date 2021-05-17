package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KidTest
{
  private Kid kid;

  @BeforeEach void setUp()
  {
    System.out.println("--> setUp()");
    kid = new Kid(13, 2, 2001, true, "good");
  }

  @AfterEach void tearDown()
  {
    System.out.println("<-- tearDown()");
  }

  @Test void setEmptyHealthCondition()
  {
    kid.setHealthConditions("");
    assertThrows(IllegalArgumentException.class, () -> {
      kid.setHealthConditions("");
    });
  }

  @Test void setNullHealthCondition()
  {
    kid.setHealthConditions(null);
    assertThrows(IllegalArgumentException.class, () -> {
      kid.setHealthConditions(null);
    });
  }

  @Test void equals()
  {
    Kid kid1 = new Kid(13, 2, 2001, true, "good");
    System.out.println(kid.equals(kid1));
    assertEquals(kid1, kid);
  }

  @Test void notEquals()
  {
    Kid kid2 = new Kid(13, 2, 2010, false, "good");
    System.out.println(kid.equals(kid2));
    assertNotEquals(kid2, kid);
  }

//  @Test void getAge()
//  {
//    int age = kid.getAge(new MyDateTime(13, 2, 2001));
//    assertEquals(20, age);
//  }

}