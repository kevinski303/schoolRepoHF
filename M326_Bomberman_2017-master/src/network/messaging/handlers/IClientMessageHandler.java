package network.messaging.handlers;

import network.messaging.client.*;

public interface IClientMessageHandler extends IMessageHandler {
    void PlayerJoinedMessageHandler(PlayerJoinedMessage message);

    void StartGameMessageHandler(StartGameMessage message);

    void UpdateTilesMessageHandler(UpdateTilesMessage message);

    void EndGameMessageHandler(EndGameMessage message);

    void ErrorMessageHandler(ErrorMessage message);
}
