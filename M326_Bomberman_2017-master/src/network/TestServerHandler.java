package network;

import global.models.Player;
import network.communication.ChannelServer;
import network.messaging.client.PlayerJoinedMessage;
import network.messaging.handlers.IServerMessageHandler;
import network.messaging.server.DropBombMessage;
import network.messaging.server.JoinGameMessage;
import network.messaging.server.MovePlayerMessage;

public class TestServerHandler implements IServerMessageHandler {

    private ChannelServer _channelServer;

    public void setChannelServer(ChannelServer channelServer){
        _channelServer = channelServer;
    }

    @Override
    public void JoinGameMessageHandler(JoinGameMessage message) {
        /*_channelServer.broadcast(new PlayerJoinedMessage(new Player[] { new Player(message.getPlayerName(),1) }));*/
    }

    @Override
    public void MovePlayerMessageHandler(MovePlayerMessage message) {

    }

    @Override
    public void DropBombMessageHandler(DropBombMessage message) {

    }
}
