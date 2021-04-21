package model;

import java.util.ArrayList;

public class Parent
{
    private int nrOfKids;
    private boolean hasPets;
    private ArrayList<Kid> kids;

    public Parent(boolean hasPets){
        this.setHasPets(hasPets);
    }


    public int getNrOfKids() {
        return kids.size();
    }

    public void setNrOfKids(int nrOfKids) {
        this.nrOfKids = nrOfKids;
    }

    public boolean isHasPets() {
        return hasPets;
    }

    public void setHasPets(boolean hasPets) {
        this.hasPets = hasPets;
    }

    public ArrayList<Kid> getKids() {
        return kids;
    }

    public void addKid(Kid kid) {
        this.kids = kids;
    }
}
