package cz.novros.websocket.example.reactive;

import cz.novros.websocket.example.entity.data.Entity;
import cz.novros.websocket.example.jetty.Configuration;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;

import javax.websocket.server.ServerContainer;
import javax.websocket.server.ServerEndpointConfig;
import java.net.InetSocketAddress;


public class RunServer {

	public static void main(String[] args) throws Exception {
		// Create jetty server
		final Server server = new Server(new InetSocketAddress(Configuration.IP_ADDRESS, Configuration.PORT));

		// Prepare context for container
		final ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		server.setHandler(context);

		// Create server web socket endpoint
		final EntityReactiveServerWebSocket serverSocket = new EntityReactiveServerWebSocket();
		final ServerContainer webSocketContainer = WebSocketServerContainerInitializer.configureContext(context);
		webSocketContainer.addEndpoint(ServerEndpointConfig.Builder.create(EntityReactiveServerWebSocket.class, "/reactive").configurator(new ServerEndpointConfig.Configurator() {
			@Override
			public <T> T getEndpointInstance(Class<T> endpointClass) throws InstantiationException {
				if (endpointClass.isAssignableFrom(EntityReactiveServerWebSocket.class)) {
					return (T) serverSocket;
				} else {
					return super.getEndpointInstance(endpointClass);
				}
			}
		}).build());

		// Create observable for sending data to client sockets.
		final Subject<Entity> data = PublishSubject.create();

		// Subscribe to send data to client sockets
		data.subscribe(serverSocket);

		// Subscribe to print data from client sockets.
		serverSocket.map(Entity::getMsg).subscribe(new PrintConsumer());

		// Run jetty server
		server.start();

		Thread.sleep(5000);
		// Send some data
		data.onNext(new Entity("Server", 2));
		data.onNext(new Entity("Server", 1));

		server.join();
	}
}
