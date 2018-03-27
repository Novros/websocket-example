package cz.novros.websocket.example.entity.data;

/**
 * Simple entity class with some string and number.
 */
public class Entity {

    private final String msg;
    private final long number;

    public Entity(String msg, long number) {
        this.msg = msg;
        this.number = number;
    }

    public String getMsg() {
        return msg;
    }

    public long getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "msg='" + msg + '\'' +
                ", number=" + number +
                '}';
    }
}
