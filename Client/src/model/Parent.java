package model;
import java.util.ArrayList;


public class Parent
{
    private int nrOfKids;
    private boolean hasPets;
    private ArrayList<Kid> kids;

    public Parent(boolean hasPets){
        this.hasPets = hasPets;
        this.kids = new ArrayList<Kid>();
    }


    public int getNrOfKids() {
        return kids.size();
    }


    public boolean hasPets() {
        return hasPets;
    }

    public void setHasPets(boolean hasPets) {
        this.hasPets = hasPets;
    }

    public boolean equals(Object obj)
    {
        if (!(obj instanceof Parent))
        {
            return false;
        }
        Parent other = (Parent) obj;
        return hasPets
            == other.hasPets
            && kids.equals(other.kids);
    }

    public ArrayList<Kid> getKids() {
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

    public String toString(){
        return "Number ofKids: " + nrOfKids + " Has pets: " + hasPets + " Kids: " + getKids();
    }
}
