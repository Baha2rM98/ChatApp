package Server;

import Client.Client;

/**
 * @author Baha2r
 * Date: 12/05/2019 03:08 AM
 **/

public interface OnConnected {
    void onConnected(Client client);
}