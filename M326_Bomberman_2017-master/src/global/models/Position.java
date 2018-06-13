package global.models;

import java.io.Serializable;

/**
 * Alle Klassen für Nachrichten, welche zwischen dem Bomberman-Server und den Bomberman-Clients
 * ausgetauscht werden, müssen dieses Interface implementieren.
 * 
 * @author Andres Scheidegger
 *
 */
public class Position extends BaseModel {
    public Position(){}
    public Position(int h, int v){
        vertical = v;
        horizontal = h;
    }
    public int vertical;
    public int horizontal;
}
