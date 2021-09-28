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
}
