package network.messaging.server;

import network.messaging.IMessage;
import network.messaging.MessageTypes;

/**
 * Diese Meldung wird vom Client an den Server gesendet, wenn der Spieler eine neue Bombe ablegt.
 * Die Meldung enthält den Ort der Bombe.
 * 
 * @author Andres Scheidegger
 *
 */
public class DropBombMessage extends ClientMessage implements IMessage {
  private int positionX;
  private int positionY;
  private int messageType = MessageTypes.DROPBOMB;

  public DropBombMessage(String playerName, int positionX, int positionY) {
    super(playerName);
    this.positionX = positionX;
    this.positionY = positionY;
  }

  public  DropBombMessage(){}

  public int getPositionX() {
    return positionX;
  }

  public int getPositionY() {
    return positionY;
  }

  @Override
  public int getMessageType() {
    return messageType;
  }

  @Override
  public void setMessageType(int messageType) {
    this.messageType = messageType;
  }
}
