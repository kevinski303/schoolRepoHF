package network.messaging.client;

import global.models.Tile;
import network.messaging.IMessage;
import network.messaging.MessageTypes;

public class StartGameMessage implements IMessage {
  private Tile[] tiles;
  private int messageType = MessageTypes.STARTEGAME;
  private int playgroundWidth;
  private int playgroundHeight;

  public StartGameMessage(Tile[] tiles, int playgroundWidth, int playgroundHeight) {
    this.tiles = tiles;
    this.playgroundWidth = playgroundWidth;
    this.playgroundHeight = playgroundHeight;
  }

  public StartGameMessage(){}

  public Tile[] getTiles() {
    return tiles;
  }
  public void setTiles(Tile[] tiles) { this.tiles = tiles; }

  @Override
  public int getMessageType() {
    return messageType;
  }

  @Override
  public void setMessageType(int messageType) {
    this.messageType = messageType;
  }

  public int getPlaygroundHeight() {
    return playgroundHeight;
  }

  public int getPlaygroundWidth() {
    return playgroundWidth;
  }
}
