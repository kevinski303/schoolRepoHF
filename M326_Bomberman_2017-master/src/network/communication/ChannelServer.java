package network.communication;


import network.messaging.IMessage;
import network.messaging.handlers.IMessageHandler;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChannelServer {

    private boolean _isRunning;

    private ServerSocket _server;

    private String _address;

    private int _port;

    private IMessageHandler _messageHandler;

    private List<Channel> _channels = new ArrayList<>();

    public  ChannelServer(String address, int port , IMessageHandler messageHandler){
        _address = address;
        _port = port;
        _messageHandler = messageHandler;
    }

    public void start() throws IOException {
        InetAddress addr = InetAddress.getByName(_address);
        _server = new ServerSocket(_port, 1, addr);
        _isRunning = true;
        new Thread(() -> {
            try {
                listener();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void broadcast(IMessage message){
        for(Channel channel: _channels){
            channel.send(message);
        }
    }

    private void listener() throws IOException, IllegalAccessException, InstantiationException {
        if (_server != null && _isRunning)
        {
            while (_isRunning)
            {
                Socket client = _server.accept();
                if (client != null)
                {
                    Channel channel = new Channel(client, _messageHandler);
                    _channels.add(channel);
                }
            }
        }
    }
}
