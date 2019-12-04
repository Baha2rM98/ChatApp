package Client;

import java.io.*;
import java.net.*;

/**
 * @author Baha2r
 * Date: 12/05/2019 02:49 AM
 **/

public class Client {
    private Socket client;
    private InputStream input;
    private OutputStream output;
    private String name = "";

    public Client(Socket socket, DataInputStream input, DataOutputStream output) {
        this.client = socket;
        this.input = input;
        this.output = output;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public InputStream getInput() {
        return this.input;
    }

    public OutputStream getOutput() {
        return this.output;
    }

    public Socket getClient() {
        return this.client;
    }
}