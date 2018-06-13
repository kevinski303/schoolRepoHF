package network.communication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import network.messaging.IMessage;
import network.messaging.handlers.IMessageHandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.*;

public class Channel {

    private final Charset UTF8_CHARSET = Charset.forName("UTF-8");

    private Socket _socket;

    private boolean _isConnected;

    private List<IMessage> _queue = new ArrayList<>();

    Map<Integer,Type> _handlerMapping = new HashMap<>();

    private IMessageHandler _messageHandler;

    public Channel(String address, int port, IMessageHandler messageHandler) throws IOException, InstantiationException, IllegalAccessException {
        _socket = new Socket(address, port);
        _isConnected = true;
        _messageHandler = messageHandler;
        loadMessageHandlerMapping(messageHandler);
        startThreads();
    }

    public Channel(Socket socket, IMessageHandler messageHandler) throws InstantiationException, IllegalAccessException {
        _socket = socket;
        _isConnected = true;
        _messageHandler = messageHandler;
        loadMessageHandlerMapping(messageHandler);
        startThreads();
    }

    public void send(IMessage message)
    {
        _queue.add(message);
    }

    private void _writeStream(Socket socket) throws IOException, InterruptedException {
        Gson gson = new GsonBuilder().create();
        while (_isConnected)
        {
            if (_queue.size() > 0)
            {
                Optional<IMessage> nextPackage = _queue.stream().findFirst();
                if (nextPackage.isPresent())
                {
                    String jsonObject = gson.toJson(nextPackage.get());
                    byte[] data = jsonObject.getBytes(UTF8_CHARSET);
                    byte[] packageLength = ByteBuffer.allocate(4).putInt(data.length).array();
                    byte[] packageType = ByteBuffer.allocate(4).putInt(nextPackage.get().getMessageType()).array();
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
                    outputStream.write(packageLength);
                    outputStream.write(packageType);
                    outputStream.write(data);
                    byte[] createdPackage = outputStream.toByteArray();
                    socket.getOutputStream().write(createdPackage);
                }
                _queue.remove(nextPackage.get());
            }
            else
            {
                Thread.sleep(1);
            }
        }
    }

    private void _readStream(Socket socket) throws IOException, InvocationTargetException, IllegalAccessException {
        Gson gson = new GsonBuilder().create();
        byte[] packageLength = new byte[4];
        int packageLengthReadCount = 0;
        byte[] packageType = new byte[4];
        int packageTypeReadCount = 0;
        int packageDataReadCount = 0;
        while (_isConnected)
        {
            packageLengthReadCount = 0;
            packageTypeReadCount = 0;
            packageDataReadCount = 0;
            while ((packageLengthReadCount += socket.getInputStream().read(packageLength, 0, packageLength.length)) < packageLength.length) ;
            while ((packageTypeReadCount += socket.getInputStream().read(packageType, 0, packageType.length)) < packageType.length) ;
            ByteBuffer wrappedPackageLength = ByteBuffer.wrap(packageLength); // big-endian by default
            byte[] buffer = new byte[wrappedPackageLength.getInt()];
            while ((packageDataReadCount += socket.getInputStream().read(buffer, 0, buffer.length)) < buffer.length) ;

            ByteBuffer wrappedPackageType = ByteBuffer.wrap(packageType);
            Type foundType = _handlerMapping.get(wrappedPackageType.getInt());
            if (foundType != null)
            {
                String jsonObject = new String(buffer, UTF8_CHARSET);
                IMessage deserializedObject = gson.fromJson(jsonObject, foundType);
                Method[] methods = _messageHandler.getClass().getDeclaredMethods();
                Optional<Method> foundMethod = Arrays.stream(methods).filter(m -> m.getParameters().length == 1 && m.getParameters()[0].getType() == foundType).findAny();
                if(foundMethod.isPresent()){
                    foundMethod.get().invoke(_messageHandler, deserializedObject);
                }
            }
        }
    }

    private void startThreads()
    {
        new Thread(() -> {
            try {
                _writeStream(_socket);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                _readStream(_socket);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void loadMessageHandlerMapping(IMessageHandler messageHandler) throws IllegalAccessException, InstantiationException {
        Method[] methods = messageHandler.getClass().getMethods();
        for (Method method: methods) {
            Parameter[] parameters = method.getParameters();
            if(parameters.length == 1){
                Type type = parameters[0].getType();
                Class[] interfaces = ((Class) type).getInterfaces();
                if(Arrays.stream(interfaces).anyMatch(i -> i.getTypeName() == IMessage.class.getTypeName())){
                    IMessage message = (IMessage)((Class) type).newInstance();
                    _handlerMapping.put(message.getMessageType(), type);
                }
            }
        }
    }
}
