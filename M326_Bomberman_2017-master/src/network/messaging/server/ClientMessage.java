package network.messaging.server;

import network.messaging.IMessage;

public abstract class ClientMessage implements IMessage {
    private String playerName;

    public ClientMessage(String playerName) {
        this.playerName = playerName;
    }

    public  ClientMessage(){}

    public String getPlayerName() {
        return playerName;
    }
}
