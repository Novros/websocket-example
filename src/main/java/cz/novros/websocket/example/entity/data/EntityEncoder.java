package cz.novros.websocket.example.entity.data;

import com.google.gson.Gson;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * JSON encoder for {@link Entity}.
 *
 * @see Encoder
 */
public class EntityEncoder implements Encoder.Text<Entity> {

    private static Gson gson = new Gson();

    public String encode(Entity entity) throws EncodeException {
        return gson.toJson(entity);
    }

    public void init(EndpointConfig endpointConfig) {
    }

    public void destroy() {
    }
}
