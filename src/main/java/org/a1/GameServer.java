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
    ArrayList<ArrayList<Tile>> melds = new ArrayList<>();

    ServerSocket ss;

    Game game = new Game();
    int numPlayers;

    public static void main( String[] args ) throws Exception {
        GameServer gs = new GameServer(false);

        gs.acceptConnections();
        gs.gameLoop();
        System.out.println("Game Over");
        System.exit(1);
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
            System.out.println("Initials Hands:");
            game.printPlayerHands(players);
            game.printRemainingTiles(tiles);
            int counter = 0;

            while (true) {
                if(game.checkForWinner(players) == null) {
                    turnsMade++;
                    System.out.println("*****************************************");
                    System.out.println("Round number " + turnsMade);
                }

                // Player 1s turn
                playerServer[0].sendTurnNo(turnsMade);
                playerServer[0].sendPlayers(players);
                playerServer[0].sendMelds(melds);
                if(game.checkForWinner(players) == null) {
                    playerServer[0].sendAction(0);

                    game.resetPreviouslyMovedTile(players, tiles, melds);

                    // receive action 1 or 2
                    int action = playerServer[0].receiveAction();
                    if (action == 1) {
                        // draw new tile for player
                        game.drawNewTile(players[0], tiles);
                    } else if (action == 2) {
                        // add meld (run or set) to table
                        //ArrayList<ArrayList<Tile>> receivedMelds = playerServer[i].receiveMeld();
                        ArrayList<ArrayList<Tile>> inMelds = playerServer[0].receiveMeld();//game.convertMeldInputToTiles(receivedMelds);
                        game.playMelds(players[0], melds, inMelds);
                    }
                    playerServer[0].sendPlayers(players);
                    playerServer[0].sendMelds(melds);
                    game.printHand(players[0]);
                    game.printMelds(melds);
                    game.printRemainingTiles(tiles);
                } else {
                    playerServer[0].sendAction(1);
                    counter += 1;
                    if(counter == 3){
                        break;
                    } else if (counter == 1) {
                        game.printWinner(players);
                    }
                }


                // Player 2s turn
                playerServer[1].sendTurnNo(turnsMade);
                playerServer[1].sendPlayers(players);
                playerServer[1].sendMelds(melds);
                if(game.checkForWinner(players) == null) {
                    playerServer[1].sendAction(0);

                    game.resetPreviouslyMovedTile(players, tiles, melds);

                    // receive action 1 or 2
                    int action2 = playerServer[1].receiveAction();
                    if (action2 == 1) {
                        // draw new tile for player
                        game.drawNewTile(players[1], tiles);
                    } else if (action2 == 2) {
                        // add meld (run or set) to table
                        //ArrayList<ArrayList<Tile>> receivedMelds = playerServer[i].receiveMeld();
                        ArrayList<ArrayList<Tile>> inMelds = playerServer[1].receiveMeld();//game.convertMeldInputToTiles(receivedMelds);
                        game.playMelds(players[1], melds, inMelds);
                    }
                    playerServer[1].sendPlayers(players);
                    playerServer[1].sendMelds(melds);
                    game.printHand(players[1]);
                    game.printMelds(melds);
                    game.printRemainingTiles(tiles);
                } else {
                    playerServer[1].sendAction(1);
                    counter += 1;
                    if(counter == 3){
                        break;
                    } else if (counter == 1) {
                        game.printWinner(players);
                    }
                }

                // Player 3s turn
                playerServer[2].sendTurnNo(turnsMade);
                playerServer[2].sendPlayers(players);
                playerServer[2].sendMelds(melds);
                if(game.checkForWinner(players) == null) {
                    playerServer[2].sendAction(0);

                    game.resetPreviouslyMovedTile(players, tiles, melds);

                    // receive action 1 or 2
                    int action3 = playerServer[2].receiveAction();
                    if (action3 == 1) {
                        // draw new tile for player
                        game.drawNewTile(players[2], tiles);
                    } else if (action3 == 2) {
                        // add meld (run or set) to table
                        //ArrayList<ArrayList<Tile>> receivedMelds = playerServer[i].receiveMeld();
                        ArrayList<ArrayList<Tile>> inMelds = playerServer[2].receiveMeld();//game.convertMeldInputToTiles(receivedMelds);
                        game.playMelds(players[2], melds, inMelds);
                    }
                    playerServer[2].sendPlayers(players);
                    playerServer[2].sendMelds(melds);
                    game.printHand(players[2]);
                    game.printMelds(melds);
                    game.printRemainingTiles(tiles);
                } else {
                    playerServer[2].sendAction(1);
                    counter += 1;
                    if(counter == 3){
                        break;
                    } else if (counter == 1) {
                        game.printWinner(players);
                    }
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

        public void sendMelds(ArrayList<ArrayList<Tile>> melds) {
            try {
                dOut.writeObject(melds);
                dOut.flush();
                dOut.reset();
            } catch (IOException ex) {
                System.out.println("Melds not sent");
                ex.printStackTrace();
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

        public void sendAction(int r) {
            try {
                dOut.writeInt(r);
                dOut.flush();
            } catch (Exception e) {
                System.out.println("Action not sent");
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

        public ArrayList<ArrayList<Tile>> receiveMeld() {
            try {
                return (ArrayList<ArrayList<Tile>>) dIn.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Melds not received");
                e.printStackTrace();
            }
            return null;
        }
    }
}
