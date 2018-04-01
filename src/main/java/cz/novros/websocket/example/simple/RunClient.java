package cz.novros.websocket.example.simple;

import cz.novros.websocket.example.jetty.Configuration;
import org.eclipse.jetty.util.component.LifeCycle;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.net.URI;

public class RunClient {

	public static void main(String[] args) throws Exception {
		// Set up client socket to connect
		final String uri = "ws://" + Configuration.IP_ADDRESS + ":" + Configuration.PORT + Configuration.SIMPLE_ENDPOINT;
		final WebSocketContainer container = ContainerProvider.getWebSocketContainer();

		// Connect client socket to server socket
		final Session session = container.connectToServer(SimpleClientSocket.class, URI.create(uri));

		// Send data to server socket
		session.getBasicRemote().sendText("Sending text...");

		// Wait for user to end.
		System.in.read();

		// Clean up
		session.close();
		if (container instanceof LifeCycle) {
			((LifeCycle) container).stop();
		}
	}
}
