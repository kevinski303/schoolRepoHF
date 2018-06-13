package network.messaging;

import java.io.Serializable;

/**
 * Alle Klassen für Nachrichten, welche zwischen dem Bomberman-Server und den Bomberman-Clients
 * ausgetauscht werden, müssen dieses Interface implementieren.
 * 
 * @author Andres Scheidegger
 *
 */
public interface IMessage extends Serializable {
    int getMessageType();
    void setMessageType(int messageType);
}
