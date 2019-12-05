import ServerSide.ChatServer;

import java.io.IOException;

/**
 * @author Baha2r
 * Date: 12/05/2019 09:18 PM
 *
 * Runs server
 **/

public class RunServer {
    public static void main(String[] args) throws IOException {
        ChatServer server = new ChatServer(3000);
    }
}
