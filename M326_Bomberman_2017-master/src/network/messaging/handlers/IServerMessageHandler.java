package network.messaging.handlers;

import network.messaging.server.*;

public interface IServerMessageHandler extends IMessageHandler {
    void  JoinGameMessageHandler(JoinGameMessage message);

    void MovePlayerMessageHandler(MovePlayerMessage message);

    void DropBombMessageHandler(DropBombMessage message);
}
