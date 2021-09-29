package org.a1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Player implements Serializable {
    private static final long serialVersionUID = 1L;
    public String name;

    int playerId = 0;

    Game game = new Game();

    public ArrayList<Tile> hand = new ArrayList<>();
    private ArrayList<ArrayList<Tile>> melds = new ArrayList<>();
    private Boolean winner = false;

    Client clientConnection;

    Player[] players = new Player[3];
    private ArrayList<ArrayList<Tile>> meldsToPlay = new ArrayList<>();

    public boolean initialThirty = false;

    public Player(String n) {
        name = n;
    }

    public Player getPlayer() {
        return this;
    }

    public String getName() { return  name; }

    public void connectToClient() {
        clientConnection = new Client();
    }

    public void initializePlayers() {
        for (int i = 0; i < 3; i++) {
            players[i] = new Player(" ");
        }
    }

    public ArrayList<Tile> getHand() {
        return this.hand;
    }

    public void setHand(ArrayList<Tile> ss) {
        this.hand = ss;
    }

    public void setWinner(Boolean isWinner)
    {
        winner = isWinner;
    }

    public int getScore()
    {
        int counter = 0;
        for (Tile t: this.hand)
        {
            counter += t.getPoints();
        }
        return counter * -1;
    }

    public Tile getTileFromhand(int n, String c, boolean remove)
    {
        for (int i = 0; i < hand.size(); i++)
        {
            if(hand.get(i).getNumber() == n)
            {
                if(hand.get(i).getColour().equals(c))
                {
                    if(remove){
                        Tile temp = new Tile(n, c);
                        hand.remove(i);
                        return temp;
                    }
                    return new Tile(n, c);
                }
            }
        }
        return null;
    }

    public void startGame() {
        while (true) {
            int round = clientConnection.receiveRoundNo();
            players = clientConnection.receivePlayers();
            melds = clientConnection.receiveMelds();
            if(game.checkForWinner(players) != null)
            {
                System.out.println("Winner: Player " + game.checkForWinner(players).getName());
                System.out.println("Scores: " + players[0].getName() + ": " + players[0].getScore() +
                        ", " + players[1].getName() + ": " + players[1].getScore() +
                        ", " + players[2].getName() + ": " + players[2].getScore());
                break;
            }
            System.out.println("\n \n ********Round Number " + round + "********");
            game.printHand(players[playerId-1]);
            game.printMelds(melds);

            String[] userInputMelds = playRound();
            if(userInputMelds == null){
                clientConnection.sendAction(1);
            } else {
                ArrayList<ArrayList<Tile>> meldIn = game.convertMeldInputToTiles(userInputMelds);
                if (game.initialMeldsAtLeastThirty(this, meldIn)) {
                    clientConnection.sendAction(2);
                    clientConnection.sendMeld(meldIn);
                    this.initialThirty = true;
                } else {
                    System.out.println("Initial Melds did not equal at least 30");
                    System.out.println("New Tile is added to your hand");
                    clientConnection.sendAction(1);
                }
            }

            System.out.println("Updated hand & Table: ");
            players = clientConnection.receivePlayers();
            melds = clientConnection.receiveMelds();
            game.printHand(players[playerId-1]);
            game.printMelds(melds);
            if(game.checkForWinner(players) != null)
            {
                System.out.println("Winner: Player " + game.checkForWinner(players).getName());
                System.out.println("Scores: " + players[0].getName() + ": " + players[0].getScore() +
                        ", " + players[1].getName() + ": " + players[1].getScore() +
                        ", " + players[2].getName() + ": " + players[2].getScore());
                break;
            }
        }
    }

    public String[] playRound() {
        Scanner myObj = new Scanner(System.in);

        System.out.println("Select an action: ");
        System.out.println(" (1) Pick a new Tile. ");
        System.out.println(" (2) Play a Meld. ");

        int act = myObj.nextInt();

        if (act == 1) {
            return null;
        } else {
            System.out.println("Select a RUNs / SETs from your hand. (Format: {01,02,03}{R1,B2,G3,O4} )");
            String[] userInputMelds = processInputMelds(myObj.next());
            return userInputMelds;
        }
    }

    public String[] processInputMelds(String input)
    {
        String[] inMeld = input.replaceAll("\\s", "").split("}");
        String[] formattedMeld = new String[inMeld.length];
        String res;
        for(int i = 0; i < inMeld.length; i++)
        {
            res = inMeld[i].replaceAll("\\{", "").replaceAll("}","");
            formattedMeld[i] = res;
        }

        return formattedMeld;
    }

    private class Client {
        Socket socket;
        private ObjectInputStream dIn;
        private ObjectOutputStream dOut;

        public Client() {
            try {
                socket = new Socket("localhost", 9010);
                dOut = new ObjectOutputStream(socket.getOutputStream());
                dIn = new ObjectInputStream(socket.getInputStream());

                playerId = dIn.readInt();

                System.out.println("Connected as " + playerId);
                sendPlayer();

            } catch (IOException ex) {
                System.out.println("Client failed to open");
            }
        }

        public void sendPlayer() {
            try {
                dOut.writeObject(getPlayer());
                dOut.flush();
                dOut.reset();
            } catch (IOException ex) {
                System.out.println("Player not sent");
                ex.printStackTrace();
            }
        }

        public void sendMeld(ArrayList<ArrayList<Tile>> meld)
        {
            try {
                dOut.writeObject(meld);
                dOut.flush();
                //dOut.reset();
            } catch (IOException ex) {
                System.out.println("Melds not sent");
                ex.printStackTrace();
            }
        }

        public void sendAction(int action) {
            try {
                dOut.writeInt(action);
                dOut.flush();
            } catch (IOException ex) {
                System.out.println("Action not sent");
                ex.printStackTrace();
            }
        }

        public Player[] receivePlayers() {
            Player[] pl = new Player[3];
            try {
                for(int i=0; i<3; i++)
                {
                    pl[i] = (Player) dIn.readObject();
                }
            } catch (IOException e) {
                System.out.println("Score sheet not received");
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                System.out.println("class not found");
                e.printStackTrace();
            }
            return pl;
        }

        public int receiveRoundNo() {
            try {
                return dIn.readInt();

            } catch (IOException e) {
                System.out.println("Round numbert not received");
                e.printStackTrace();
            }
            return 0;
        }

        public ArrayList<ArrayList<Tile>> receiveMelds() {
            try {
                return (ArrayList<ArrayList<Tile>>) dIn.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Melds not received");
                e.printStackTrace();
            }
            return null;
        }
    }

    public static void main(String args[]) {
        Scanner myObj = new Scanner(System.in);
        System.out.print("What is your name ? ");
        String name = myObj.next();
        Player p = new Player(name);
        p.initializePlayers();
        p.connectToClient();
        p.startGame();
        myObj.close();
    }
}


