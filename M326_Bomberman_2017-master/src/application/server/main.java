/*package application.server;

import global.models.Direction;
import network.messaging.server.DropBombMessage;
import network.messaging.server.JoinGameMessage;
import network.messaging.server.MovePlayerMessage;

public class main {

    public static void main(String[] args){
        try{

            Server server = new Server("127.0.0.1", 6969);
            server.JoinGameMessageHandler(new JoinGameMessage("p1"));
            server.JoinGameMessageHandler(new JoinGameMessage("p2"));
            server.JoinGameMessageHandler(new JoinGameMessage("p3"));
            server.JoinGameMessageHandler(new JoinGameMessage("p4"));

            Thread.sleep(1000);
            server.MovePlayerMessageHandler(new MovePlayerMessage("p1", Direction.RIGHT));
            Thread.sleep(1000);
            server.MovePlayerMessageHandler(new MovePlayerMessage("p1", Direction.RIGHT));
            Thread.sleep(1000);
            server.MovePlayerMessageHandler(new MovePlayerMessage("p1", Direction.DOWN));
            Thread.sleep(1000);
            server.DropBombMessageHandler(new DropBombMessage("p1", 2, 1));
            server.DropBombMessageHandler(new DropBombMessage("p1", 6, 0));
            server.DropBombMessageHandler(new DropBombMessage("p1", 6, 7));
            Thread.sleep(1000);
            server.MovePlayerMessageHandler(new MovePlayerMessage("p1", Direction.UP));
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}*/
