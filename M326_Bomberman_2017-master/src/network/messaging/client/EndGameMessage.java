package network.messaging.client;

import global.models.Highscore;
import network.messaging.IMessage;
import network.messaging.MessageTypes;

public class EndGameMessage implements IMessage {
  private Highscore[] highscores;
  private int messageType = MessageTypes.ENDGAME;

  public EndGameMessage(Highscore[] highscores) {
    this.highscores = highscores;
  }

  public  EndGameMessage(){}

  public Highscore[] getHighscores() {
    return highscores;
  }
  public void setHighscores(Highscore[] value) {
    highscores = value;
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
