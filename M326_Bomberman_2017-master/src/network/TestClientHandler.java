package network;

import network.communication.ChannelServer;
import network.messaging.client.*;
import network.messaging.handlers.IClientMessageHandler;

public class TestClientHandler implements IClientMessageHandler {

    @Override
    public void PlayerJoinedMessageHandler(PlayerJoinedMessage message) {

    }

    @Override
    public void StartGameMessageHandler(StartGameMessage message) {

    }

    @Override
    public void UpdateTilesMessageHandler(UpdateTilesMessage message) {

    }

    @Override
    public void EndGameMessageHandler(EndGameMessage message) {

    }

    @Override
    public void ErrorMessageHandler(ErrorMessage message) {

    }
}
