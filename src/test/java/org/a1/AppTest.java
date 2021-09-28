package org.a1;

import junit.framework.TestCase;

import static org.junit.Assert.assertTrue;


public class AppTest extends TestCase {

    Game game;

    public AppTest() {
        game = new Game();
    }

    /*
     *  Test Case: Player draws a new tile from board
     */
    public void testDrawingANewTileCase1() {
        GameServer gs = new GameServer(true);
        Player p1 = new Player("P1");
        gs.players[0] = p1;
        gs.tiles = game.generateTiles(); // generate all tiles

        // create player 1s hand - {B2,R2,O2,B3,R2,O3,O8,O9,O10,R8,R9,R10} B12,R7
        game.drawNewTile(p1, 2, "B", gs.tiles);
        game.drawNewTile(p1, 2, "R", gs.tiles);
        game.drawNewTile(p1, 2, "O", gs.tiles);
        game.drawNewTile(p1, 3, "B", gs.tiles);
        game.drawNewTile(p1, 3, "R", gs.tiles);
        game.drawNewTile(p1, 3, "O", gs.tiles);
        game.drawNewTile(p1, 8, "O", gs.tiles);
        game.drawNewTile(p1, 9, "O", gs.tiles);
        game.drawNewTile(p1, 10, "O", gs.tiles);
        game.drawNewTile(p1, 8, "R", gs.tiles);
        game.drawNewTile(p1, 9, "R", gs.tiles);
        game.drawNewTile(p1, 10, "R", gs.tiles);
        game.drawNewTile(p1, 12, "B", gs.tiles);
        game.drawNewTile(p1, 7, "R", gs.tiles);

        assertTrue(p1.getHand().size() == 14);   // player 1 hand starts with 14 tiles
        assertTrue(gs.tiles.size() == 90);       // 90 unused tiles on table
        game.drawNewTile(p1, gs.tiles);                   // draw a new tile from the table (randomly drawn, as in a real game)
        assertTrue(p1.getHand().size() == 15);   // player 1 hand is updated (15 tiles in hand)
        assertTrue(gs.tiles.size() == 89);       // unused tiles updated
    }

    /*
     *  Test Case: Initial meld(s) equal at least 30 points
     */
    public void testInitial30PointsCase1() {
        GameServer gs = new GameServer(true);
        Player p1 = new Player("P1");
        gs.players[0] = p1;
        gs.tiles = game.generateTiles(); // generate all tiles

        // create player 1s hand - {R11,R12,R13} and 11 random tiles
        game.drawNewTile(p1, 11, "R", gs.tiles);
        game.drawNewTile(p1, 12, "R", gs.tiles);
        game.drawNewTile(p1, 13, "R", gs.tiles);
        game.drawNewTiles(p1, 11, gs.tiles);

        assertTrue(p1.getHand().size() == 14);   // player 1s hand has 14 tiles
        assertTrue(game.initialMeldsAtLeastThirty(p1, game.convertMeldInputToTiles(p1.processInputMelds("{R11,R12,R13}")))); // Check for initial meld being at least 30 points
    }

    /*
     *  Test Case: Initial meld(s) equal at least 30 points
     */
    public void testInitial30PointsCase2() {
        GameServer gs = new GameServer(true);
        Player p1 = new Player("P1");
        gs.players[0] = p1;
        gs.tiles = game.generateTiles(); // generate all tiles

        // create player 1s hand - {R12,B12,G12} and 11 random tiles
        game.drawNewTile(p1, 12, "R", gs.tiles);
        game.drawNewTile(p1, 12, "B", gs.tiles);
        game.drawNewTile(p1, 12, "G", gs.tiles);
        game.drawNewTiles(p1, 11, gs.tiles);

        assertTrue(p1.getHand().size() == 14);   // player 1s hand has 14 tiles
        assertTrue(game.initialMeldsAtLeastThirty(p1, game.convertMeldInputToTiles(p1.processInputMelds("{R12,B12,G12}")))); // Check for initial meld being at least 30 points
    }

