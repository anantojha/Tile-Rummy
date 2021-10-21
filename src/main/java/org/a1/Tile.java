package org.a1;

import java.io.Serializable;

public class Tile implements Serializable {

    private int number;
    private String colour;
    private boolean lastUsed = false;
    private int points = 0;
    public boolean reuse = false;
    private int reuseIndex = -1;

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
}