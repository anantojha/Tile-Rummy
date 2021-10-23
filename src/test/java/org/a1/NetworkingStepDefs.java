package org.a1;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.TestCase;

import java.util.ArrayList;

public class NetworkingStepDefs extends TestCase {
    private GameServer server;
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Thread> threads = new ArrayList<>();

    @Given("the server is running on port {int}")
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

    @Then("Player {int} is connected to server")
    public void player_is_connected_to_server(int player) throws InterruptedException {
        threads.get(threads.size()-1).start();
        Thread.sleep(200);
    }

}