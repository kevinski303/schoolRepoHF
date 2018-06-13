package network.messaging.client;

import global.models.Tile;
import network.messaging.IMessage;
import network.messaging.MessageTypes;

public class UpdateTilesMessage implements IMessage {
  private Tile[] tiles;
  private int messageType = MessageTypes.UPDATETILES;

  public UpdateTilesMessage(Tile[] tiles) {
    this.tiles = tiles;
  }

  public  UpdateTilesMessage(){}

  public Tile[] getTiles() {
    return tiles;
  }

  public void setTiles(Tile[] tiles){
    this.tiles = tiles;
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

