package ServerSide;

import ClientSide.Client;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * @author Baha2r
 * Date: 12/05/2019 02:33 AM
 **/

public class ChatServer implements OnConnected {
    static Vector<ClientHandler> activeClients;
    private ServerSocket serverSocket;
    private Socket server;
    private int activeClientsNumber = 1;
//    private String clientName;

    public ChatServer(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        System.out.println("Server is up!\nWaiting for client...\n");
        activeClients = new Vector<>();
        while (true) {
            onConnected();
            DataInputStream input = new DataInputStream(server.getInputStream());
            DataOutputStream output = new DataOutputStream(server.getOutputStream());
//            this.clientName = Client.getNickName();
            ClientHandler newClient = new ClientHandler(this.server, this.activeClientsNumber + "", input, output);
            activeClientsNumber++;
            activeClients.add(newClient);
            newClient.start();
//            if (!newClient.isAlive())
//                return;
        }
    }

    public int getActiveClientsNumber() {
        return this.activeClientsNumber;
    }

    public Socket getServer() {
        return this.server;
    }

    @Override
    public void onConnected() {
        try {
            this.server = serverSocket.accept();
            System.out.println("New client joined at : " + server.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}