package org.a1;

import junit.framework.TestCase;
import org.junit.Assert;

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
     *  Test Case: Player draws a new tile from board
     */
    public void testDrawingANewTileCase2() {
        GameServer gs = new GameServer(true);
        Player p1 = new Player("P1");
        gs.players[0] = p1;
        gs.tiles = game.generateTiles(); // generate all tiles

        // create player 1s hand - {B2,B2,O2,R3,G3,G3,R5,G6,O7,R9,R10,B11,G12,G13}
        game.drawNewTile(p1, 2, "B", gs.tiles);
        game.drawNewTile(p1, 2, "B", gs.tiles);
        game.drawNewTile(p1, 2, "O", gs.tiles);
        game.drawNewTile(p1, 3, "R", gs.tiles);
        game.drawNewTile(p1, 3, "G", gs.tiles);
        game.drawNewTile(p1, 3, "G", gs.tiles);
        game.drawNewTile(p1, 5, "R", gs.tiles);
        game.drawNewTile(p1, 6, "G", gs.tiles);
        game.drawNewTile(p1, 7, "O", gs.tiles);
        game.drawNewTile(p1, 9, "R", gs.tiles);
        game.drawNewTile(p1, 10, "R", gs.tiles);
        game.drawNewTile(p1, 11, "B", gs.tiles);
        game.drawNewTile(p1, 12, "G", gs.tiles);
        game.drawNewTile(p1, 13, "G", gs.tiles);

        assertTrue(p1.getHand().size() == 14);   // player 1 hand starts with 14 tiles
        assertTrue(gs.tiles.size() == 90);       // 90 unused tiles on table
        game.drawNewTile(p1, gs.tiles);                   // draw a new tile from the table
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

    /*
     *  Test Case: Playing Melds after Initial 30 points (turn #2)
     */
    public void testPlayingMeldsCase1() {
        // setup
        GameServer gs = new GameServer(true);
        Player p1 = new Player("P1");
        Player p2 = new Player("P2");
        Player p3 = new Player("P3");
        gs.players[0] = p1;
        gs.players[1] = p2;
        gs.players[2] = p3;
        gs.tiles = game.generateTiles(); // generate all tiles

        // create player 1s hand - {R11,R12,R13}{B2,B3,B4} and 8 random tiles
        game.drawNewTile(p1, 11, "R", gs.tiles);
        game.drawNewTile(p1, 12, "R", gs.tiles);
        game.drawNewTile(p1, 13, "R", gs.tiles);
        game.drawNewTile(p1, 2, "B", gs.tiles);
        game.drawNewTile(p1, 3, "B", gs.tiles);
        game.drawNewTile(p1, 4, "B", gs.tiles);

        // create player 2s hand - {G11,G12,G13} and 11 random tiles
        game.drawNewTile(p2, 11, "G", gs.tiles);
        game.drawNewTile(p2, 12, "G", gs.tiles);
        game.drawNewTile(p2, 13, "G", gs.tiles);

        // create player 3s hand - {O11,O12,O13} and 11 random tiles
        game.drawNewTile(p3, 11, "O", gs.tiles);
        game.drawNewTile(p3, 12, "O", gs.tiles);
        game.drawNewTile(p3, 13, "O", gs.tiles);

        game.drawNewTiles(p1, 8, gs.tiles);
        game.drawNewTiles(p2, 11, gs.tiles);
        game.drawNewTiles(p3, 11, gs.tiles);

        // P1 plays {R11,R12,R13}
        assertTrue(p1.getHand().size() == 14);   // player 1s hand has 14 tiles
        assertTrue(game.playMelds(p1, gs.melds, game.convertMeldInputToTiles(p1.processInputMelds("{R11,R12,R13}"))));  // Meld was played successfully
        assertTrue(p1.getHand().size() == 11);   // player 1 hand is updated
        assertTrue(gs.melds.size() == 1);        // melds are updated
        assertTrue(gs.tiles.size() == 62);       // unused tiles updated

        // P2 plays {G11,G12,G13}
        assertTrue(p2.getHand().size() == 14);   // player 2s hand has 14 tiles
        assertTrue(game.playMelds(p2, gs.melds, game.convertMeldInputToTiles(p2.processInputMelds("{G11,G12,G13}"))));  // Meld was played successfully (Initial 30 points)
        assertTrue(p2.getHand().size() == 11);   // player 2 hand is updated
        assertTrue(gs.melds.size() == 2);        // melds are updated
        assertTrue(gs.tiles.size() == 62);       // unused tiles updated

        // P3 plays {O11,O12,O13}
        assertTrue(p3.getHand().size() == 14);   // player 3s hand has 14 tiles
        assertTrue(game.playMelds(p3, gs.melds, game.convertMeldInputToTiles(p3.processInputMelds("{O11,O12,O13}"))));  // Meld was played successfully (Initial 30 points)
        assertTrue(p3.getHand().size() == 11);   // player 3 hand is updated
        assertTrue(gs.melds.size() == 3);        // melds are updated
        assertTrue(gs.tiles.size() == 62);       // unused tiles updated

        // test case: P1 plays {2C 3C 4C} for turn #2
        assertTrue(p1.getHand().size() == 11);   // player 1s hand has 11 tiles
        assertTrue(game.playMelds(p1, gs.melds, game.convertMeldInputToTiles(p1.processInputMelds("{B2,B3,B4}"))));  // Meld was played successfully
        assertTrue(p1.getHand().size() == 8);   // player 1 hand is updated
        assertTrue(gs.melds.size() == 4);        // melds are updated
        assertTrue(gs.tiles.size() == 62);       // unused tiles updated
    }

    /*
     *  Test Case: Playing Melds after Initial 30 points (turn #2)
     */
    public void testPlayingMeldsCase2() {
        // setup
        GameServer gs = new GameServer(true);
        Player p1 = new Player("P1");
        Player p2 = new Player("P2");
        Player p3 = new Player("P3");
        gs.players[0] = p1;
        gs.players[1] = p2;
        gs.players[2] = p3;
        gs.tiles = game.generateTiles(); // generate all tiles

        // create player 1s hand - {R11,R12,R13}{B2,B3,B4}{O8,O9,O10} and 5 random tiles
        game.drawNewTile(p1, 11, "R", gs.tiles);
        game.drawNewTile(p1, 12, "R", gs.tiles);
        game.drawNewTile(p1, 13, "R", gs.tiles);
        game.drawNewTile(p1, 2, "B", gs.tiles);
        game.drawNewTile(p1, 3, "B", gs.tiles);
        game.drawNewTile(p1, 4, "B", gs.tiles);
        game.drawNewTile(p1, 8, "O", gs.tiles);
        game.drawNewTile(p1, 9, "O", gs.tiles);
        game.drawNewTile(p1, 10, "O", gs.tiles);

        // create player 2s hand - {G11,G12,G13} and 11 random tiles
        game.drawNewTile(p2, 11, "G", gs.tiles);
        game.drawNewTile(p2, 12, "G", gs.tiles);
        game.drawNewTile(p2, 13, "G", gs.tiles);

        // create player 3s hand - {O11,O12,O13} and 11 random tiles
        game.drawNewTile(p3, 11, "O", gs.tiles);
        game.drawNewTile(p3, 12, "O", gs.tiles);
        game.drawNewTile(p3, 13, "O", gs.tiles);

        game.drawNewTiles(p1, 5, gs.tiles);
        game.drawNewTiles(p2, 11, gs.tiles);
        game.drawNewTiles(p3, 11, gs.tiles);

        // Player 1 plays: {R11,R12,R13}
        assertTrue(p1.getHand().size() == 14);   // player 1s hand has 14 tiles
        assertTrue(game.playMelds(p1, gs.melds, game.convertMeldInputToTiles(p1.processInputMelds("{R11,R12,R13}"))));  // Meld was played successfully
        assertTrue(p1.getHand().size() == 11);   // player 1 hand is updated
        assertTrue(gs.melds.size() == 1);        // melds are updated
        assertTrue(gs.tiles.size() == 62);       // unused tiles updated

        //Player 2 plays: {G11,G12,G13}
        assertTrue(p2.getHand().size() == 14);   // player 2s hand has 14 tiles
        assertTrue(game.playMelds(p2, gs.melds, game.convertMeldInputToTiles(p2.processInputMelds("{G11,G12,G13}"))));  // Meld was played successfully (Initial 30 points)
        assertTrue(p2.getHand().size() == 11);   // player 2 hand is updated
        assertTrue(gs.melds.size() == 2);        // melds are updated
        assertTrue(gs.tiles.size() == 62);       // unused tiles updated

        // Player 3 plays: {O11,O12,O13}
        assertTrue(p3.getHand().size() == 14);   // player 3s hand has 14 tiles
        assertTrue(game.playMelds(p3, gs.melds, game.convertMeldInputToTiles(p3.processInputMelds("{O11,O12,O13}"))));  // Meld was played successfully (Initial 30 points)
        assertTrue(p3.getHand().size() == 11);   // player 3 hand is updated
        assertTrue(gs.melds.size() == 3);        // melds are updated
        assertTrue(gs.tiles.size() == 62);       // unused tiles updated

        // test case: P1 plays {2C 3C 4C}{8D 9D 10D} for turn #2
        assertTrue(p1.getHand().size() == 11);   // player 1s hand has 11 tiles
        assertTrue(game.playMelds(p1, gs.melds, game.convertMeldInputToTiles(p1.processInputMelds("{B2,B3,B4}{O8,O9,O10}"))));  // Meld was played successfully
        assertTrue(p1.getHand().size() == 5);   // player 1 hand is updated
        assertTrue(gs.melds.size() == 5);        // melds are updated
        assertTrue(gs.tiles.size() == 62);       // unused tiles updated
    }

    /*
     *  Test Case: Playing Melds after Initial 30 points (turn #2)
     */
    public void testPlayingMeldsCase3() {
        // setup
        GameServer gs = new GameServer(true);
        Player p1 = new Player("P1");
        Player p2 = new Player("P2");
        Player p3 = new Player("P3");
        gs.players[0] = p1;
        gs.players[1] = p2;
        gs.players[2] = p3;
        gs.tiles = game.generateTiles(); // generate all tiles

        // create player 1s hand - {R11,R12,R13}{B2,R2,O2} and 8 random tiles
        game.drawNewTile(p1, 11, "R", gs.tiles);
        game.drawNewTile(p1, 12, "R", gs.tiles);
        game.drawNewTile(p1, 13, "R", gs.tiles);
        game.drawNewTile(p1, 2, "B", gs.tiles);
        game.drawNewTile(p1, 2, "R", gs.tiles);
        game.drawNewTile(p1, 2, "O", gs.tiles);

        // create player 2s hand - {G11,G12,G13} and 11 random tiles
        game.drawNewTile(p2, 11, "G", gs.tiles);
        game.drawNewTile(p2, 12, "G", gs.tiles);
        game.drawNewTile(p2, 13, "G", gs.tiles);

        // create player 3s hand - {O11,O12,O13} and 11 random tiles
        game.drawNewTile(p3, 11, "O", gs.tiles);
        game.drawNewTile(p3, 12, "O", gs.tiles);
        game.drawNewTile(p3, 13, "O", gs.tiles);

        game.drawNewTiles(p1, 8, gs.tiles)
        ;game.drawNewTiles(p2, 11, gs.tiles);
        game.drawNewTiles(p3, 11, gs.tiles);

        // Player 1 plays: {R11,R12,R13}
        assertTrue(p1.getHand().size() == 14);   // player 1s hand has 14 tiles
        assertTrue(game.playMelds(p1, gs.melds, game.convertMeldInputToTiles(p1.processInputMelds("{R11,R12,R13}"))));  // Meld was played successfully (Initial 30 points)
        assertTrue(p1.getHand().size() == 11);   // player 1 hand is updated
        assertTrue(gs.melds.size() == 1);        // melds are updated
        assertTrue(gs.tiles.size() == 62);       // unused tiles updated

        // P2 plays {G11,G12,G13}
        assertTrue(p2.getHand().size() == 14);   // player 2s hand has 14 tiles
        assertTrue(game.playMelds(p2, gs.melds, game.convertMeldInputToTiles(p2.processInputMelds("{G11,G12,G13}"))));  // Meld was played successfully (Initial 30 points)
        assertTrue(p2.getHand().size() == 11);   // player 2 hand is updated
        assertTrue(gs.melds.size() == 2);        // melds are updated
        assertTrue(gs.tiles.size() == 62);       // unused tiles updated

        // P3 plays {O11,O12,O13}
        assertTrue(p3.getHand().size() == 14);   // player 3s hand has 14 tiles
        assertTrue(game.playMelds(p3, gs.melds, game.convertMeldInputToTiles(p3.processInputMelds("{O11,O12,O13}"))));  // Meld was played successfully (Initial 30 points)
        assertTrue(p3.getHand().size() == 11);   // player 3 hand is updated
        assertTrue(gs.melds.size() == 3);        // melds are updated
        assertTrue(gs.tiles.size() == 62);       // unused tiles updated

        // test case: P1 plays {2C 2H 2D} for turn #2
        assertTrue(p1.getHand().size() == 11);   // player 1s hand has 11 tiles
        assertTrue(game.playMelds(p1, gs.melds, game.convertMeldInputToTiles(p1.processInputMelds("{B2,R2,O2}"))));  // Meld was played successfully
        assertTrue(p1.getHand().size() == 8);   // player 1 hand is updated
        assertTrue(gs.melds.size() == 4);        // melds are updated
        assertTrue(gs.tiles.size() == 62);       // unused tiles updated
    }

    /*
     *  Test Case: Playing Melds after Initial 30 points (turn #2)
     */
    public void testPlayingMeldsCase4() {
        // setup
        GameServer gs = new GameServer(true);
        Player p1 = new Player("P1");
        Player p2 = new Player("P2");
        Player p3 = new Player("P3");
        gs.players[0] = p1;
        gs.players[1] = p2;
        gs.players[2] = p3;
        gs.tiles = game.generateTiles(); // generate all tiles

        // create player 1s hand - {R11,R12,R13}{B2,R2,O2}{O8,R8,G8,B8} and 4 random tiles
        game.drawNewTile(p1, 11, "R", gs.tiles);
        game.drawNewTile(p1, 12, "R", gs.tiles);
        game.drawNewTile(p1, 13, "R", gs.tiles);
        game.drawNewTile(p1, 2, "B", gs.tiles);
        game.drawNewTile(p1, 2, "R", gs.tiles);
        game.drawNewTile(p1, 2, "O", gs.tiles);
        game.drawNewTile(p1, 8, "O", gs.tiles);
        game.drawNewTile(p1, 8, "R", gs.tiles);
        game.drawNewTile(p1, 8, "G", gs.tiles);
        game.drawNewTile(p1, 8, "B", gs.tiles);

        // create player 2s hand - {G11,G12,G13} and 11 random tiles
        game.drawNewTile(p2, 11, "G", gs.tiles);
        game.drawNewTile(p2, 12, "G", gs.tiles);
        game.drawNewTile(p2, 13, "G", gs.tiles);

        // create player 3s hand - {O11,O12,O13} and 11 random tiles
        game.drawNewTile(p3, 11, "O", gs.tiles);
        game.drawNewTile(p3, 12, "O", gs.tiles);
        game.drawNewTile(p3, 13, "O", gs.tiles);

        game.drawNewTiles(p1, 4, gs.tiles);
        game.drawNewTiles(p2, 11, gs.tiles);
        game.drawNewTiles(p3, 11, gs.tiles);

        // Player 1 plays: {R11,R12,R13}
        assertTrue(p1.getHand().size() == 14);   // player 1s hand has 14 tiles
        assertTrue(game.playMelds(p1, gs.melds, game.convertMeldInputToTiles(p1.processInputMelds("{R11,R12,R13}"))));  // Meld was played successfully (Initial 30 points)
        assertTrue(p1.getHand().size() == 11);   // player 1 hand is updated
        assertTrue(gs.melds.size() == 1);        // melds are updated
        assertTrue(gs.tiles.size() == 62);       // unused tiles updated

        // P2 plays {G11,G12,G13}
        assertTrue(p2.getHand().size() == 14);   // player 2s hand has 14 tiles
        assertTrue(game.playMelds(p2, gs.melds, game.convertMeldInputToTiles(p2.processInputMelds("{G11,G12,G13}"))));  // Meld was played successfully (Initial 30 points)
        assertTrue(p2.getHand().size() == 11);   // player 2 hand is updated
        assertTrue(gs.melds.size() == 2);        // melds are updated
        assertTrue(gs.tiles.size() == 62);       // unused tiles updated

        // P3 plays {O11,O12,O13}
        assertTrue(p3.getHand().size() == 14);   // player 3s hand has 14 tiles
        assertTrue(game.playMelds(p3, gs.melds, game.convertMeldInputToTiles(p3.processInputMelds("{O11,O12,O13}"))));  // Meld was played successfully (Initial 30 points)
        assertTrue(p3.getHand().size() == 11);   // player 3 hand is updated
        assertTrue(gs.melds.size() == 3);        // melds are updated
        assertTrue(gs.tiles.size() == 62);       // unused tiles updated

        // test case: P1 plays {2C 2H 2D} {8D 8H 8S 8C} for turn #2
        assertTrue(p1.getHand().size() == 11);   // player 1s hand has 11 tiles
        assertTrue(game.playMelds(p1, gs.melds, game.convertMeldInputToTiles(p1.processInputMelds("{B2,R2,O2}{O8,R8,G8,B8}"))));  // Meld was played successfully
        assertTrue(p1.getHand().size() == 4);   // player 1 hand is updated
        assertTrue(gs.melds.size() == 5);        // melds are updated
        assertTrue(gs.tiles.size() == 62);       // unused tiles updated
    }

    /*
     *  Test Case: Playing Melds after Initial 30 points (turn #2)
     */
    public void testPlayingMeldsCase5() {
        // setup
        GameServer gs = new GameServer(true);
        Player p1 = new Player("P1");
        Player p2 = new Player("P2");
        Player p3 = new Player("P3");
        gs.players[0] = p1;
        gs.players[1] = p2;
        gs.players[2] = p3;
        gs.tiles = game.generateTiles(); // generate all tiles

        // create player 1s hand - {R11,R12,R13}{B2,R2,O2}{O8,O9,O10} and 5 random tiles
        game.drawNewTile(p1, 11, "R", gs.tiles);
        game.drawNewTile(p1, 12, "R", gs.tiles);
        game.drawNewTile(p1, 13, "R", gs.tiles);
        game.drawNewTile(p1, 2, "B", gs.tiles);
        game.drawNewTile(p1, 2, "R", gs.tiles);
        game.drawNewTile(p1, 2, "O", gs.tiles);
        game.drawNewTile(p1, 8, "O", gs.tiles);
        game.drawNewTile(p1, 9, "O", gs.tiles);
        game.drawNewTile(p1, 10, "O", gs.tiles);

        // create player 2s hand - {G11,G12,G13} and 11 random tiles
        game.drawNewTile(p2, 11, "G", gs.tiles);
        game.drawNewTile(p2, 12, "G", gs.tiles);
        game.drawNewTile(p2, 13, "G", gs.tiles);

        // create player 3s hand - {O11,O12,O13} and 11 random tiles
        game.drawNewTile(p3, 11, "O", gs.tiles);
        game.drawNewTile(p3, 12, "O", gs.tiles);
        game.drawNewTile(p3, 13, "O", gs.tiles);

        game.drawNewTiles(p1, 5, gs.tiles);
        game.drawNewTiles(p2, 11, gs.tiles);
        game.drawNewTiles(p3, 11, gs.tiles);

        // Player 1 plays: {R11,R12,R13}
        assertTrue(p1.getHand().size() == 14);   // player 1s hand has 14 tiles
        assertTrue(game.playMelds(p1, gs.melds, game.convertMeldInputToTiles(p1.processInputMelds("{R11,R12,R13}"))));  // Meld was played successfully (Initial 30 points)
        assertTrue(p1.getHand().size() == 11);   // player 1 hand is updated
        assertTrue(gs.melds.size() == 1);        // melds are updated
        assertTrue(gs.tiles.size() == 62);       // unused tiles updated

        // P2 plays {G11,G12,G13}
        assertTrue(p2.getHand().size() == 14);   // player 2s hand has 14 tiles
        assertTrue(game.playMelds(p2, gs.melds, game.convertMeldInputToTiles(p2.processInputMelds("{G11,G12,G13}"))));  // Meld was played successfully (Initial 30 points)
        assertTrue(p2.getHand().size() == 11);   // player 2 hand is updated
        assertTrue(gs.melds.size() == 2);        // melds are updated
        assertTrue(gs.tiles.size() == 62);       // unused tiles updated

        // P3 plays {O11,O12,O13}
        assertTrue(p3.getHand().size() == 14);   // player 3s hand has 14 tiles
        assertTrue(game.playMelds(p3, gs.melds, game.convertMeldInputToTiles(p3.processInputMelds("{O11,O12,O13}"))));  // Meld was played successfully (Initial 30 points)
        assertTrue(p3.getHand().size() == 11);   // player 3 hand is updated
        assertTrue(gs.melds.size() == 3);        // melds are updated
        assertTrue(gs.tiles.size() == 62);       // unused tiles updated

        // test case: P1 plays {2C 2H 2D} {8D 9D 10D} for turn #2
        assertTrue(p1.getHand().size() == 11);   // player 1s hand has 11 tiles
        assertTrue(game.playMelds(p1, gs.melds, game.convertMeldInputToTiles(p1.processInputMelds("{B2,R2,O2}{O8,O9,O10}"))));  // Meld was played successfully
        assertTrue(p1.getHand().size() == 5);   // player 1 hand is updated
        assertTrue(gs.melds.size() == 5);        // melds are updated
        assertTrue(gs.tiles.size() == 62);       // unused tiles updated
    }

    /*
     *  Test Case: Playing Melds after Initial 30 points (turn #2)
     */
    public void testPlayingMeldsCase6() {
        // setup
        GameServer gs = new GameServer(true);
        Player p1 = new Player("P1");
        Player p2 = new Player("P2");
        Player p3 = new Player("P3");
        gs.players[0] = p1;
        gs.players[1] = p2;
        gs.players[2] = p3;
        gs.tiles = game.generateTiles(); // generate all tiles

        // create player 1s hand - {R11,R12,R13}{B2,R2,O2}{B3,R3,O3}{B8,B9,B10,B11,B12} and 8 random tiles
        game.drawNewTile(p1, 11, "R", gs.tiles);
        game.drawNewTile(p1, 12, "R", gs.tiles);
        game.drawNewTile(p1, 13, "R", gs.tiles);
        game.drawNewTile(p1, 2, "B", gs.tiles);
        game.drawNewTile(p1, 2, "R", gs.tiles);
        game.drawNewTile(p1, 2, "O", gs.tiles);
        game.drawNewTile(p1, 3, "B", gs.tiles);
        game.drawNewTile(p1, 3, "R", gs.tiles);
        game.drawNewTile(p1, 3, "O", gs.tiles);
        game.drawNewTile(p1, 8, "B", gs.tiles);
        game.drawNewTile(p1, 9, "B", gs.tiles);
        game.drawNewTile(p1, 10, "B", gs.tiles);
        game.drawNewTile(p1, 11, "B", gs.tiles);
        game.drawNewTile(p1, 12, "B", gs.tiles);

        // create player 2s hand - {G11,G12,G13} and 11 random tiles
        game.drawNewTile(p2, 11, "G", gs.tiles);
        game.drawNewTile(p2, 12, "G", gs.tiles);
        game.drawNewTile(p2, 13, "G", gs.tiles);

        // create player 3s hand - {O11,O12,O13} and 11 random tiles
        game.drawNewTile(p3, 11, "O", gs.tiles);
        game.drawNewTile(p3, 12, "O", gs.tiles);
        game.drawNewTile(p3, 13, "O", gs.tiles);

        game.drawNewTiles(p2, 11, gs.tiles);
        game.drawNewTiles(p3, 11, gs.tiles);

        // Player 1 plays: {R11,R12,R13}
        assertTrue(p1.getHand().size() == 14);   // player 1s hand has 14 tiles
        assertTrue(game.playMelds(p1, gs.melds, game.convertMeldInputToTiles(p1.processInputMelds("{R11,R12,R13}"))));  // Meld was played successfully (Initial 30 points)
        assertTrue(p1.getHand().size() == 11);   // player 1 hand is updated
        assertTrue(gs.melds.size() == 1);        // melds are updated (1 meld now on table)
        assertTrue(gs.tiles.size() == 62);       // unused tiles updated

        // P2 plays {G11,G12,G13}
        assertTrue(p2.getHand().size() == 14);   // player 2s hand has 14 tiles
        assertTrue(game.playMelds(p2, gs.melds, game.convertMeldInputToTiles(p2.processInputMelds("{G11,G12,G13}"))));  // Meld was played successfully (Initial 30 points)
        assertTrue(p2.getHand().size() == 11);   // player 2 hand is updated
        assertTrue(gs.melds.size() == 2);        // melds are updated
        assertTrue(gs.tiles.size() == 62);       // unused tiles updated

        // P3 plays {O11,O12,O13}
        assertTrue(p3.getHand().size() == 14);   // player 3s hand has 14 tiles
        assertTrue(game.playMelds(p3, gs.melds, game.convertMeldInputToTiles(p3.processInputMelds("{O11,O12,O13}"))));  // Meld was played successfully (Initial 30 points)
        assertTrue(p3.getHand().size() == 11);   // player 3 hand is updated
        assertTrue(gs.melds.size() == 3);        // melds are updated
        assertTrue(gs.tiles.size() == 62);       // unused tiles updated

        // test case: P1 plays {B2,R2,O2} {B3,R3,O3} {B8,B9,B10,B11,B12} for turn #2
        assertTrue(p1.getHand().size() == 11);   // player 1s hand has 11 tiles
        assertTrue(game.playMelds(p1, gs.melds, game.convertMeldInputToTiles(p1.processInputMelds("{B2,R2,O2}{B3,R3,O3}{B8,B9,B10,B11,B12}"))));  // Meld was played successfully
        assertTrue(p1.getHand().size() == 0);   // player 1 hand is updated
        assertTrue(gs.melds.size() == 6);        // melds are updated
        assertTrue(gs.tiles.size() == 62);       // unused tiles updated
    }

    /*
     *  Test Case: Start a new game and play a round
     *  - verify updates to player's hand, melds on table and unused tiles
     */
    public void testPlayingARound() {
        GameServer gs = new GameServer(true);
        Player p1 = new Player("P1");
        Player p2 = new Player("P2");
        Player p3 = new Player("P3");
        gs.players[0] = p1;
        gs.players[1] = p2;
        gs.players[2] = p3;
        gs.tiles = game.generateTiles(); // generate all tiles

        // create player 1s hand
        game.drawNewTile(p1, 12, "R", gs.tiles);
        game.drawNewTile(p1, 12, "G", gs.tiles);
        game.drawNewTile(p1, 12, "O", gs.tiles);

        // create player 2s hand
        game.drawNewTile(p2, 11, "R", gs.tiles);
        game.drawNewTile(p2, 12, "R", gs.tiles);
        game.drawNewTile(p2, 13, "R", gs.tiles);

        // create player 3s hand
        game.drawNewTile(p3, 13, "R", gs.tiles);
        game.drawNewTile(p3, 13, "G", gs.tiles);
        game.drawNewTile(p3, 13, "B", gs.tiles);
        game.drawNewTile(p3, 2, "B", gs.tiles);
        game.drawNewTile(p3, 2, "R", gs.tiles);
        game.drawNewTile(p3, 2, "O", gs.tiles);

        game.drawNewTiles(p1, 11, gs.tiles);           // create player 1s hand - {R12,G12,O12} and 11 random tiles
        game.drawNewTiles(p2, 11, gs.tiles);           // create player 2s hand - {R11,R12,R13} and 11 random tiles
        game.drawNewTiles(p3, 8, gs.tiles);            // create player 3s hand - {R13 G13 B13}{B2 R2 O2} and 8 random tiles

        /* Player 1 draws a new tile */
        game.drawNewTile(p1, gs.tiles);                   // draw a new tile from the table (random)
        assertTrue(p1.getHand().size() == 15);   // player 1 hand is updated (15 tiles in hand)
        assertTrue(gs.melds.size() == 0);        // melds are updated (melds still empty)
        assertTrue(gs.tiles.size() == 61);       // unused tiles updated

        game.resetPreviouslyMovedTile(gs.players, gs.tiles, gs.melds);   // clear '*' from tiles previously used.

        /* Player 2 plays a new meld {R11 R12 R13} */
        assertTrue(p2.getHand().size() == 14);   // player 2s hand has 14 tiles
        assertTrue(game.playMelds(p2, gs.melds, game.convertMeldInputToTiles(p2.processInputMelds("{R11,R12,R13}"))));  // Meld was played successfully (Initial 30 points)
        assertTrue(p2.getHand().size() == 11);   // player 2 hand is updated
        assertTrue(gs.melds.size() == 1);        // melds are updated
        assertTrue(gs.tiles.size() == 61);       // unused tiles updated

        game.resetPreviouslyMovedTile(gs.players, gs.tiles, gs.melds);   // clear '*' marker from tiles previously used.

        /* Player 3 plays a new meld {R13 G13 B13}{B2 R2 O2} */
        assertTrue(p3.getHand().size() == 14);   // player 3s hand has 14 tiles
        assertTrue(game.playMelds(p3, gs.melds, game.convertMeldInputToTiles(p3.processInputMelds("{R13,G13,B13}{B2,R2,O2}"))));  // Meld was played successfully (Initial 30 points)
        assertTrue(p3.getHand().size() == 8);    // player 3 hand is updated
        assertTrue(gs.melds.size() == 3);        // melds are updated
        assertTrue(gs.tiles.size() == 61);       // unused tiles updated

        game.resetPreviouslyMovedTile(gs.players, gs.tiles, gs.melds);   // clear '*' from tiles previously used.

        /* Player 1 plays a new meld {R12,G12,O12} */
        assertTrue(p1.getHand().size() == 15);   // player 1s hand has 15 tiles - {R12,G12,O12} and 12 random tiles (1 drawn from previous turn)
        assertTrue(game.playMelds(p1, gs.melds, game.convertMeldInputToTiles(p1.processInputMelds("{R12,G12,O12}"))));  // Meld was played successfully
        assertTrue(p1.getHand().size() == 12);   // player 1 hand is updated
        assertTrue(gs.melds.size() == 4);        // melds are updated
        assertTrue(gs.tiles.size() == 61);       // unused tiles updated

        //UI updates - state after players took turns (GameServer view):
        game.printPlayerHands(gs.players);
        game.printMelds(gs.melds);
        game.printRemainingTiles(gs.tiles);
    }

    /*
     *  Test Case: test player winning a game
     */
    public void testWinningPlayer() {
        GameServer gs = new GameServer(true);
        Player p1 = new Player("P1");
        Player p2 = new Player("P2");
        Player p3 = new Player("P3");
        gs.players[0] = p1;
        gs.players[1] = p2;
        gs.players[2] = p3;
        gs.tiles = game.generateTiles(); // generate all tiles

        // create player 1s hand - {B2,B2,O2,R3,G3,G3,R5,G6,O7,R9,R10,B11,G12,G13}
        game.drawNewTile(p1, 3, "B", gs.tiles);
        game.drawNewTile(p1, 2, "B", gs.tiles);
        game.drawNewTile(p1, 2, "O", gs.tiles);
        game.drawNewTile(p1, 3, "R", gs.tiles);
        game.drawNewTile(p1, 3, "G", gs.tiles);
        game.drawNewTile(p1, 3, "G", gs.tiles);
        game.drawNewTile(p1, 5, "R", gs.tiles);
        game.drawNewTile(p1, 6, "G", gs.tiles);
        game.drawNewTile(p1, 7, "O", gs.tiles);
        game.drawNewTile(p1, 9, "R", gs.tiles);
        game.drawNewTile(p1, 10, "R", gs.tiles);
        game.drawNewTile(p1, 11, "B", gs.tiles);
        game.drawNewTile(p1, 12, "G", gs.tiles);
        game.drawNewTile(p1, 13, "G", gs.tiles);

        // create player 2s hand - {2H 2S 2C 2D} {3C 4C 6C 7C} {4D 5D 6D 7D 8D} //{R2,G2,B2,O2}{B3,B4,B5,B6,B7}{O4,O5,O6,O7,O8}
        game.drawNewTile(p2, 2, "R", gs.tiles);
        game.drawNewTile(p2, 2, "G", gs.tiles);
        game.drawNewTile(p2, 2, "B", gs.tiles);
        game.drawNewTile(p2, 2, "O", gs.tiles);
        game.drawNewTile(p2, 3, "B", gs.tiles);
        game.drawNewTile(p2, 4, "B", gs.tiles);
        game.drawNewTile(p2, 6, "B", gs.tiles);
        game.drawNewTile(p2, 7, "B", gs.tiles);
        game.drawNewTile(p2, 4, "O", gs.tiles);
        game.drawNewTile(p2, 5, "O", gs.tiles);
        game.drawNewTile(p2, 6, "O", gs.tiles);
        game.drawNewTile(p2, 7, "O", gs.tiles);
        game.drawNewTile(p2, 8, "O", gs.tiles);

        // create player 3s hand - 4H 6D 6D 7S 7H 8C {10H JH QH KH} {10S JS QS KS}
        game.drawNewTile(p3, 4, "R", gs.tiles);
        game.drawNewTile(p3, 6, "O", gs.tiles);
        game.drawNewTile(p3, 6, "O", gs.tiles);
        game.drawNewTile(p3, 7, "G", gs.tiles);
        game.drawNewTile(p3, 7, "R", gs.tiles);
        game.drawNewTile(p3, 8, "B", gs.tiles);
        game.drawNewTile(p3, 10, "R", gs.tiles);
        game.drawNewTile(p3, 11, "R", gs.tiles);
        game.drawNewTile(p3, 12, "R", gs.tiles);
        game.drawNewTile(p3, 13, "R", gs.tiles);
        game.drawNewTile(p3, 10, "G", gs.tiles);
        game.drawNewTile(p3, 11, "G", gs.tiles);
        game.drawNewTile(p3, 12, "G", gs.tiles);
        game.drawNewTile(p3, 13, "G", gs.tiles);

        //P1 draws 2H
        game.drawNewTile(p1, 2, "R", gs.tiles);
        //P2 chooses to draw and draws 5C
        game.drawNewTile(p2, 5, "B", gs.tiles);
        //P3 plays  {10H JH QH KH} {10S JS QS KS}
        game.playMelds(p3, gs.melds, game.convertMeldInputToTiles(p3.processInputMelds("{R10,R11,R12,R13}{G10,G11,G12,G13}")));
        //P1 plays {2C 2D 2H}
        game.playMelds(p1, gs.melds, game.convertMeldInputToTiles(p1.processInputMelds("{B3,O2,R2}")));
        //P2 plays and wins: {2H 2S 2C 2D} {3C 4C 5C 6C 7C} {4D 5D 6D 7D 8D}
        game.playMelds(p2, gs.melds, game.convertMeldInputToTiles(p2.processInputMelds("{R2,G2,B2,O2}{B3,B4,B5,B6,B7}{O4,O5,O6,O7,O8}")));

        //check for winner
        assertTrue(game.checkForWinner(gs.players).getName().equals("P2"));
        assertTrue(gs.players[0].getScore() == -85); // P1 score: -85
        assertTrue(gs.players[1].getScore() == 0);
        assertTrue(gs.players[2].getScore() == -32);
    }

    /*
     *  Test Case 1: test player reusing tiles on table
     */
    public void testSimpleTableReuseCase1() {
        //setup
        System.out.println();
        System.out.println("Test: Simple Reuse From Table - Case 1");

        GameServer gs = new GameServer(true);
        Player p1 = new Player("P1");
        Player p2 = new Player("P2");
        Player p3 = new Player("P3");
        gs.players[0] = p1;
        gs.players[1] = p2;
        gs.players[2] = p3;
        gs.tiles = game.generateTiles(); // generate all tiles

        /* Create Player 1 hand  */
        game.drawNewTile(p1, 11, "R", gs.tiles);
        game.drawNewTile(p1, 11, "O", gs.tiles);
        game.drawNewTile(p1, 11, "G", gs.tiles);
        game.drawNewTile(p1, 11, "B", gs.tiles);

        /* Create Player 2 hand  */
        game.drawNewTile(p2, 12, "R", gs.tiles);
        game.drawNewTile(p2, 12, "G", gs.tiles);
        game.drawNewTile(p2, 12, "B", gs.tiles);
        game.drawNewTile(p2, 11, "G", gs.tiles);
        game.drawNewTile(p2, 11, "B", gs.tiles);

        /* Create Player 3 hand  */
        game.drawNewTile(p3, 7, "O", gs.tiles);
        game.drawNewTile(p3, 8, "O", gs.tiles);
        game.drawNewTile(p3, 9, "O", gs.tiles);
        game.drawNewTile(p3, 10, "O", gs.tiles);
        game.drawNewTile(p3, 11, "O", gs.tiles);
        game.drawNewTile(p3, 12, "O", gs.tiles);
        game.drawNewTile(p3, 13, "O", gs.tiles);

        game.drawNewTiles(p1, 10, gs.tiles);           // create player 1s hand - {R11,O11,G11,B11} and 10 random tiles
        game.drawNewTiles(p2, 9, gs.tiles);            // create player 2s hand - {R12,G12,B12,G11,B11} and 9 random tiles
        game.drawNewTiles(p3, 7, gs.tiles);            // create player 3s hand - {O7,O8,O9,O10,O11,O12,O13} and 7 random tiles

        /* Player 1 plays a new meld {R11,O11,G11,B11} */
        assertTrue(p1.getHand().size() == 14);   // player 2s hand has 14 tiles
        assertTrue(game.playMelds(p1, gs.melds, game.convertMeldInputToTiles(p1.processInputMelds("{R11,O11,G11,B11}"))));  // Meld was played successfully (Initial 30 points)
        assertTrue(p1.getHand().size() == 10);   // player 1 hand is updated
        assertTrue(gs.melds.size() == 1);        // melds are updated (melds still empty)
        assertTrue(gs.tiles.size() == 62);       // unused tiles updated

        /* Player 2 plays a new meld {R12,G12,B12} */
        assertTrue(p2.getHand().size() == 14);   // player 2s hand has 14 tiles
        assertTrue(game.playMelds(p2, gs.melds, game.convertMeldInputToTiles(p2.processInputMelds("{R12,G12,B12}"))));  // Meld was played successfully (Initial 30 points)
        assertTrue(p2.getHand().size() == 11);   // player 2 hand is updated
        assertTrue(gs.melds.size() == 2);        // melds are updated (1 new meld)
        assertTrue(gs.tiles.size() == 62);       // unused tiles updated

        /* Player 3 plays a new meld {O7,O8,O9,O10,O11,O12,O13} */
        assertTrue(p3.getHand().size() == 14);   // player 3s hand has 14 tiles
        assertTrue(game.playMelds(p3, gs.melds, game.convertMeldInputToTiles(p3.processInputMelds("{O7,O8,O9,O10,O11,O12,O13}"))));  // Meld was played successfully (Initial 30 points)
        assertTrue(p3.getHand().size() == 7);    // player 3 hand is updated
        assertTrue(gs.melds.size() == 3);        // melds are updated (3 melds now on table)
        assertTrue(gs.tiles.size() == 62);       // unused tiles updated

        game.resetPreviouslyMovedTile(gs.players, gs.tiles, gs.melds);   // clear '*' from tiles previously used.

        // Case 1
        // Player 1 draws new tile for their 2nd turn
        game.drawNewTile(p1, gs.tiles);                   // draw a new tile from the table (random)
        assertTrue(p1.getHand().size() == 11);   // player 1 hand is updated
        assertTrue(gs.tiles.size() == 61);       // unused tiles updated

        System.out.println("Before Reuse:");
        game.printMelds(gs.melds);
        game.resetPreviouslyMovedTile(gs.players, gs.tiles, gs.melds);   // clear '*' from tiles previously used.

        //Player 2 plays: {G11,B11,R11} for their 2nd turn (R11 is reused from existing meld on table)
        assertTrue(game.playMelds(p2, gs.melds, game.convertMeldInputToTiles(p2.processInputMelds("{G11,B11,*R11}"))));  // Meld was played successfully
        assertTrue(p2.getHand().size() == 9);   // player 2 hand is updated
        assertTrue(gs.melds.size() == 4);        // melds are updated

        System.out.println("After Reuse:");
        game.printMelds(gs.melds);
    }

    /*
     *  Test Case 2: test player reusing tiles on table
     */
    public void testSimpleTableReuseCase2() {
        //setup
        System.out.println();
        System.out.println("Test: Simple Reuse From Table - Case 2");

        GameServer gs = new GameServer(true);
        Player p1 = new Player("P1");
        Player p2 = new Player("P2");
        Player p3 = new Player("P3");
        gs.players[0] = p1;
        gs.players[1] = p2;
        gs.players[2] = p3;
        gs.tiles = game.generateTiles(); // generate all tiles

        /* Create Player 1 hand  */
        game.drawNewTile(p1, 11, "R", gs.tiles);
        game.drawNewTile(p1, 11, "O", gs.tiles);
        game.drawNewTile(p1, 11, "G", gs.tiles);
        game.drawNewTile(p1, 11, "B", gs.tiles);

        /* Create Player 2 hand  */
        game.drawNewTile(p2, 12, "R", gs.tiles);
        game.drawNewTile(p2, 12, "G", gs.tiles);
        game.drawNewTile(p2, 12, "B", gs.tiles);
        game.drawNewTile(p2, 12, "R", gs.tiles);
        game.drawNewTile(p2, 13, "R", gs.tiles);

        /* Create Player 3 hand  */
        game.drawNewTile(p3, 7, "O", gs.tiles);
        game.drawNewTile(p3, 8, "O", gs.tiles);
        game.drawNewTile(p3, 9, "O", gs.tiles);
        game.drawNewTile(p3, 10, "O", gs.tiles);
        game.drawNewTile(p3, 11, "O", gs.tiles);
        game.drawNewTile(p3, 12, "O", gs.tiles);
        game.drawNewTile(p3, 13, "O", gs.tiles);

        game.drawNewTiles(p1, 10, gs.tiles);           // create player 1s hand - {R11,O11,G11,B11} and 1 random tiles
        game.drawNewTiles(p2, 9, gs.tiles);            // create player 2s hand - {R12,G12,B12,R12,R13} and 9 random tiles
        game.drawNewTiles(p3, 7, gs.tiles);            // create player 3s hand - {O7,O8,O9,O10,O11,O12,O13} and 7 random tiles

        /* Player 1 plays a new meld {R11,O11,G11,B11} */
        assertTrue(p1.getHand().size() == 14);   // player 2s hand has 14 tiles
        assertTrue(game.playMelds(p1, gs.melds, game.convertMeldInputToTiles(p1.processInputMelds("{R11,O11,G11,B11}"))));  // Meld was played successfully (Initial 30 points)
        assertTrue(p1.getHand().size() == 10);   // player 1 hand is updated
        assertTrue(gs.melds.size() == 1);        // melds are updated (melds still empty)
        assertTrue(gs.tiles.size() == 62);       // unused tiles updated

        /* Player 2 plays a new meld {R12,G12,B12} */
        assertTrue(p2.getHand().size() == 14);   // player 2s hand has 14 tiles
        assertTrue(game.playMelds(p2, gs.melds, game.convertMeldInputToTiles(p2.processInputMelds("{R12,G12,B12}"))));  // Meld was played successfully (Initial 30 points)
        assertTrue(p2.getHand().size() == 11);   // player 2 hand is updated
        assertTrue(gs.melds.size() == 2);        // melds are updated (1 new meld)
        assertTrue(gs.tiles.size() == 62);       // unused tiles updated

        /* Player 3 plays a new meld {O7,O8,O9,O10,O11,O12,O13} */
        assertTrue(p3.getHand().size() == 14);   // player 3s hand has 14 tiles
        assertTrue(game.playMelds(p3, gs.melds, game.convertMeldInputToTiles(p3.processInputMelds("{O7,O8,O9,O10,O11,O12,O13}"))));  // Meld was played successfully (Initial 30 points)
        assertTrue(p3.getHand().size() == 7);    // player 3 hand is updated
        assertTrue(gs.melds.size() == 3);        // melds are updated (3 melds now on table)
        assertTrue(gs.tiles.size() == 62);       // unused tiles updated

        game.resetPreviouslyMovedTile(gs.players, gs.tiles, gs.melds);   // clear '*' from tiles previously used.

        // Case 2
        // Player 1 draws new tile for their 2nd turn
        game.drawNewTile(p1, gs.tiles);                   // draw a new tile from the table (random)
        assertTrue(p1.getHand().size() == 11);   // player 1 hand is updated
        assertTrue(gs.tiles.size() == 61);       // unused tiles updated

        System.out.println("Before Reuse:");
        game.printMelds(gs.melds);
        game.resetPreviouslyMovedTile(gs.players, gs.tiles, gs.melds);   // clear '*' from tiles previously used.

        //Player 2 plays: {R11,R12,R13} for their 2nd turn (R11 is reused from existing meld on table)
        assertTrue(game.playMelds(p2, gs.melds, game.convertMeldInputToTiles(p2.processInputMelds("{*R11,R12,R13}"))));  // Meld was played successfully
        assertTrue(p2.getHand().size() == 9);   // player 2 hand is updated
        assertTrue(gs.melds.size() == 4);        // melds are updated

        System.out.println("After Reuse:");
        game.printMelds(gs.melds);
    }

    /*
     *  Test Case 3: test player reusing tiles on table
     */
    public void testSimpleTableReuseCase3() {
        //setup
        System.out.println();
        System.out.println("Test: Simple Reuse From Table - Case 3");

        GameServer gs = new GameServer(true);
        Player p1 = new Player("P1");
        Player p2 = new Player("P2");
        Player p3 = new Player("P3");
        gs.players[0] = p1;
        gs.players[1] = p2;
        gs.players[2] = p3;
        gs.tiles = game.generateTiles(); // generate all tiles

        /* Create Player 1 hand  */
        game.drawNewTile(p1, 11, "R", gs.tiles);
        game.drawNewTile(p1, 11, "O", gs.tiles);
        game.drawNewTile(p1, 11, "G", gs.tiles);
        game.drawNewTile(p1, 11, "B", gs.tiles);
        game.drawNewTile(p1, 7, "R", gs.tiles);
        game.drawNewTile(p1, 7, "G", gs.tiles);

        /* Create Player 2 hand  */
        game.drawNewTile(p2, 12, "R", gs.tiles);
        game.drawNewTile(p2, 12, "G", gs.tiles);
        game.drawNewTile(p2, 12, "B", gs.tiles);

        /* Create Player 3 hand  */
        game.drawNewTile(p3, 7, "O", gs.tiles);
        game.drawNewTile(p3, 8, "O", gs.tiles);
        game.drawNewTile(p3, 9, "O", gs.tiles);
        game.drawNewTile(p3, 10, "O", gs.tiles);
        game.drawNewTile(p3, 11, "O", gs.tiles);
        game.drawNewTile(p3, 12, "O", gs.tiles);
        game.drawNewTile(p3, 13, "O", gs.tiles);

        game.drawNewTiles(p1, 8, gs.tiles);            // create player 1s hand - {R11,O11,G11,B11,R7,G7} and 8 random tiles
        game.drawNewTiles(p2, 11, gs.tiles);           // create player 2s hand - {R12,G12,B12} and 11 random tiles
        game.drawNewTiles(p3, 7, gs.tiles);            // create player 3s hand - {O7,O8,O9,O10,O11,O12,O13} and 7 random tiles

        /* Player 1 plays a new meld {R11,O11,G11,B11} */
        assertTrue(p1.getHand().size() == 14);   // player 2s hand has 14 tiles
        assertTrue(game.playMelds(p1, gs.melds, game.convertMeldInputToTiles(p1.processInputMelds("{R11,O11,G11,B11}"))));  // Meld was played successfully (Initial 30 points)
        assertTrue(p1.getHand().size() == 10);   // player 1 hand is updated
        assertTrue(gs.melds.size() == 1);        // melds are updated (melds still empty)
        assertTrue(gs.tiles.size() == 62);       // unused tiles updated

        /* Player 2 plays a new meld {R12,G12,B12} */
        assertTrue(p2.getHand().size() == 14);   // player 2s hand has 14 tiles
        assertTrue(game.playMelds(p2, gs.melds, game.convertMeldInputToTiles(p2.processInputMelds("{R12,G12,B12}"))));  // Meld was played successfully (Initial 30 points)
        assertTrue(p2.getHand().size() == 11);   // player 2 hand is updated
        assertTrue(gs.melds.size() == 2);        // melds are updated (1 new meld)
        assertTrue(gs.tiles.size() == 62);       // unused tiles updated

        game.resetPreviouslyMovedTile(gs.players, gs.tiles, gs.melds);   // clear '*' from tiles previously used.

        /* Player 3 plays a new meld {O7,O8,O9,O10,O11,O12,O13} */
        assertTrue(p3.getHand().size() == 14);   // player 3s hand has 14 tiles
        assertTrue(game.playMelds(p3, gs.melds, game.convertMeldInputToTiles(p3.processInputMelds("{O7,O8,O9,O10,O11,O12,O13}"))));  // Meld was played successfully (Initial 30 points)
        assertTrue(p3.getHand().size() == 7);    // player 3 hand is updated
        assertTrue(gs.melds.size() == 3);        // melds are updated (3 melds now on table)
        assertTrue(gs.tiles.size() == 62);       // unused tiles updated

        System.out.println("Before Reuse:");
        game.printMelds(gs.melds);
        game.resetPreviouslyMovedTile(gs.players, gs.tiles, gs.melds);   // clear '*' from tiles previously used.

        // Case 3
        // Player 1 plays: {O7,R7,G7} for their 2nd turn (O7 is reused from existing meld on table)
        assertTrue(game.playMelds(p1, gs.melds, game.convertMeldInputToTiles(p2.processInputMelds("{*O7,R7,G7}"))));  // Meld was played successfully
        assertTrue(p1.getHand().size() == 8);   // player 1 hand is updated
        assertTrue(gs.melds.size() == 4);        // melds are updated

        System.out.println("After Reuse:");
        game.printMelds(gs.melds);
    }

    /*
     *  Test Case 4: test player reusing tiles on table
     */
    public void testSimpleTableReuseCase4() {
        //setup
        System.out.println();
        System.out.println("Test: Simple Reuse From Table - Case 3");

        GameServer gs = new GameServer(true);
        Player p1 = new Player("P1");
        Player p2 = new Player("P2");
        Player p3 = new Player("P3");
        gs.players[0] = p1;
        gs.players[1] = p2;
        gs.players[2] = p3;
        gs.tiles = game.generateTiles(); // generate all tiles

        /* Create Player 1 hand  */
        game.drawNewTile(p1, 11, "R", gs.tiles);
        game.drawNewTile(p1, 11, "O", gs.tiles);
        game.drawNewTile(p1, 11, "G", gs.tiles);
        game.drawNewTile(p1, 11, "B", gs.tiles);
        game.drawNewTile(p1, 13, "R", gs.tiles);
        game.drawNewTile(p1, 13, "G", gs.tiles);

        /* Create Player 2 hand  */
        game.drawNewTile(p2, 12, "R", gs.tiles);
        game.drawNewTile(p2, 12, "G", gs.tiles);
        game.drawNewTile(p2, 12, "B", gs.tiles);

        /* Create Player 3 hand  */
        game.drawNewTile(p3, 7, "O", gs.tiles);
        game.drawNewTile(p3, 8, "O", gs.tiles);
        game.drawNewTile(p3, 9, "O", gs.tiles);
        game.drawNewTile(p3, 10, "O", gs.tiles);
        game.drawNewTile(p3, 11, "O", gs.tiles);
        game.drawNewTile(p3, 12, "O", gs.tiles);
        game.drawNewTile(p3, 13, "O", gs.tiles);

        game.drawNewTiles(p1, 8, gs.tiles);            // create player 1s hand - {R11,O11,G11,B11,R13,G13} and 8 random tiles
        game.drawNewTiles(p2, 11, gs.tiles);           // create player 2s hand - {R12,G12,B12} and 11 random tiles
        game.drawNewTiles(p3, 7, gs.tiles);            // create player 3s hand - {O7,O8,O9,O10,O11,O12,O13} and 7 random tiles

        /* Player 1 plays a new meld {R11,O11,G11,B11} */
        assertTrue(p1.getHand().size() == 14);   // player 2s hand has 14 tiles
        assertTrue(game.playMelds(p1, gs.melds, game.convertMeldInputToTiles(p1.processInputMelds("{R11,O11,G11,B11}"))));  // Meld was played successfully (Initial 30 points)
        assertTrue(p1.getHand().size() == 10);   // player 1 hand is updated
        assertTrue(gs.melds.size() == 1);        // melds are updated (melds still empty)
        assertTrue(gs.tiles.size() == 62);       // unused tiles updated

        /* Player 2 plays a new meld {R12,G12,B12} */
        assertTrue(p2.getHand().size() == 14);   // player 2s hand has 14 tiles
        assertTrue(game.playMelds(p2, gs.melds, game.convertMeldInputToTiles(p2.processInputMelds("{R12,G12,B12}"))));  // Meld was played successfully (Initial 30 points)
        assertTrue(p2.getHand().size() == 11);   // player 2 hand is updated
        assertTrue(gs.melds.size() == 2);        // melds are updated (1 new meld)
        assertTrue(gs.tiles.size() == 62);       // unused tiles updated

        game.resetPreviouslyMovedTile(gs.players, gs.tiles, gs.melds);   // clear '*' from tiles previously used.

        /* Player 3 plays a new meld {O7,O8,O9,O10,O11,O12,O13} */
        assertTrue(p3.getHand().size() == 14);   // player 3s hand has 14 tiles
        assertTrue(game.playMelds(p3, gs.melds, game.convertMeldInputToTiles(p3.processInputMelds("{O7,O8,O9,O10,O11,O12,O13}"))));  // Meld was played successfully (Initial 30 points)
        assertTrue(p3.getHand().size() == 7);    // player 3 hand is updated
        assertTrue(gs.melds.size() == 3);        // melds are updated (3 melds now on table)
        assertTrue(gs.tiles.size() == 62);       // unused tiles updated

        System.out.println("Before Reuse:");
        game.printMelds(gs.melds);
        game.resetPreviouslyMovedTile(gs.players, gs.tiles, gs.melds);   // clear '*' from tiles previously used.

        // Case 4
        // Player 1 plays: {R13,G13,O13} for their 2nd turn (O13 is reused from existing meld on table)
        assertTrue(game.playMelds(p1, gs.melds, game.convertMeldInputToTiles(p2.processInputMelds("{R13,G13,*O13}"))));  // Meld was played successfully
        assertTrue(p1.getHand().size() == 8);   // player 1 hand is updated
        assertTrue(gs.melds.size() == 4);        // melds are updated

        System.out.println("After Reuse:");
        game.printMelds(gs.melds);
    }

    /*
     *  Test Case 5: test player reusing tiles on table
     */
    public void testSimpleTableReuseCase5() {
        //setup
        System.out.println();
        System.out.println("Test: Simple Reuse From Table - Case 3");

        GameServer gs = new GameServer(true);
        Player p1 = new Player("P1");
        Player p2 = new Player("P2");
        Player p3 = new Player("P3");
        gs.players[0] = p1;
        gs.players[1] = p2;
        gs.players[2] = p3;
        gs.tiles = game.generateTiles(); // generate all tiles

        /* Create Player 1 hand  */
        game.drawNewTile(p1, 11, "R", gs.tiles);
        game.drawNewTile(p1, 11, "O", gs.tiles);
        game.drawNewTile(p1, 11, "G", gs.tiles);
        game.drawNewTile(p1, 11, "B", gs.tiles);
        game.drawNewTile(p1, 8, "O", gs.tiles);
        game.drawNewTile(p1, 9, "O", gs.tiles);

        /* Create Player 2 hand  */
        game.drawNewTile(p2, 12, "R", gs.tiles);
        game.drawNewTile(p2, 12, "G", gs.tiles);
        game.drawNewTile(p2, 12, "B", gs.tiles);

        /* Create Player 3 hand  */
        game.drawNewTile(p3, 7, "O", gs.tiles);
        game.drawNewTile(p3, 8, "O", gs.tiles);
        game.drawNewTile(p3, 9, "O", gs.tiles);
        game.drawNewTile(p3, 10, "O", gs.tiles);
        game.drawNewTile(p3, 11, "O", gs.tiles);
        game.drawNewTile(p3, 12, "O", gs.tiles);
        game.drawNewTile(p3, 13, "O", gs.tiles);

        game.drawNewTiles(p1, 8, gs.tiles);            // create player 1s hand - {R11,O11,G11,B11,O8,O9} and 8 random tiles
        game.drawNewTiles(p2, 11, gs.tiles);           // create player 2s hand - {R12,G12,B12} and 11 random tiles
        game.drawNewTiles(p3, 7, gs.tiles);            // create player 3s hand - {O7,O8,O9,O10,O11,O12,O13} and 7 random tiles

        /* Player 1 plays a new meld {R11,O11,G11,B11} */
        assertTrue(p1.getHand().size() == 14);   // player 2s hand has 14 tiles
        assertTrue(game.playMelds(p1, gs.melds, game.convertMeldInputToTiles(p1.processInputMelds("{R11,O11,G11,B11}"))));  // Meld was played successfully (Initial 30 points)
        assertTrue(p1.getHand().size() == 10);   // player 1 hand is updated
        assertTrue(gs.melds.size() == 1);        // melds are updated (melds still empty)
        assertTrue(gs.tiles.size() == 62);       // unused tiles updated

        /* Player 2 plays a new meld {R12,G12,B12} */
        assertTrue(p2.getHand().size() == 14);   // player 2s hand has 14 tiles
        assertTrue(game.playMelds(p2, gs.melds, game.convertMeldInputToTiles(p2.processInputMelds("{R12,G12,B12}"))));  // Meld was played successfully (Initial 30 points)
        assertTrue(p2.getHand().size() == 11);   // player 2 hand is updated
        assertTrue(gs.melds.size() == 2);        // melds are updated (1 new meld)
        assertTrue(gs.tiles.size() == 62);       // unused tiles updated

        game.resetPreviouslyMovedTile(gs.players, gs.tiles, gs.melds);   // clear '*' from tiles previously used.

        /* Player 3 plays a new meld {O7,O8,O9,O10,O11,O12,O13} */
        assertTrue(p3.getHand().size() == 14);   // player 3s hand has 14 tiles
        assertTrue(game.playMelds(p3, gs.melds, game.convertMeldInputToTiles(p3.processInputMelds("{O7,O8,O9,O10,O11,O12,O13}"))));  // Meld was played successfully (Initial 30 points)
        assertTrue(p3.getHand().size() == 7);    // player 3 hand is updated
        assertTrue(gs.melds.size() == 3);        // melds are updated (3 melds now on table)
        assertTrue(gs.tiles.size() == 62);       // unused tiles updated

        System.out.println("Before Reuse:");
        game.printMelds(gs.melds);
        game.resetPreviouslyMovedTile(gs.players, gs.tiles, gs.melds);   // clear '*' from tiles previously used.

        // Case 5
        // Player 1 plays: {O8,O9,O10} for their 2nd turn (O10 is reused from existing meld on table)
        assertTrue(game.playMelds(p1, gs.melds, game.convertMeldInputToTiles(p2.processInputMelds("{O8,O9,*O10}"))));  // Meld was played successfully
        assertTrue(p1.getHand().size() == 8);   // player 1 hand is updated
        assertTrue(gs.melds.size() == 4);        // melds are updated

        System.out.println("After Reuse:");
        game.printMelds(gs.melds);
    }
}
