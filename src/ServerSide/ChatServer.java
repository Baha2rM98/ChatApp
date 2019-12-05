package ServerSide;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * @author Baha2r
 * Date: 12/05/2019 02:33 AM
 **/

public class ChatServer {
    private Socket server;
    static Vector<ClientHandler> activeClients;
    static int activeClientsNumber = 0;

    public ChatServer(int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Running the server...\nWaiting for client...\n");
        activeClients = new Vector<>();
        while (true) {
            this.server = serverSocket.accept();
            System.out.println("New client request received : " + server.toString());
            DataInputStream input = new DataInputStream(server.getInputStream());
            DataOutputStream output = new DataOutputStream(server.getOutputStream());
            ClientHandler newClient = new ClientHandler();
            activeClientsNumber++;
            activeClients.add(newClient);
            newClient.start();
        }
    }

    public static int getActiveClientsNumber() {
        return activeClientsNumber;
    }

    public Socket getServer() {
        return server;
    }
}