package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ParentTest
{
  private Parent parent;
  private ArrayList<Kid> kids;

  @BeforeEach void setUp()
  {
    parent = new Parent("Lana", "password", "lana@gmail.com", "Lana", "Peters",
        false);
    kids = new ArrayList<>();
  }

  @Test void sizeAfterAdding4Kids()
  {
    parent.addKid(new Kid(13, 2, 2001, true, "health issues"));
    parent.addKid(new Kid(12, 1, 2001, false, "health issues"));
    parent.addKid(new Kid(10, 4, 2001, true, "health issues"));
    parent.addKid(new Kid(5, 2, 2001, false, "health issues"));

    assertEquals(4, parent.getNrOfKids());
  }

  @Test void setHasPets()
  {
    parent.setHasPets(true);
    assertTrue(parent.hasPets());
  }

  @Test void testEquals()
  {
    Parent parent1 = new Parent("La", "password", "lana123@gmail.com", "Lana",
        "Peters", false);
    Parent parent2 = new Parent("Lana", "password", "lana@gmail.com", "Lana",
        "Peters", false);
    System.out.println(parent1.equals(parent));
    assertNotEquals(parent1, parent);
    System.out.println(parent.equals(parent2));
    assertEquals(parent, parent2);
  }

  @Test void getKids()
  {

    assertEquals(kids, parent.getKids());
  }

  @Test void addNullKidObject()
  {
    parent.addKid(null);
    assertThrows(IllegalArgumentException.class, () -> {
      parent.addKid(null);
    });
  }

}