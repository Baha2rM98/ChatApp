import ServerSide.ChatServer;

import java.io.IOException;

/**
 * @author Baha2r
 * Date: 12/05/2019 09:18 PM
 * <p>
 * Runs server
 **/

public class RunServer {
    public static void main(String[] args) throws IOException {
        new ChatServer(3000);
    }
}
