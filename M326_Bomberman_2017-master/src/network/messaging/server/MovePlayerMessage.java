package network.messaging.server;

import global.models.Direction;
import network.messaging.IMessage;
import network.messaging.MessageTypes;

/**
 * Diese Meldung wird vom Client zum Server gesendet, wenn der Spieler seine Bomberman-Figur um
 * ein Feld verschiebt.
 * Die Meldung enthält die Richtung, in welcher die Figur verschoben wurde.
 * 
 * @author Andres Scheidegger
 *
 */
public class MovePlayerMessage extends ClientMessage implements IMessage {
  private Direction direction;
  private int messageType = MessageTypes.MOVEPLAYER;

  public MovePlayerMessage(String playerName, Direction direction) {
    super(playerName);
    this.direction = direction;
  }

  public  MovePlayerMessage(){}

  public Direction getDirection() {
    return direction;
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
