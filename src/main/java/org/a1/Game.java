package org.a1;

import java.io.Serializable;
import java.security.KeyPair;
import java.util.*;
import java.util.stream.Collectors;

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
        Tile jokerOne = new Tile(0,"J");
        Tile jokerTwo = new Tile(0,"J");
        tiles.add(jokerOne);
        tiles.add(jokerTwo);
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
        return new ArrayList<>(tiles.subList(42, 106));
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
                    Tile t;
                    if(v.contains("**")){
                        t = new Tile(Integer.parseInt(v.substring(3)), v.substring(2, 3));
                        t.reuse = true;
                        t.setReuseIndex(1);
                    } else {
                        t = new Tile(Integer.parseInt(v.substring(2)), v.substring(1, 2));
                        t.reuse = true;
                        t.setReuseIndex(0);
                    }
                    meld.add(t);
                } else {
                    meld.add(new Tile(Integer.parseInt(v.substring(1)), v.substring(0, 1)));
                }
            }
            melds.add(meld);
        }

        return melds;
    }

    public Tile getTileFromTable(int n, String c, int r, boolean remove, ArrayList<ArrayList<Tile>> melds)
    {
        ArrayList<Pair> found = new ArrayList<>();
        for (int i = 0; i < melds.size(); i++)
        {
            for (int j = 0; j<melds.get(i).size(); j++)
            {
                if(melds.get(i).get(j).getNumber() == n)
                {
                    if(melds.get(i).get(j).getColour().equals(c))
                    {
                        found.add(new Pair(i,j));
                    }
                }
            }
        }
        if(remove){
            Tile temp = new Tile(n, c);
            melds.get(found.get(r).getX()).remove(found.get(r).getY());
            return temp;
        } else {
            return null;
        }
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
                if (!tile.reuse) {
                    counter += tile.getNumber();
                }
            }
        }

        return counter >= 30;
    }

    public boolean checkPlayerHasTiles(ArrayList<Tile> hand, ArrayList<ArrayList<Tile>> melds){
        Boolean found;
        for (ArrayList<Tile> m : melds){
            for(Tile t: m){
                found = false;
                for(Tile h: hand){
                    if(t.getNumber() == h.getNumber() && t.getColour().equals(h.getColour())){
                        found = true;
                    }
                }
                if(!found){
                    return false;
                }
            }
        }
        return true;
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

    public static boolean consecutive(int a, int b, int c) {
        int min = Math.min(a, Math.min(b, c));
        int max = Math.max(a, Math.max(b, c));
        return max - min == 2 && a != b && a != c && b != c;
    }

    public ArrayList<Pair> lookForSplits(int[] numbers, int meldIndex){
        ArrayList<Pair> splits = new ArrayList<>();
        for(int i=0; i<numbers.length-1; i++){
            int start = i;
            int size = 1;
            int end = start;
            if(i<numbers.length-2){
                end = start+1;
                while(numbers[end] == numbers[end-1]+1){
                    size++;
                    end++;
                    if(end >= numbers.length)
                        break;
                }
            }
            if(size >=3){
                Pair p = new Pair(meldIndex,end-1);
                p.setSize(size);
                p.setStart(end-size);
                p.setEnd(end-1);
                splits.add(p);
                i = end-1;
            }
        }
        return splits;
    }

    public String[] getMeldColours(ArrayList<Tile> meld){
        String[] colors = new String[meld.size()];
        for(int i = 0; i < meld.size(); i++){
            colors[i] = meld.get(i).getColour();
        }
        return colors;
    }

    public int[] getMeldNumbers(ArrayList<Tile> meld){
        int[] numbers = new int[meld.size()];
        for(int i = 0; i < meld.size(); i++){
            numbers[i] = meld.get(i).getNumber();
        }
        return numbers;
    }


    public boolean validateMelds(ArrayList<ArrayList<Tile>> melds) {
        Boolean isValid = false;
        for(int j =0; j< melds.size(); j++){
            if (melds.get(j).size() == 0){
                return false;
            }

            String[] colors = getMeldColours(melds.get(j));
            int[] numbers = getMeldNumbers(melds.get(j));

            if(setColors(colors) && setNumbers(numbers) || runColors(colors) && runNumbers(numbers)) {
                if (setColors(colors) && setNumbers(numbers)) {
                    if (melds.get(j).size() == 3 || melds.get(j).size() == 4) {
                        isValid = true;
                    } else {
                        isValid = false;
                        break;
                    }
                }

                if (runColors(colors) && runNumbers(numbers)) {
                    if (melds.get(j).size() > 2) {
                        isValid = true;
                    } else {
                        isValid = false;
                        break;
                    }
                }
            } else {
                return false;
            }
        }
        return isValid;
    }

    public Boolean validateReuse(){
        return true;
    }

    public Boolean playMelds(Player player, ArrayList<ArrayList<Tile>> meld, ArrayList<ArrayList<Tile>> inMeld)
    {
        if (!initialMeldsAtLeastThirty(player, inMeld) && checkPlayerHasTiles(player.getHand(), inMeld))
        {
            return false;
        } else {
            player.initialThirty = true;
        }

        ArrayList<ArrayList<Tile>> resetMeld = meld.stream().map(ArrayList::new).collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Tile> resetHand = new ArrayList<>();
        Iterator<Tile> it = player.hand.iterator();
        while (it.hasNext()) {
            Tile s = it.next();
            Tile newS = new Tile(s.getNumber(), s.getColour());
            resetHand.add(newS);
        }

        ArrayList<Tile> newHandOffset;
        ArrayList<ArrayList<Tile>> newMelds = new ArrayList<>();

        for (ArrayList<Tile> m: inMeld)
        {
            newHandOffset = new ArrayList<>();


            for (Tile t: m) {
                if (!t.reuse) {
                    Tile temp = player.getTileFromhand(t.getNumber(), t.getColour(), true);
                    if (temp != null) {
                        temp.setLastUsed(true);
                        newHandOffset.add(temp);
                    } else {
                        return false;
                    }
                } else {
                    Tile temp = getTileFromTable(t.getNumber(), t.getColour(), t.getReuseIndex(), true, resetMeld);
                    if(temp != null){
                            temp.setReuse(true);
                            newHandOffset.add(temp);
                    } else {
                        return false;
                    }
                }
            }

            newMelds.add(newHandOffset);
        }

        for (ArrayList<Tile> group: newMelds)
        {
            resetMeld.add(group);
        }

        resetMeld.removeIf(ArrayList::isEmpty);

        if(validateMelds(resetMeld)) {
            meld.clear();
            meld.addAll(resetMeld);
            for(ArrayList<Tile> m: meld){
                for(Tile t: m){
                    Tile n = new Tile(t.getNumber(), t.getColour());
                    t = n;
                }
            }
            return true;
        } else {
            ArrayList<Pair> splits = new ArrayList<>();
            for(int j = 0; j < resetMeld.size(); j++) {
                if (!(setColors(getMeldColours(resetMeld.get(j))) && setNumbers(getMeldNumbers(resetMeld.get(j))))) {
                    if (runColors(getMeldColours(resetMeld.get(j)))) {
                        splits.addAll(lookForSplits(getMeldNumbers(resetMeld.get(j)), j));
                    }
                }
            }
            ArrayList<ArrayList<Tile>> splitted = new ArrayList<>();
            ArrayList<Tile> split;
            for(int k = 0; k<splits.size()-1; k++){
                split = new ArrayList<>();
                for(int z = splits.get(k).getStart(); z< splits.get(k).getEnd()+1; z++){
                    Tile t = new Tile(resetMeld.get(splits.get(k).getX()).get(z).getNumber(), resetMeld.get(splits.get(k).getX()).get(z).getColour());
                    t.reuse = true;
                    split.add(t);
                }
                splitted.add(split);
            }

            resetMeld.remove(splits.get(0).getX());
            resetMeld.addAll(splitted);

            if(validateMelds(resetMeld)){
                meld.clear();
                meld.addAll(resetMeld);
                for(ArrayList<Tile> m: meld){
                    for(Tile t: m){
                        Tile n = new Tile(t.getNumber(), t.getColour());
                        t = n;
                    }
                }
                return true;
            } else {
                player.hand.clear();
                it = resetHand.iterator();
                while (it.hasNext()) {
                    Tile s = it.next();
                    Tile newS = new Tile(s.getNumber(), s.getColour());
                    player.hand.add(newS);
                }
            }
        }
        return false;
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
        ArrayList<Tile> sortedHandJoker = new ArrayList<>();

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
            else if(t.getColour().equals("O"))
            {
                sortedHandOrange.add(t);
            }
            else {
                sortedHandJoker.add(t);
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
        sortedHandJoker.sort(Comparator.comparingInt(Tile::getNumber));
        result.add(sortedHandJoker);
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
