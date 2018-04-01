package cz.novros.websocket.example.simple;

import cz.novros.websocket.example.jetty.Configuration;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;

/**
 * The simplest implementation of {@link ServerEndpoint} for server socket implementation. Not all method are needed to
 * be implemented. It can only implemented method with {@link OnMessage}. Endpoint under which it will be visible is
 * defined by {@link ServerEndpoint#value()}.
 *
 * @see ServerEndpoint
 */
@ServerEndpoint(value = Configuration.SIMPLE_ENDPOINT)
public class SimpleServerSocket {

	/**
	 * Method to react on client socket connection.
	 *
	 * @param session Session of client socket, which currently was connected.
	 *
	 * @see OnOpen
	 */
	@OnOpen
	public void onOpen(final Session session) {
		printMessage(session, "On open...");
	}

	/**
	 * Will receive client message
	 *
	 * @param session Session of client socket, which sent the message.
	 *
	 * @throws IOException Is thrown when was problem with sending message to client socket.
	 * @see OnMessage
	 */
	@OnMessage
	public void onMessage(final Session session, final String message) throws IOException {
		printMessage(session, "On message: " + message);
		session.getBasicRemote().sendText("Thanks for message...");
	}

	/**
	 * Method to react any error during communication with client socket.
	 *
	 * @param session Session of client socket, were was problem.
	 * @param t       Error which occurs.
	 *
	 * @see OnError
	 */
	@OnError
	public void onError(final Session session, final Throwable t) {
		printMessage(session, "On error: " + t.getMessage());
	}

	/**
	 * Method to react, when client socket going to be disconnect.
	 *
	 * @param session Session of client socket, which will be disconnected.
	 *
	 * @see OnClose
	 */
	@OnClose
	public void onClose(final Session session) {
		printMessage(session, "On close...");
	}

	/**
	 * Print simple message with date and session id.
	 */
	private static void printMessage(final Session session, final String message) {
		System.out.println("[" + new Date() + "][" + session.getId() + "][" + SimpleServerSocket.class.getCanonicalName() + "] " + message);
	}
}
