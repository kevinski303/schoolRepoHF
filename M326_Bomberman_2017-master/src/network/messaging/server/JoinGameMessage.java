package network.messaging.server;

import network.messaging.IMessage;
import network.messaging.MessageTypes;

public class JoinGameMessage extends ClientMessage implements IMessage {
  private int messageType = MessageTypes.JOINGAME;

  public JoinGameMessage(String playerName)
  {
    super(playerName);
  }

  public JoinGameMessage(){}

  @Override
  public int getMessageType() {
    return messageType;
  }

  @Override
  public void setMessageType(int messageType) {
    this.messageType = messageType;
  }
}
