package ServerSide;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * @author Baha2r
 * Date: 12/05/2019 02:33 AM
 **/

public class ChatServer extends Thread {
    private ServerSocket serverSocket;
    private InputStream input;
    private OutputStream output;
    private Socket server;
    private Scanner scn;

    public ChatServer(int port) throws IOException {
        this.scn = new Scanner(System.in);
        this.serverSocket = new ServerSocket(port);
        System.out.println("Running the server...");
        this.server = this.serverSocket.accept();
        System.out.println("Server is Up!\nConnected to: " + server.toString());
    }
}