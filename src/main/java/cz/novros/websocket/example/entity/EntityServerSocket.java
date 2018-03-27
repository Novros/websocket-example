package cz.novros.websocket.example.entity;

import cz.novros.websocket.example.entity.data.Entity;
import cz.novros.websocket.example.entity.data.EntityDecoder;
import cz.novros.websocket.example.entity.data.EntityEncoder;
import cz.novros.websocket.example.jetty.Configuration;
import cz.novros.websocket.example.simple.SimpleServerSocket;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;

/**
 * Server socket for sending and receiving {@link Entity}. The important part is define {@link ServerEndpoint#decoders()}
 * for sending and {@link ServerEndpoint#encoders()} for receiving.
 */
@ServerEndpoint(value = Configuration.ENTITY_ENDPOINT, decoders = EntityDecoder.class, encoders = EntityEncoder.class)
public class EntityServerSocket {

    /**
     * Method to react on client socket connection.
     *
     * @param session Session of client socket, which currently was connected.
     * @see OnOpen
     */
    @OnOpen
    public void onOpen(final Session session) {
        printMessage(session, "On open...");
    }

    /**
     * Will receive client {@link Entity}.
     *
     * @param session Session of client socket, which sent the message.
     * @throws IOException Is thrown when was problem with sending message to client socket.
     * @see OnMessage
     */
    @OnMessage
    public void onMessage(final Session session, final Entity entity) throws IOException, EncodeException {
        printMessage(session, "On message: " + entity);
        session.getBasicRemote().sendObject(new Entity("Thanks...", 2));
    }

    /**
     * Method to react any error during communication with client socket.
     *
     * @param session Session of client socket, were was problem.
     * @param t       Error which occurs.
     * @see OnError
     */
    @OnError
    public void onError(final Session session, Throwable t) {
        printMessage(session, "On error: " + t.getMessage());
    }

    /**
     * Method to react, when client socket going to be disconnect.
     *
     * @param session Session of client socket, which will be disconnected.
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