    /*
     *  Test Case: Initial meld(s) equal at least 30 points
     */
    public void testInitial30PointsCase3() {
        GameServer gs = new GameServer(true);
        Player p1 = new Player("P1");
        gs.players[0] = p1;
        gs.tiles = game.generateTiles(); // generate all tiles

        // create player 1s hand - {R9,R10,R11,R12,R13} and 9 random tiles
        game.drawNewTile(p1, 9, "R", gs.tiles);
        game.drawNewTile(p1, 10, "R", gs.tiles);
        game.drawNewTile(p1, 11, "R", gs.tiles);
        game.drawNewTile(p1, 12, "R", gs.tiles);
        game.drawNewTile(p1, 13, "R", gs.tiles);
        game.drawNewTiles(p1, 9, gs.tiles);

        assertTrue(p1.getHand().size() == 14);   // player 1s hand has 14 tiles
        assertTrue(game.initialMeldsAtLeastThirty(p1, game.convertMeldInputToTiles(p1.processInputMelds("{R9,R10,R11,R12,R13}")))); // Check for initial meld being at least 30 points
    }

    /*
     *  Test Case: Initial meld(s) equal at least 30 points
     */
    public void testInitial30PointsCase4() {
        GameServer gs = new GameServer(true);
        Player p1 = new Player("P1");
        gs.players[0] = p1;
        gs.tiles = game.generateTiles(); // generate all tiles

        // create player 1s hand - {R13,B13,G13,O13} and 10 random tiles
        game.drawNewTile(p1, 13, "R", gs.tiles);
        game.drawNewTile(p1, 13, "B", gs.tiles);
        game.drawNewTile(p1, 13, "G", gs.tiles);
        game.drawNewTile(p1, 13, "O", gs.tiles);
        game.drawNewTiles(p1, 10, gs.tiles);

        assertTrue(p1.getHand().size() == 14);   // player 1s hand has 14 tiles
        assertTrue(game.initialMeldsAtLeastThirty(p1, game.convertMeldInputToTiles(p1.processInputMelds("{R13,B13,G13,O13}")))); // Check for initial meld being at least 30 points
    }

    /*
     *  Test Case: Initial meld(s) equal at least 30 points
     */
    public void testInitial30PointsCase5() {
        GameServer gs = new GameServer(true);
        Player p1 = new Player("P1");
        gs.players[0] = p1;
        gs.tiles = game.generateTiles(); // generate all tiles

        // create player 1s hand - {R2,R3,R4}{G7,G8,G9} and 8 random tiles
        game.drawNewTile(p1, 2, "R", gs.tiles);
        game.drawNewTile(p1, 3, "R", gs.tiles);
        game.drawNewTile(p1, 4, "R", gs.tiles);
        game.drawNewTile(p1, 7, "G", gs.tiles);
        game.drawNewTile(p1, 8, "G", gs.tiles);
        game.drawNewTile(p1, 9, "G", gs.tiles);
        game.drawNewTiles(p1, 8, gs.tiles);

        assertTrue(p1.getHand().size() == 14);   // player 1s hand has 14 tiles
        assertTrue(game.initialMeldsAtLeastThirty(p1, game.convertMeldInputToTiles(p1.processInputMelds("{R2,R3,R4}{G7,G8,G9}")))); // Check for initial meld being at least 30 points
    }

    /*
     *  Test Case: Initial meld(s) equal at least 30 points
     */
    public void testInitial30PointsCase6() {
        GameServer gs = new GameServer(true);
        Player p1 = new Player("P1");
        gs.players[0] = p1;
        gs.tiles = game.generateTiles(); // generate all tiles

        // create player 1s hand - {R2,G2,O2}{B4,O4,R4}{O5,G5,R5} and 5 random tiles
        game.drawNewTile(p1, 2, "R", gs.tiles);
        game.drawNewTile(p1, 2, "G", gs.tiles);
        game.drawNewTile(p1, 2, "O", gs.tiles);
        game.drawNewTile(p1, 4, "B", gs.tiles);
        game.drawNewTile(p1, 4, "O", gs.tiles);
        game.drawNewTile(p1, 4, "R", gs.tiles);
        game.drawNewTile(p1, 5, "O", gs.tiles);
        game.drawNewTile(p1, 5, "G", gs.tiles);
        game.drawNewTile(p1, 5, "R", gs.tiles);
        game.drawNewTiles(p1, 5, gs.tiles);

        assertTrue(p1.getHand().size() == 14);   // player 1s hand has 14 tiles
        assertTrue(game.initialMeldsAtLeastThirty(p1, game.convertMeldInputToTiles(p1.processInputMelds("{R2,G2,O2}{B4,O4,R4}{O5,G5,R5}")))); // Check for initial meld being at least 30 points
    }

