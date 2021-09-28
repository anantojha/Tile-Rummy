package org.a1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */


public class GameServer implements Serializable
{
    private static final long serialVersionUID = 1L;
    private int turnsMade;

    Server[] playerServer = new Server[3];
    Player[] players = new Player[3];
    ArrayList<Tile> tiles = new ArrayList<>();

    ServerSocket ss;
    int numPlayers;

    Game game = new Game();

    public static void main( String[] args ) throws Exception {
        GameServer gs = new GameServer(false);

        gs.acceptConnections();
        gs.gameLoop();
    }

    public GameServer(boolean test) {
        if (!test){
            System.out.println("Starting game server");
        }
        numPlayers = 0;
        turnsMade = 0;
        // initialize the players list with new players
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player(" ");
        }

        if(!test) {
            try {
                ss = new ServerSocket(9010);
            } catch (IOException ex) {
                System.out.println("Server Failed to open");
            }
        }
    }

    /*
     * -----------Networking stuff ----------
     *
     */
    public void acceptConnections() throws ClassNotFoundException {
        try {
            System.out.println("Waiting for players...");
            while (numPlayers < 3) {
                Socket s = ss.accept();
                numPlayers++;

                Server server = new Server(s, numPlayers);

                // send the player number
                server.dOut.writeInt(server.playerId);
                server.dOut.flush();

                // get the player name
                Player in = (Player) server.dIn.readObject();
                System.out.println("Player " + server.playerId + " ~ " + in.name + " ~ has joined");
                // add the player to the player list
                players[server.playerId - 1] = in;
                playerServer[numPlayers - 1] = server;
            }
            System.out.println("Three players have joined the game");

            // start the server threads
            for (int i = 0; i < playerServer.length; i++) {
                Thread t = new Thread(playerServer[i]);
                t.start();
            }
            // start their threads
        } catch (IOException ex) {
            System.out.println("Could not connect 3 players");
        }
    }

    public void gameLoop() {
        try {
            tiles = game.createPlayerHands(players, game.generateTiles());

            while (true) {
                turnsMade++;
                System.out.println("*****************************************");
                System.out.println("Round number " + turnsMade);

                for (int i = 0; i < 3; i++)
                {
                    playerServer[i].sendTurnNo(turnsMade);
                    playerServer[i].sendPlayers(players);

                    // receive action 1 or 2
                    int action = playerServer[i].receiveAction();

                    if (action == 1) {
                        // draw new tile for player
                        game.drawNewTile(players[i], tiles);
                    }
                    playerServer[i].sendPlayers(players);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class Server implements Runnable {
        private Socket socket;
        private ObjectInputStream dIn;
        private ObjectOutputStream dOut;
        private int playerId;

        public Server(Socket s, int playerid) {
            socket = s;
            playerId = playerid;
            try {
                dOut = new ObjectOutputStream(socket.getOutputStream());
                dIn = new ObjectInputStream(socket.getInputStream());
            } catch (IOException ex) {
                System.out.println("Server Connection failed");
            }
        }

        /*
         * run function for threads --> main body of the thread will start here
         */
        public void run() {
            try {
                while (true) {
                }

            } catch (Exception ex) {
                {
                    System.out.println("Run failed");
                    ex.printStackTrace();
                }
            }
        }

        public void sendTurnNo(int r) {
            try {
                dOut.writeInt(r);
                dOut.flush();
            } catch (Exception e) {
                System.out.println("Turn Number not received");
                e.printStackTrace();
            }
        }

        public int receiveAction() {
            try {
                int act = dIn.readInt();
                return act;
            } catch (Exception e) {
                System.out.println("Action not received");
                e.printStackTrace();
            }
            return -1;
        }

        public void sendPlayers(Player[] pl) {
            try {
                for (Player p : pl) {
                    dOut.writeObject(p);
                    dOut.flush();
                    dOut.reset();
                }

            } catch (IOException ex) {
                System.out.println("Players not sent");
                ex.printStackTrace();
            }
        }
    }
}
