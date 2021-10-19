package org.a1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

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
                if(v.contains("*"))
                {
                    Tile t = new Tile(Integer.parseInt(v.substring(2)), v.substring(1, 2));
                    t.reuse = true;
                    meld.add(t);
                } else {
                    meld.add(new Tile(Integer.parseInt(v.substring(1)), v.substring(0, 1)));
                }
            }
            melds.add(meld);
        }

        return melds;
    }

    public Tile getTileFromTable(int n, String c, boolean remove, ArrayList<ArrayList<Tile>> melds)
    {
        for (int i = 0; i < melds.size(); i++)
        {
            for (int j = 0; j<melds.get(i).size(); j++)
            {
                if(melds.get(i).get(j).getNumber() == n)
                {
                    if(melds.get(i).get(j).getColour().equals(c))
                    {
                        if(remove){
                            Tile temp = new Tile(n, c);
                            melds.get(i).remove(j);
                            return temp;
                        }
                        return new Tile(n, c);
                    }
                }
            }
        }
        return null;
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

        return counter >= 30;
    }

    public Boolean setColors(String[] colors){
        for (int i = 0; i < colors.length; i++) {
            for (int j = 0; j < colors.length; j++) {
                if (colors[i].equals(colors[j]) && i != j) {
                    return false;
                }
            }
        }
        return true;
    }

    public Boolean runColors(String[] colors){
        String first = colors[0];
        for (String color : colors) {
            if (!first.equals(color)) {
                return false;
            }
        }
        return true;
    }

    public Boolean setNumbers(int[] numbers){
        int first = numbers[0];
        for (int number : numbers) {
            if (first != number) {
                return false;
            }
        }
        return true;
    }

    public Boolean runNumbers(int[] numbers){
        Arrays.sort(numbers);
        for(int i = 1; i < numbers.length; i++){
            if(numbers[i-1] + 1 != numbers[i]){
                return false;
            }
        }
        return true;
    }


    public boolean validateMelds(ArrayList<ArrayList<Tile>> melds) {
        for(ArrayList<Tile> meld: melds){
            if (meld.size() == 0){
                return false;
            }

            String[] colors = new String[meld.size()];
            int[] numbers = new int[meld.size()];
            for(int i = 0; i < meld.size(); i++){
                colors[i] = meld.get(i).getColour();
                numbers[i] = meld.get(i).getNumber();
            }

            if(setColors(colors) && setNumbers(numbers)){
                return true;
            }

            if(runColors(colors) && runNumbers(numbers)){
                return true;
            }
        }

        return false;
    }

    public Boolean playMelds(Player player, ArrayList<ArrayList<Tile>> meld, ArrayList<ArrayList<Tile>> inMeld)
    {
        if (!initialMeldsAtLeastThirty(player, inMeld))
        {
            return false;
        }

        ArrayList<Tile> newHandOffset;
        ArrayList<ArrayList<Tile>> newMelds = new ArrayList<>();

        for (ArrayList<Tile> m: inMeld)
        {
            newHandOffset = new ArrayList<>();


            for (Tile t: m) {
                if (t.reuse != true) {
                    Tile temp = player.getTileFromhand(t.getNumber(), t.getColour(), true);
                    temp.setLastUsed(true);
                    newHandOffset.add(temp);
                } else {
                    Tile temp = getTileFromTable(t.getNumber(), t.getColour(), true, meld);
                    temp.setReuse(true);
                    newHandOffset.add(temp);
                }
            }

            newMelds.add(newHandOffset);
        }

        for (ArrayList<Tile> group: newMelds)
        {
            meld.add(group);
        }

        meld.removeIf(ArrayList::isEmpty);

        player.initialThirty = true;

        return true;
    }

    public void printPlayerHands(Player[] players)
    {
        for (Player p : players)
        {
            printHand(p);
        }
    }

    public void printHand(Player p)
    {
        printSortedHand(p.getHand(), p.getName());
    }

    public ArrayList<ArrayList<Tile>> sortTiles(ArrayList<Tile> tiles)
    {
        ArrayList<Tile> sortedHandRed = new ArrayList<>();
        ArrayList<Tile> sortedHandBlue = new ArrayList<>();
        ArrayList<Tile> sortedHandGreen = new ArrayList<>();
        ArrayList<Tile> sortedHandOrange = new ArrayList<>();

        for (Tile t : tiles)
        {
            if(t.getColour().equals("R"))
            {
                sortedHandRed.add(t);
            }
            else if(t.getColour().equals("B"))
            {
                sortedHandBlue.add(t);
            }
            else if(t.getColour().equals("G"))
            {
                sortedHandGreen.add(t);
            }
            else
            {
                sortedHandOrange.add(t);
            }
        }

        ArrayList<ArrayList<Tile>> result = new ArrayList<>();

        sortedHandRed.sort(Comparator.comparingInt(Tile::getNumber));
        result.add(sortedHandRed);
        sortedHandBlue.sort(Comparator.comparingInt(Tile::getNumber));
        result.add(sortedHandBlue);
        sortedHandGreen.sort(Comparator.comparingInt(Tile::getNumber));
        result.add(sortedHandGreen);
        sortedHandOrange.sort(Comparator.comparingInt(Tile::getNumber));
        result.add(sortedHandOrange);
        return  result;
    }

    public void printSortedHand(ArrayList<Tile> tiles, String playerName) { // implement sort order RBGO 1-13

        ArrayList<ArrayList<Tile>> sortedTiles = sortTiles(tiles);

        System.out.println("|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("|  Hand of player : " + playerName);

        for(ArrayList<Tile> sortedSingleColourTiles: sortedTiles)
        {
            printTiles(sortedSingleColourTiles, false);
        }

        System.out.println();
        System.out.println(
                "|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");

    }

    public void printMelds(ArrayList<ArrayList<Tile>> melds)
    {
        System.out.println(
                "|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("|  Melds: ");
        for (ArrayList<Tile> meld: melds)
        {
            System.out.print("{");
            ArrayList<ArrayList<Tile>> sortedTiles = sortTiles(meld);
            for (ArrayList<Tile> tiles : sortedTiles)
            {
                printTiles(tiles, false);
            }
            System.out.print("}, ");
        }
        System.out.println();
        System.out.println(
                "|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
    }

    public void printTiles(ArrayList<Tile> tiles, boolean line)
    {
        for (Tile t : tiles) {
            if(t.getLastUsed()){
                System.out.print("| " + t.getColour() + t.getNumber() + "* | ");
            } else if (t.reuse == true) {
                System.out.print("| " + t.getColour() + t.getNumber() + "! | ");
            } else {
                System.out.print("| " + t.getColour() + t.getNumber() + " | ");
            }
        }
        if (line)
        {
            System.out.println();
        }
    }

    public void printRemainingTiles(ArrayList<Tile> tiles)
    {
        ArrayList<ArrayList<Tile>> sortedTiles = sortTiles(tiles);

        System.out.println(
                "|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("|  Remaining Tiles on table (faced down):  " + tiles.size() + " Tiles ");
        for(ArrayList<Tile> t: sortedTiles)
        {
            printTiles(t, true);
        }
        System.out.println(
                "|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");

    }

    public void printWinner(Player[] players)
    {
        System.out.println("Winner: Player " + checkForWinner(players).getName());
        System.out.println("Scores: " + players[0].getName() + ": " + players[0].getScore() +
                ", " + players[1].getName() + ": " + players[1].getScore() +
                ", " + players[2].getName() + ": " + players[2].getScore());
    }

    public void resetPreviouslyMovedTile(Player[] players, ArrayList<Tile> tiles, ArrayList<ArrayList<Tile>> melds)
    {
        for (Player p : players)
        {
            for (Tile t: p.getHand())
            {
                t.setLastUsed(false);
                t.reuse = false;
            }
        }

        for (Tile t: tiles)
        {
            t.setLastUsed(false);
            t.reuse = false;
        }

        for (ArrayList<Tile> m: melds)
        {
            for (Tile t: m)
            {
                t.setLastUsed(false);
                t.reuse = false;
            }
        }
    }

    public Player checkForWinner(Player[] players) {
        for (Player p: players)
        {
            if (p.getHand().size() == 0)
            {
                p.setWinner(true);
                return p;
            }
        }
        return null;
    }
}
