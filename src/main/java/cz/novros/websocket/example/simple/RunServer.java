package cz.novros.websocket.example.simple;

import cz.novros.websocket.example.jetty.Configuration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;

import javax.websocket.server.ServerContainer;
import java.net.InetSocketAddress;


public class RunServer {

    public static void main(String[] args) throws Exception {
        // Create jetty server
        final Server server = new Server(new InetSocketAddress(Configuration.IP_ADDRESS, Configuration.PORT));

        // Prepare context for container
        final ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        // Create server socket
        final ServerContainer webSocketContainer = WebSocketServerContainerInitializer.configureContext(context);
        webSocketContainer.addEndpoint(SimpleServerSocket.class);

        // Run jetty server
        server.start();
        server.join();
    }
}
