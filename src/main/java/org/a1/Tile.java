package org.a1;

import java.io.Serializable;
import java.util.ArrayList;

public class Tile implements Serializable, Comparable<Tile> {

    private int number;
    private String colour;
    private boolean lastUsed = false;
    private int points = 0;
    public boolean reuse = false;
    private int reuseIndex = -1;
    private int rank = 0;
    ArrayList<Tile> desiredTile;


    public Tile(int number, String colour)
    {
        super();
        this.number = number;
        this.colour = colour;
        if (number > 10)
        {
            points = 10;
        } else {
            points = number;
        }
        if (colour.equals("R")) {
            rank = 1;
        } else if(colour.equals("B")) {
            rank = 2;
        } else if(colour.equals("G")) {
            rank = 3;
        } else if(colour.equals("O")) {
            rank = 4;
        }

        desiredTile = new ArrayList<>();
    }

    public int getRank(){
        return rank;
    }

    public void setRank(int r) {
        rank = r;
    }

    public int getNumber() {
        return number;
    }

    public String getColour() {
        return colour;
    }

    public boolean getLastUsed()
    {
        return lastUsed;
    }

    public void setLastUsed(boolean b)
    {
        lastUsed = b;
    }

    public void setReuse(boolean b) { reuse = b; }

    public int getPoints() { return points; }

    public int getReuseIndex() {
        return reuseIndex;
    }

    public void setReuseIndex(int reuseIndex) {
        this.reuseIndex = reuseIndex;
    }

    @Override
    public int compareTo(Tile anotherInstance) {
        return this.rank < anotherInstance.rank ? 0 : 1;
    }

    public ArrayList<Tile> getDesiredTiles() {
        return desiredTile;
    }

    public void setDesiredTile(Tile t) {
        this.desiredTile.add(t);
    }

    public void printDesiredTile(){
        for(Tile t: desiredTile){
            System.out.println("Joker Represents: " + t.getColour() + t.getNumber());
        }
    }
}