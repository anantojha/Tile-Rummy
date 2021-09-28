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

    Client clientConnection;

    Player[] players = new Player[3];
    public ArrayList<Tile> hand = new ArrayList<>();

    public Player(String n) {
        name = n;
    }

    public Player getPlayer() {
        return this;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Tile> getHand() {
        return this.hand;
    }

    public void setHand(ArrayList<Tile> ss) {
        this.hand = ss;
    }

    public void connectToClient() {
        clientConnection = new Client();
    }

    public void initializePlayers() {
        for (int i = 0; i < 3; i++) {
            players[i] = new Player(" ");
        }
    }

    public void startGame() {
        while (true) {
            int round = clientConnection.receiveRoundNo();
            System.out.println("\n \n ********Round Number " + round + "********");
            players = clientConnection.receivePlayers();

            String[] userInputMelds = playRound();

            if(userInputMelds == null){
                clientConnection.sendAction(1);
            }

            System.out.println("Updated hand & Table: ");
            players = clientConnection.receivePlayers();
        }
    }

    public String[] playRound() {
        Scanner myObj = new Scanner(System.in);

        System.out.println("Select an action: ");
        System.out.println(" (1) Pick a new Tile. ");

        int act = myObj.nextInt();

        if (act == 1) {
            return null;
        }

        return null;
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


