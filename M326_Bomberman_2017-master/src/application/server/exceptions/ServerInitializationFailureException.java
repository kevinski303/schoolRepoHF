package application.server.exceptions;

public class ServerInitializationFailureException extends Exception {

    public ServerInitializationFailureException(String message){
        super(message);
    }
}
