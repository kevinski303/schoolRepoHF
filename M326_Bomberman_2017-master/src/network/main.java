package network;

import network.communication.Channel;
import network.communication.ChannelServer;
import network.messaging.server.JoinGameMessage;

import java.io.IOException;

public class main {
    public static void main(String[] args) throws IllegalAccessException, IOException, InstantiationException, InterruptedException {
        TestServerHandler serverHandler = new TestServerHandler();
        ChannelServer channelServer = new ChannelServer("127.0.0.1", 5000, serverHandler);
        serverHandler.setChannelServer(channelServer);
        channelServer.start();
        System.out.println("Server started");
        Thread.sleep(2000);
        Channel channel = new Channel("127.0.0.1",5000, new TestClientHandler());
        System.out.println("Client started");
        Thread.sleep(2000);
        channel.send(new JoinGameMessage("Hans"));
        System.out.println("Message sent");


        new application.client.Main();
        System.out.println("GUI(1) started..");
        new application.client.Main();
        System.out.println("GUI(2) started..");
    }
}