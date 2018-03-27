package cz.novros.websocket.example.entity;

import cz.novros.websocket.example.entity.data.Entity;
import cz.novros.websocket.example.jetty.Configuration;
import cz.novros.websocket.example.simple.SimpleClientSocket;
import org.eclipse.jetty.util.component.LifeCycle;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.net.URI;

public class RunClient {

    public static void main(String[] args) throws Exception {
        // Set up client socket to connect
        final String uri = "ws://" + Configuration.IP_ADDRESS + ":" + Configuration.PORT + Configuration.ENTITY_ENDPOINT;
        final WebSocketContainer container = ContainerProvider.getWebSocketContainer();

        // Connect client socket to server socket
        final Session session = container.connectToServer(EntityClientSocket.class, URI.create(uri));

        // Send data to server socket
        session.getBasicRemote().sendObject(new Entity("test", 9));

        // Wait for user to end.
        System.in.read();

        // Clean up
        session.close();
        if (container instanceof LifeCycle) {
            ((LifeCycle) container).stop();
        }
    }
}
