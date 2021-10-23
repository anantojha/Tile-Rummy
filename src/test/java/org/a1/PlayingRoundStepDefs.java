package org.a1;

import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.TestCase;

import java.util.ArrayList;

public class PlayingRoundStepDefs extends TestCase {
    private GameServer gs;
    private Game game;

    @Given("Game is started")
    public void start_game(){
        gs = new GameServer(true);
        game = new Game();
        gs.players[0] = new Player("P1");
        gs.players[1] = new Player("P2");
        gs.players[2] = new Player("P3");
        gs.players[0].setInitial30Meld(true);
        gs.players[1].setInitial30Meld(true);
        gs.players[2].setInitial30Meld(true);
        gs.tiles = game.generateTiles();
    }

    @And("Player {int} first turn")
    public void players_first_turn(int player){
        gs.players[player-1].setInitial30Meld(false);
    }

    @And("Player {int} hand is {string}")
    public void set_play_hands(int player, String hand){
        for(String s : hand.replaceAll("\\s", "").replaceAll("}","").replaceAll("\\{","").split(",")) {
            game.drawNewTile(gs.players[player-1], Integer.parseInt(s.substring(1)), s.substring(0,1), gs.tiles);
        }
        game.resetPreviouslyMovedTile(gs.players, gs.tiles, gs.melds);
    }

    @When("Player {int} draws new tile")
    public void player_draws_new_tile(int player){
        if(gs.players[player-1].getHand().size() == 0) {
            game.drawNewTiles(gs.players[player-1], 11, gs.tiles);
            game.resetPreviouslyMovedTile(gs.players, gs.tiles, gs.melds);
        }
        game.drawNewTile(gs.players[player-1], gs.tiles);
    }

    @When("Player {int} plays {string}")
    public void player_plays_melds(int player, String melds){
        game.resetPreviouslyMovedTile(gs.players, gs.tiles, gs.melds);
        game.playMelds(gs.players[player-1], gs.melds, game.convertMeldInputToTiles(gs.players[player-1].processInputMelds(melds)));
    }

    @Then("Player {int} hand is updated")
    public void player_hand_updated(int player) throws InterruptedException {
        Thread.sleep(100);
        game.printHand(gs.players[player-1]);
    }

    @And("Table is updated")
    public void table_updated() throws InterruptedException {
        Thread.sleep(100);
        game.printMelds(gs.melds);
        game.printRemainingTiles(gs.tiles);
    }



    @And("Joker is used as another tile")
    public void player_plays_joker(){
        for(ArrayList<Tile> m : gs.melds){
            for(Tile t : m){
                t.printDesiredTile();
            }
        }
    }
}