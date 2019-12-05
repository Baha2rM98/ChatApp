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
    public void run() {
//        super.run();
        String received = "";
        while (true) {
            try {
                received = this.input.readUTF();
                System.out.println(received);
                if (received.toLowerCase().equals("logout")) {
                    this.isLoggedIn = false;
                    this.server.close();
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            StringTokenizer st = new StringTokenizer(received, "@");
            String message = st.nextToken();
            String contact = st.nextToken();
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
        onDisconnected();
    }

    @Override
    public void onDisconnected() {
        try {
            this.input.close();
            this.output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}