package network.messaging.client;

import global.models.Player;
import network.messaging.IMessage;
import network.messaging.MessageTypes;

public class PlayerJoinedMessage implements IMessage {
  private Player[] players;
  private int messageType = MessageTypes.PLAYERJOINED;

  public PlayerJoinedMessage(Player[] players) {
    this.players = players;
  }

  public PlayerJoinedMessage(){}

  public Player[] getPlayers() {
    return players;
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
