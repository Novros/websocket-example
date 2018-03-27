package cz.novros.websocket.example.entity.data;

import com.google.gson.Gson;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * JSON decoder for {@link Entity}.
 *
 * @see Decoder
 */
public class EntityDecoder implements Decoder.Text<Entity> {

    private static final Gson gson = new Gson();

    public Entity decode(String s) throws DecodeException {
        return gson.fromJson(s, Entity.class);
    }

    public boolean willDecode(String s) {
        return false;
    }

    public void init(EndpointConfig endpointConfig) {

    }

    public void destroy() {

    }
}
