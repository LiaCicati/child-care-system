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
    setHasPets(hasPets);
    this.kids = new ArrayList<Kid>();
  }

  public Parent(String userName, String password, String email,
      String firstName, String lastName)
  {
    super(userName, password, email, firstName, lastName);
    this.kids = new ArrayList<>();
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

  public Kid getByID(int id)
  {
    for (int i = 0; i < kids.size(); i++)
    {
      if (kids.get(i).getId() == id)
      {
        return kids.get(i);
      }
    }
    return null;
  }

  public Kid addKid(Kid kid)
  {
    if (kid == null)
    {
      throw new IllegalArgumentException("Add data");
    }
    kids.add(kid);
    return kid;
  }

  public Kid getKid(int index){
    return kids.get(index);
  }

  public ArrayList<Kid> getAllKids()
  {
    return kids;
  }
  public String toString()
  {
    return super.toString() + "\n" + "Number ofKids: " + nrOfKids + "\n"
        + " Has pets: " + hasPets + "\n" + " Kids: " + getKids();
  }
}
