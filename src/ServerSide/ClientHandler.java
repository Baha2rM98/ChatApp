package ServerSide;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * @author Baha2r
 * Date: 12/05/2019 08:03 PM
 **/

class ClientHandler extends Thread implements OnDisconnected {
    private String clientName;
    private final DataInputStream input;
    private final DataOutputStream output;
    private Socket server;
    private boolean isLoggedIn;

    ClientHandler(Socket server, String clientName, DataInputStream input, DataOutputStream output) {
        this.server = server;
        this.clientName = clientName;
        this.input = input;
        this.output = output;
        this.isLoggedIn = true;
    }

    @Override
    public synchronized void run() {
        super.run();
        String received = "";
        while (true) {
            try {
                received = this.input.readUTF();
                System.out.println(received);
                if (received.toLowerCase().equals("logout")) {
                    onDisconnected();
                    return;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            StringTokenizer tokenizer = new StringTokenizer(received, "@");
            String message = tokenizer.nextToken();
            String contact = tokenizer.nextToken();
            for (ClientHandler client : ChatServer.activeClients) {
                if (client.clientName.equals(contact) && client.isLoggedIn) {
                    try {
                        client.output.writeUTF(this.clientName + " : " + message);
                        break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void onDisconnected() {
        try {
            this.isLoggedIn = false;
            this.server.close();
            this.input.close();
            this.output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}