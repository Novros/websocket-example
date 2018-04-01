package cz.novros.websocket.example.reactive;

import cz.novros.reactive.websocket.ReactiveServerWebSocket;
import cz.novros.websocket.example.entity.data.Entity;
import cz.novros.websocket.example.jetty.Configuration;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Date;

/**
 * Example of implementing {@link ReactiveServerWebSocket} for {@link Entity} class.
 *
 * @see ServerEndpoint
 */
@ServerEndpoint(value = Configuration.REACTIVE_ENDPOINT)
public class EntityReactiveServerWebSocket extends ReactiveServerWebSocket<Entity> {

	public EntityReactiveServerWebSocket() {
		super(Entity.class);
	}

	/**
	 * Method to react on client socket connection.
	 *
	 * @param session Session of client socket, which currently was connected.
	 *
	 * @see OnOpen
	 */
	@Override
	public void onOpen(final Session session) {
		super.onOpen(session);
		printMessage(session, "On open...");
	}

	/**
	 * Method to react {@link ClientEndpoint} sended entity.
	 *
	 * @param session Session of client socket, which sent the message.
	 * @param entity  Entity which was sent.
	 */
	@Override
	protected void onMessage(final Session session, final Entity entity) {
		super.onMessage(session, entity);
		printMessage(session, "On message: " + entity);
		sentEntity(new Entity("Thanks..", 5), session);
	}

	/**
	 * Method to react any error during communication with client socket.
	 *
	 * @param session Session of client socket, were was problem.
	 * @param t       Error which occurs.
	 *
	 * @see OnError
	 */
	@Override
	public void onError(final Session session, final Throwable t) {
		super.onError(session, t);
		printMessage(session, "On error: " + t.getMessage());
	}

	/**
	 * Method to react, when client socket going to be disconnect.
	 *
	 * @param session Session of client socket, which will be disconnected.
	 *
	 * @see OnClose
	 */
	@Override
	public void onClose(final Session session) {
		super.onClose(session);
		printMessage(session, "On close...");
	}

	/**
	 * Print simple message with date and session id.
	 */
	private static void printMessage(final Session session, final String message) {
		System.out.println("[" + new Date() + "][" + session.getId() + "][" + EntityReactiveServerWebSocket.class.getCanonicalName() + "] " + message);
	}
}