    /*
     *  Test Case: Initial meld(s) equal at least 30 points
     */
    public void testInitial30PointsCase7() {
        GameServer gs = new GameServer(true);
        Player p1 = new Player("P1");
        gs.players[0] = p1;
        gs.tiles = game.generateTiles(); // generate all tiles

        // create player 1s hand - {R8,G8,O8}{R2,R3,R4} and 8 random tiles
        game.drawNewTile(p1, 8, "R", gs.tiles);
        game.drawNewTile(p1, 8, "B", gs.tiles);
        game.drawNewTile(p1, 8, "O", gs.tiles);
        game.drawNewTile(p1, 2, "R", gs.tiles);
        game.drawNewTile(p1, 3, "R", gs.tiles);
        game.drawNewTile(p1, 4, "R", gs.tiles);
        game.drawNewTiles(p1, 8, gs.tiles);

        assertTrue(p1.getHand().size() == 14);   // player 1s hand has 14 tiles
        assertTrue(game.initialMeldsAtLeastThirty(p1, game.convertMeldInputToTiles(p1.processInputMelds("{R8,G8,O8}{R2,R3,R4}")))); // Check for initial meld being at least 30 points
    }

    /*
     *  Test Case: Initial meld(s) equal at least 30 points
     */
    public void testInitial30PointsCase8() {
        GameServer gs = new GameServer(true);
        Player p1 = new Player("P1");
        gs.players[0] = p1;
        gs.tiles = game.generateTiles(); // generate all tiles

        // create player 1s hand - {R2,O2,G2}{B2,B3,B4}{R3,G3,O3}{G5,G6,G7} and 8 random tiles
        game.drawNewTile(p1, 2, "R", gs.tiles);
        game.drawNewTile(p1, 2, "O", gs.tiles);
        game.drawNewTile(p1, 2, "G", gs.tiles);
        game.drawNewTile(p1, 2, "B", gs.tiles);
        game.drawNewTile(p1, 3, "B", gs.tiles);
        game.drawNewTile(p1, 4, "B", gs.tiles);
        game.drawNewTile(p1, 3, "R", gs.tiles);
        game.drawNewTile(p1, 3, "G", gs.tiles);
        game.drawNewTile(p1, 3, "O", gs.tiles);
        game.drawNewTile(p1, 5, "G", gs.tiles);
        game.drawNewTile(p1, 6, "G", gs.tiles);
        game.drawNewTile(p1, 7, "G", gs.tiles);
        game.drawNewTiles(p1, 2, gs.tiles);

        assertTrue(p1.getHand().size() == 14);   // player 1s hand has 14 tiles
        assertTrue(game.initialMeldsAtLeastThirty(p1, game.convertMeldInputToTiles(p1.processInputMelds("{R2,O2,G2}{B2,B3,B4}{R3,G3,O3}{G5,G6,G7}")))); // Check for initial meld being at least 30 points
    }

    /*
     *  Test Case: Initial meld(s) equal at least 30 points
     */
    public void testInitial30PointsCase9() {
        GameServer gs = new GameServer(true);
        Player p1 = new Player("P1");
        gs.players[0] = p1;
        gs.tiles = game.generateTiles(); // generate all tiles

        // create player 1s hand - {R2,O2,G2}{B2,B3,B4}{R3,G3,O3} and 8 random tiles
        game.drawNewTile(p1, 2, "R", gs.tiles);
        game.drawNewTile(p1, 2, "G", gs.tiles);
        game.drawNewTile(p1, 2, "B", gs.tiles);
        game.drawNewTile(p1, 2, "O", gs.tiles);
        game.drawNewTile(p1, 3, "B", gs.tiles);
        game.drawNewTile(p1, 4, "B", gs.tiles);
        game.drawNewTile(p1, 5, "B", gs.tiles);
        game.drawNewTile(p1, 6, "B", gs.tiles);
        game.drawNewTile(p1, 7, "B", gs.tiles);
        game.drawNewTile(p1, 4, "O", gs.tiles);
        game.drawNewTile(p1, 5, "O", gs.tiles);
        game.drawNewTile(p1, 6, "O", gs.tiles);
        game.drawNewTile(p1, 7, "O", gs.tiles);
        game.drawNewTile(p1, 8, "O", gs.tiles);

        assertTrue(p1.getHand().size() == 14);   // player 1s hand has 14 tiles
        assertTrue(game.initialMeldsAtLeastThirty(p1, game.convertMeldInputToTiles(p1.processInputMelds("{R2,G2,B2,O2}{B3,B4,B5,B6,B7}{O4,O5,O6,O7,O8}")))); // Check for initial meld being at least 30 points
    }
}
