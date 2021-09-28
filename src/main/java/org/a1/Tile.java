package org.a1;

import java.io.Serializable;

public class Tile implements Serializable {

    private int number;
    private String colour;
    private boolean lastUsed = false;

    public Tile(int number, String colour)
    {
        this.number = number;
        this.colour = colour;
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
}