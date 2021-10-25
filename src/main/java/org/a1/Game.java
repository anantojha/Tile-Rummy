package org.a1;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class Game implements Serializable {

    private static final long serialVersionUID = 1L;
    ArrayList<String> sets;
    ArrayList<String> runs;

    public Game(){
        sets = new ArrayList<>();
        runs = new ArrayList<>();
        createValidSets();
        createValidRuns();
    }

    public ArrayList<Tile> generateJokerReplacement() {
        ArrayList<Tile> tiles = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            addTiles("R", tiles);
            addTiles("B", tiles);
            addTiles("G", tiles);
            addTiles("O", tiles);
        }
        return tiles;
    }

    public ArrayList<Tile> generateTiles() {
        ArrayList<Tile> tiles = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            addTiles("R", tiles);
            addTiles("B", tiles);
            addTiles("G", tiles);
            addTiles("O", tiles);
        }
        Tile jokerOne = new Tile(0, "J");
        Tile jokerTwo = new Tile(0, "J");
        tiles.add(jokerOne);
        tiles.add(jokerTwo);
        Collections.shuffle(tiles);
        return tiles;
    }

    private void addTiles(String colour, ArrayList<Tile> tiles) {
        for (int i = 0; i < 13; i++) {
            tiles.add(new Tile(i + 1, colour));
        }
    }

    public ArrayList<Tile> createPlayerHands(Player[] players, ArrayList<Tile> tiles) {
        players[0].setHand(new ArrayList<>(tiles.subList(0, 14)));
        players[1].setHand(new ArrayList<>(tiles.subList(14, 28)));
        players[2].setHand(new ArrayList<>(tiles.subList(28, 42)));
        return new ArrayList<>(tiles.subList(42, 106));
    }

    public Tile drawNewTile(Player p, int n, String c, ArrayList<Tile> tiles) {
        for (int i = 0; i < tiles.size(); i++) {
            if (tiles.get(i).getNumber() == n) {
                if (tiles.get(i).getColour().equals(c)) {
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

    public void drawNewTile(Player p, ArrayList<Tile> tiles) {
        Tile tile = tiles.get(0);
        tiles.remove(0);
        tile.setLastUsed(true);
        p.getPlayer().getHand().add(tile);
    }

    public void drawNewTiles(Player p, int n, ArrayList<Tile> tiles) {
        for (int i = 0; i < n; i++) {
            Tile tile = tiles.get(0);
            tiles.remove(0);
            tile.setLastUsed(true);
            p.getPlayer().getHand().add(tile);
        }
    }

    public ArrayList<ArrayList<Tile>> convertMeldInputToTiles(String[] in) {
        String[] temp;
        ArrayList<ArrayList<Tile>> melds = new ArrayList<>();
        for (String s : in) {
            temp = s.split(",");
            ArrayList<Tile> meld = new ArrayList<>();
            for (String v : temp) {
                if (v.contains("*")) {
                    Tile t;
                    if (v.contains("**")) {
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

    public Tile getTileFromTable(int n, String c, int r, boolean remove, ArrayList<ArrayList<Tile>> melds) {
        ArrayList<Pair> found = new ArrayList<>();
        for (int i = 0; i < melds.size(); i++) {
            for (int j = 0; j < melds.get(i).size(); j++) {
                if (melds.get(i).get(j).getNumber() == n) {
                    if (melds.get(i).get(j).getColour().equals(c)) {
                        found.add(new Pair(i, j));
                    }
                }
            }
        }
        if (remove) {
            Tile temp = new Tile(n, c);
            melds.get(found.get(r).getX()).remove(found.get(r).getY());
            return temp;
        } else {
            return null;
        }
    }

    public boolean initialMeldsAtLeastThirty(Player player, ArrayList<ArrayList<Tile>> melds) {
        if (player.initialThirty) {
            return true;
        }

        int jokerCounter = 0;
        int counter = 0;

        ArrayList<Tile> uniqueTiles = generateJokerReplacement();
        ArrayList<Tile> found = new ArrayList<>();

        for (ArrayList<Tile> meld : melds) {
            if(meldContainsJoker(meld)){
                for (Tile tile : meld) {
                    if (tile.getColour().equals("J") && tile.getNumber() == 0) {
                        if(!tile.reuse){
                            for(Tile t : uniqueTiles){
                                if(validateJokerMeld(meld, t)){
                                    found.add(t);
                                }
                            }
                            if(found.size() == 1){
                                if(found.get(0).getNumber() > 10){
                                    counter += 10;
                                } else {
                                    counter += found.get(0).getNumber();
                                }
                            } else if (found.size() == 2){
                                if(found.get(1).getNumber() > 10){
                                    counter += 10;
                                } else {
                                    counter += found.get(1).getNumber();
                                }
                            } else {
                                counter += 0;
                            }
                        }
                    } else {
                        if (!tile.reuse) {
                            counter += tile.getPoints();
                        }
                    }
                }
            } else {
                for (Tile tile : meld) {
                    if (!tile.reuse) {
                        counter += tile.getPoints();
                    }
                }
            }
        }
        return counter >= 30;
    }

    public boolean checkPlayerHasTiles(ArrayList<Tile> hand, ArrayList<ArrayList<Tile>> melds) {
        Boolean found;
        for (ArrayList<Tile> m : melds) {
            for (Tile t : m) {
                found = false;
                for (Tile h : hand) {
                    if (t.getNumber() == h.getNumber() && t.getColour().equals(h.getColour())) {
                        found = true;
                    }
                }
                if (!found) {
                    return false;
                }
            }
        }
        return true;
    }

    public void createValidSets(){
        for (int i = 0; i < 13; i++) {
            sets.add("R" + (i+1) + "B" + (i+1) + "G" + (i+1) + "O" + (i+1));
        }
        for (int j = 0; j < 4; j++) {
            for(int i = 0; i < 13; i++) {
                if (j == 0) {
                    sets.add("R" + (i+1) + "B" + (i+1) + "G" + (i+1));
                }
                if (j == 1) {
                    sets.add("R" + (i+1) + "G" + (i+1) + "O" + (i+1));
                }
                if (j == 2) {
                    sets.add("R" + (i+1) + "B" + (i+1) + "O" + (i+1));
                }
                if (j == 3) {
                    sets.add("B" + (i+1) + "G" + (i+1) + "O" + (i+1));
                }
            }
        }
    }

    public Boolean validIfValidSet(ArrayList<Tile> meld) {
        ArrayList<Tile> sorted = new ArrayList();
        ArrayList<ArrayList<Tile>> tmp = sortTiles(meld);
        for(ArrayList<Tile> m : tmp){
            sorted.addAll(m);
        }
        String searchString = "";
        for(int i = 0; i < meld.size(); i++){
            searchString += sorted.get(i).getColour() + sorted.get(i).getNumber();
        }

        if(sets.contains(searchString)){
            return true;
        }
        return false;
    }

    public Boolean validIfValidRun(ArrayList<Tile> meld) {
        ArrayList<Tile> sorted = new ArrayList();
        ArrayList<ArrayList<Tile>> tmp = sortTiles(meld);
        for(ArrayList<Tile> m : tmp){
            sorted.addAll(m);
        }
        String searchString = "";
        for(int i = 0; i < meld.size(); i++){
            searchString += sorted.get(i).getColour() + sorted.get(i).getNumber();
        }

        if(runs.contains(searchString)){
            return true;
        }
        return false;
    }

    public void createRunString(int cr, int xr, int yr){
        String[] colors =  {"R","B","G","O"};
        for (int c = 0; c < cr; c++) {
            for (int x = 0; x < xr; x++) {
                String s = "";
                for (int y = 0; y < yr; y++) {
                    s += colors[c] + (x+y+1) ;
                }
                runs.add(s);
            }
        }
    }
    public void createValidRuns() {
        createRunString(4,11,3);
        createRunString(4,10,4);
        createRunString(4,9,5);
        createRunString(4,8,6);
        createRunString(4,7,7);
        createRunString(4,6,8);
        createRunString(4,5,9);
        createRunString(4,4,10);
        createRunString(4,3,11);
        createRunString(4,2,12);
        createRunString(4,1,13);
    }

    public Boolean meldContainsJoker(ArrayList<Tile> meld){
        for(Tile t : meld){
            if (t.getColour().equals("J")){
                return true;
            }
        }
        return false;
    }

    public boolean validateJokerMeld(ArrayList<Tile> meld, Tile potential){
        ArrayList<Tile> noJoker = new ArrayList<>();
        int jokerIndex = -1;
        for(int i = 0; i < meld.size(); i++){
            if(!meld.get(i).getColour().equals("J")){
                noJoker.add(new Tile(meld.get(i).getNumber(), meld.get(i).getColour()));
            } else {
                jokerIndex = i;
            }
        }

        noJoker.add(potential);

        if(validIfValidSet(noJoker) || validIfValidRun(noJoker)) {
            meld.get(jokerIndex).setDesiredTile(potential);
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkJokerCombinations(ArrayList<Tile> meld){
        if(!meldContainsJoker(meld)){
            return false;
        }
        ArrayList<Tile> uniqueTiles = generateJokerReplacement();
        ArrayList<Boolean> found = new ArrayList();
        for(Tile t : uniqueTiles){
            if(validateJokerMeld(meld, t)){
                found.add(true);
            }
        }
        return found.size() != 0;
    }

    public boolean validateMelds(ArrayList<ArrayList<Tile>> melds) {
        for (int j = 0; j < melds.size(); j++) {
            if (melds.get(j).size() == 0) {
                return false;
            }

            if(!(validIfValidSet(melds.get(j)) || validIfValidRun(melds.get(j)) || checkJokerCombinations(melds.get(j)))) {
                return false;
            }
        }
        return true;
    }

    public Boolean playMelds(Player player, ArrayList<ArrayList<Tile>> meld, ArrayList<ArrayList<Tile>> inMeld) {
        if (!(initialMeldsAtLeastThirty(player, inMeld) && checkPlayerHasTiles(player.getHand(), inMeld))) {
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

        for (ArrayList<Tile> m : inMeld) {
            newHandOffset = new ArrayList<>();


            for (Tile t : m) {
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
                    if (temp != null) {
                        temp.setReuse(true);
                        newHandOffset.add(temp);
                    } else {
                        return false;
                    }
                }
            }

            newMelds.add(newHandOffset);
        }

        for (ArrayList<Tile> group : newMelds) {
            resetMeld.add(group);
        }

        resetMeld.removeIf(ArrayList::isEmpty);

        if (validateMelds(resetMeld)) {
            meld.clear();
            meld.addAll(resetMeld);
            for (ArrayList<Tile> m : meld) {
                for (Tile t : m) {
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
        return false;
    }

    public void printPlayerHands(Player[] players) {
        for (Player p : players) {
            printHand(p);
        }
    }

    public void printHand(Player p) {
        printSortedHand(p.getHand(), p.getName());
    }

    public ArrayList<ArrayList<Tile>> sortTiles(ArrayList<Tile> tiles) {
        ArrayList<Tile> sortedHandRed = new ArrayList<>();
        ArrayList<Tile> sortedHandBlue = new ArrayList<>();
        ArrayList<Tile> sortedHandGreen = new ArrayList<>();
        ArrayList<Tile> sortedHandOrange = new ArrayList<>();
        ArrayList<Tile> sortedHandJoker = new ArrayList<>();

        for (Tile t : tiles) {
            if (t.getColour().equals("R")) {
                sortedHandRed.add(t);
            } else if (t.getColour().equals("B")) {
                sortedHandBlue.add(t);
            } else if (t.getColour().equals("G")) {
                sortedHandGreen.add(t);
            } else if (t.getColour().equals("O")) {
                sortedHandOrange.add(t);
            } else {
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
        return result;
    }

    public void printSortedHand(ArrayList<Tile> tiles, String playerName) { // implement sort order RBGO 1-13

        ArrayList<ArrayList<Tile>> sortedTiles = sortTiles(tiles);

        System.out.println("|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("|  Hand of player : " + playerName);

        for (ArrayList<Tile> sortedSingleColourTiles : sortedTiles) {
            printTiles(sortedSingleColourTiles, false);
        }

        System.out.println();
        System.out.println(
                "|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");

    }

    public void printMelds(ArrayList<ArrayList<Tile>> melds) {
        System.out.println(
                "|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("|  Melds: ");
        for (ArrayList<Tile> meld : melds) {
            System.out.print("{");
            ArrayList<ArrayList<Tile>> sortedTiles = sortTiles(meld);
            for (ArrayList<Tile> tiles : sortedTiles) {
                printTiles(tiles, false);
            }
            System.out.print("}, ");
        }
        System.out.println();
        System.out.println(
                "|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
    }

    public void printTiles(ArrayList<Tile> tiles, boolean line) {
        for (Tile t : tiles) {
            if (t.getLastUsed()) {
                System.out.print("| " + t.getColour() + t.getNumber() + "* | ");
            } else if (t.reuse == true) {
                System.out.print("| " + t.getColour() + t.getNumber() + "! | ");
            } else {
                System.out.print("| " + t.getColour() + t.getNumber() + " | ");
            }
        }
        if (line) {
            System.out.println();
        }
    }

    public void printRemainingTiles(ArrayList<Tile> tiles) {
        ArrayList<ArrayList<Tile>> sortedTiles = sortTiles(tiles);

        System.out.println(
                "|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("|  Remaining Tiles on table (faced down):  " + tiles.size() + " Tiles ");
        for (ArrayList<Tile> t : sortedTiles) {
            printTiles(t, true);
        }
        System.out.println(
                "|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");

    }

    public void printWinner(Player[] players) {
        System.out.println("Winner: Player " + checkForWinner(players).getName());
        System.out.println("Scores: " + players[0].getName() + ": " + players[0].getScore() +
                ", " + players[1].getName() + ": " + players[1].getScore() +
                ", " + players[2].getName() + ": " + players[2].getScore());
    }

    public void resetPreviouslyMovedTile(Player[] players, ArrayList<Tile> tiles, ArrayList<ArrayList<Tile>> melds) {
        for (Player p : players) {
            for (Tile t : p.getHand()) {
                t.setLastUsed(false);
                t.reuse = false;
            }
        }

        for (Tile t : tiles) {
            t.setLastUsed(false);
            t.reuse = false;
        }

        for (ArrayList<Tile> m : melds) {
            for (Tile t : m) {
                t.setLastUsed(false);
                t.reuse = false;
            }
        }
    }

    public Player checkForWinner(Player[] players) {
        for (Player p : players) {
            if (p.getHand().size() == 0) {
                p.setWinner(true);
                return p;
            }
        }
        return null;
    }


}

