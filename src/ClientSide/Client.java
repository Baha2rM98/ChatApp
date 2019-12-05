package ClientSide;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * @author Baha2r
 * Date: 12/05/2019 02:49 AM
 **/

public class Client {
    private String IP;
    private int portNumber;
    private Scanner scn;
    private DataInputStream input;
    private DataOutputStream output;

    public Client(String IP, int port) throws IOException {
        this.scn = new Scanner(System.in);
        this.IP = IP;
        this.portNumber = port;
        Socket client = new Socket(this.IP, portNumber);
        this.input = new DataInputStream(client.getInputStream());
        this.output = new DataOutputStream(client.getOutputStream());
        Thread send = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    String message = scn.nextLine();
                    try {
                        output.writeUTF(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread read = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        String message = input.readUTF();
                        System.out.println(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        send.start();
        read.start();
    }

    public String getIP() {
        return this.IP;
    }

    public int getPortNumber() {
        return this.portNumber;
    }
}