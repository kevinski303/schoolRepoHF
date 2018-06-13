package global.models;

import java.io.Serializable;

/**
 * Alle Klassen für Nachrichten, welche zwischen dem Bomberman-Server und den Bomberman-Clients
 * ausgetauscht werden, müssen dieses Interface implementieren.
 * 
 * @author Andres Scheidegger
 *
 */
public class Tile extends BaseModel {
    protected int id;
    protected Position position;

    public Tile(int horizontal, int vertical){
        this.position = new Position(horizontal, vertical);
    }

    public Position getPosition() {
        return position;
    }

    public int getId() {
        return id;
    }
}
