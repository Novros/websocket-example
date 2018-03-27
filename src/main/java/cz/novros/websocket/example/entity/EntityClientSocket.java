package cz.novros.websocket.example.entity;

import cz.novros.websocket.example.entity.data.Entity;
import cz.novros.websocket.example.entity.data.EntityDecoder;
import cz.novros.websocket.example.entity.data.EntityEncoder;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;

/**
 * Client socket for receiving {@link Entity} as message. The important thing is define {@link ClientEndpoint#decoders()},
 * where must be implementation of {@link javax.websocket.Decoder} and {@link ClientEndpoint#encoders()}, where should
 * be implementation {@link javax.websocket.Encoder}.
 */
@ClientEndpoint(decoders = EntityDecoder.class, encoders = EntityEncoder.class)
public class EntityClientSocket {

    /**
     * Will receive message, which defined by {@link OnMessage}, from {@link javax.websocket.server.ServerEndpoint} (server socket).
     *
     * @see OnMessage
     */
    @OnMessage
    public void onMessage(final Entity entity) {
        System.out.println("On message: " + entity);
    }
}
