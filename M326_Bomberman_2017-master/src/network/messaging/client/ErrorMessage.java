package network.messaging.client;

import network.messaging.IMessage;
import network.messaging.MessageTypes;

public class ErrorMessage implements IMessage {
  private String errorMessage;
  private int messageType = MessageTypes.ERROR;

  public ErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public  ErrorMessage(){}

  public String getErrorMessage() {
    return errorMessage;
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
