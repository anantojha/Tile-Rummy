package org.a1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.Scanner;

public class Player implements Serializable {
    private static final long serialVersionUID = 1L;
    public String name;

    int playerId = 0;

    Client clientConnection;

    Player[] players = new Player[3];

    public Player(String n) {
        name = n;
    }

    public Player getPlayer() {
        return this;
    }

    public String getName() {
        return name;
    }

    public void connectToClient() {
        clientConnection = new Client();
    }

    public void initializePlayers() {
        for (int i = 0; i < 3; i++) {
            players[i] = new Player(" ");
        }
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
    }

    public static void main(String args[]) {
        Scanner myObj = new Scanner(System.in);
        System.out.print("What is your name ? ");
        String name = myObj.next();
        Player p = new Player(name);
        p.initializePlayers();
        p.connectToClient();
        myObj.close();
    }
}


