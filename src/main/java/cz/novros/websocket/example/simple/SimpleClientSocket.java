package cz.novros.websocket.example.simple;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;

/**
 * This is the simplest {@link ClientEndpoint} for client socket implementation.
 *
 * @see ClientEndpoint
 */
@ClientEndpoint
public class SimpleClientSocket {

    /**
     * Will receive message, which defined by {@link OnMessage}, from {@link javax.websocket.server.ServerEndpoint} (server socket).
     *
     * @see OnMessage
     */
    @OnMessage
    public void onMessage(final String message) {
        System.out.println("On message: " + message);
    }

}
