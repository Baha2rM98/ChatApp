package ServerSide;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * @author Baha2r
 * Date: 12/05/2019 02:33 AM
 **/

public class ChatServer implements OnConnected {
    private ServerSocket serverSocket;
    private Socket server;
    static Vector<ClientHandler> activeClients;
    private int activeClientsNumber = 0;

    public ChatServer(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        System.out.println("Running the server...\nWaiting for client...\n");
        activeClients = new Vector<>();
        while (true) {
            onConnected();
            DataInputStream input = new DataInputStream(server.getInputStream());
            DataOutputStream output = new DataOutputStream(server.getOutputStream());
            ClientHandler newClient = new ClientHandler(this.server, "client " + activeClientsNumber, input, output);
            activeClientsNumber++;
            activeClients.add(newClient);
            newClient.start();
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
            System.out.println("New client request received : " + server.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}