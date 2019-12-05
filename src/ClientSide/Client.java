package ClientSide;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * @author Baha2r
 * Date: 12/05/2019 02:49 AM
 **/

public class Client {
    private Scanner scn;
    private DataInputStream input;
    private DataOutputStream output;
//    private static String nickName;

    public Client(String IP, int port) throws IOException {
        Socket client = new Socket(IP, port);
        this.input = new DataInputStream(client.getInputStream());
        this.output = new DataOutputStream(client.getOutputStream());
        this.scn = new Scanner(System.in);
//        System.out.println("Enter your nickname: ");
//        nickName = scn.nextLine();
        startSend().start();
        startRead().start();
    }

    private Thread startSend() {
        return new Thread(() -> {
            while (true) {
                String message = this.scn.nextLine();
                try {
                    this.output.writeUTF(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private Thread startRead() {
        return new Thread(() -> {
            while (true) {
                try {
                    String message = this.input.readUTF();
                    System.out.println(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

//    public static String getNickName() {
//        return nickName;
//    }
}