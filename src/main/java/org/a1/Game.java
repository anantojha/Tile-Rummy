package org.a1;

import java.io.Serializable;
import java.util.*;

public class Game implements Serializable {

    private static final long serialVersionUID = 1L;

    public ArrayList<Tile> generateTiles()
    {
        ArrayList<Tile> tiles = new ArrayList<>();
        for(int i = 0; i < 2; i++)
        {
            addTiles( "R", tiles);
            addTiles( "B", tiles);
            addTiles( "G", tiles);
            addTiles( "O", tiles);
        }
        Collections.shuffle(tiles);
        return tiles;
    }

    private void addTiles(String colour, ArrayList<Tile> tiles)
    {
        for(int i = 0; i < 13; i++)
        {
            tiles.add(new Tile(i + 1, colour));
        }
    }

    public ArrayList<Tile> createPlayerHands(Player[] players, ArrayList<Tile> tiles)
    {
        players[0].setHand(new ArrayList<>(tiles.subList(0, 14)));
        players[1].setHand(new ArrayList<>(tiles.subList(14, 28)));
        players[2].setHand(new ArrayList<>(tiles.subList(28, 42)));
        return new ArrayList<>(tiles.subList(42, 104));
    }

    public Tile drawNewTile(Player p, int n, String c, ArrayList<Tile> tiles){
        for (int i = 0; i<tiles.size(); i++)
        {
            if(tiles.get(i).getNumber() == n)
            {
                if(tiles.get(i).getColour().equals(c)){
                    Tile tile = tiles.get(i);
                    tiles.remove(i);
                    tile.setLastUsed(true);
                    p.getPlayer().getHand().add(tile);
                    return tile;
                }
            }
        }
        return null;
    }

    public void drawNewTile(Player p, ArrayList<Tile> tiles)
    {
        Tile tile = tiles.get(0);
        tiles.remove(0);
        tile.setLastUsed(true);
        p.getPlayer().getHand().add(tile);
    }

    public void drawNewTiles(Player p, int n, ArrayList<Tile> tiles){
        for (int i = 0; i<n; i++)
        {
            Tile tile = tiles.get(0);
            tiles.remove(0);
            tile.setLastUsed(true);
            p.getPlayer().getHand().add(tile);
        }
    }

    public ArrayList<ArrayList<Tile>>  convertMeldInputToTiles(String[] in)
    {
        String[] temp;
        ArrayList<ArrayList<Tile>> melds = new ArrayList<>();
        for (String s : in)
        {
            temp = s.split(",");
            ArrayList<Tile> meld = new ArrayList<>();
            for (String v : temp)
            {
                meld.add(new Tile(Integer.parseInt(v.substring(1)), v.substring(0, 1)));
            }
            melds.add(meld);
        }

        return melds;
    }

    public boolean initialMeldsAtLeastThirty(Player player, ArrayList<ArrayList<Tile>> melds)
    {
        if (player.initialThirty){
            return true;
        }

        int counter = 0;

        for (ArrayList<Tile> meld: melds)
        {
            for (Tile tile: meld)
            {
                counter += tile.getNumber();
            }
        }

        if(counter >= 30)
        {
            return true;
        }
        else
        {
            return  false;
        }
    }
}
