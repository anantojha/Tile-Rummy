package org.a1;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.TestCase;

import java.util.ArrayList;

public class NetworkingStepDefs extends TestCase {
    private GameServer server;  // Game Server
    private ArrayList<Player> players = new ArrayList<>();  // List of Players
    private ArrayList<Thread> threads = new ArrayList<>();  // List of Game Server Threads

    /*
     *  Starting the Game Server
     * - Connect to a PORT
     * - Accept incoming connections from players
     * - Start the Game Loop
     */
    @Given("Game Server is running on port {int}")
    public void start_server(Integer port) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                server = new GameServer(true, port);
                try {
                    server.acceptConnections();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                server.startGame();
                server.gameLoop();
            }
        }).start();
    }

    /*
     *  Starting the Player Client
     * - Initialize new Player
     * - Connect Player to server at PORT with a NAME
     */
    @When("I connect to the server on port {int} with name {string}")
    public void connect_a_player_to_server_at_port(int port, String name) {
        threads.add(new Thread(new Runnable() {
            @Override
            public void run() {
                players.add(new Player(name));
                players.get(players.size()-1).initializePlayers();
                players.get(players.size()-1).connectToClient(port);
            }
        }));
    }

    /*
     *  Player Client is connected to Game Server
     * - Start Game Server Thread for Player
     */
    @Then("Player {int} is connected to server")
    public void player_is_connected_to_server(int player) throws InterruptedException {
        threads.get(threads.size()-1).start();
        Thread.sleep(200);
    }

}