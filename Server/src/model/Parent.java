package model;

import java.util.ArrayList;

public class Parent extends Account
{
  private int nrOfKids;
  private boolean hasPets;
  private ArrayList<Kid> kids;

  public Parent(String userName, String password, String email,
      String firstName, String lastName, boolean hasPets)
  {
    super(userName, password, email, firstName, lastName);
    this.hasPets = hasPets;
    this.kids = new ArrayList<Kid>();
  }

  public int getNrOfKids()
  {
    return kids.size();
  }

  public boolean hasPets()
  {
    return hasPets;
  }

  public void setHasPets(boolean hasPets)
  {
    this.hasPets = hasPets;
  }

  public boolean equals(Object obj)
  {
    if (!(obj instanceof Parent))
    {
      return false;
    }
    Parent other = (Parent) obj;
    return super.equals(obj) && hasPets == other.hasPets && kids
        .equals(other.kids);
  }

  public ArrayList<Kid> getKids()
  {
    return kids;
  }

  public void addKid(Kid kid)
  {
    if (kid == null)
    {
      throw new IllegalArgumentException("Add data");
    }
    kids.add(kid);
  }

  public String toString()
  {
    return super.toString() + "\n" + "Number ofKids: " + nrOfKids + "\n"
        + " Has pets: " + hasPets + "\n" + " Kids: " + getKids();
  }
}
