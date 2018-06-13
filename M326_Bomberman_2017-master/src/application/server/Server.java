package application.server;

import application.highscore.gruppe1.InternalHighScoreService;
import application.server.exceptions.ServerInitializationFailureException;
import application.server.worker.BombWorker;
import application.server.worker.GameUpdateWorker;
import global.models.*;
import network.communication.ChannelServer;
import network.messaging.IMessage;
import network.messaging.client.*;
import network.messaging.handlers.IServerMessageHandler;
import network.messaging.server.DropBombMessage;
import network.messaging.server.JoinGameMessage;
import network.messaging.server.MovePlayerMessage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/***
 * Server part of the application. Gets initialized when a player activates his client as server.
 * The Server orchestrates the game flow, rules, starting and ending the game, updating game objects, etc. It says when and who does something.
 * Furthermore, its the layer between the network part and the data layer. It controls the logic, the incoming and outgoing messages.
 */
public class Server implements IServerMessageHandler, IServerThreadEventHandler {

    private ChannelServer channelServer;
    private Game game;

    private Thread updater;
    private GameUpdateWorker worker;

    private InternalHighScoreService highScoreService;

    public Server(String address, int port) throws ServerInitializationFailureException {
        channelServer = new ChannelServer(address, port, this);
        game = new Game();
        InitializeServer();
    }

    @Override
    public void JoinGameMessageHandler(JoinGameMessage message) {
        System.out.println("JoinGameMessageHandler called.");
        int countPlayer = game.getPlayerCount();

        if(countPlayer >= 4) {
            System.out.println("No more player allowed.");
            return;
        }

        System.out.println("Create new player.");
        countPlayer = game.gamerJoin(message.getPlayerName());
        System.out.println("New player joined with result: " + countPlayer);
        if(countPlayer == 0){
            ErrorMessage errMessage = new ErrorMessage("Player already joined");
            channelServer.broadcast(errMessage);
        } else {
            PlayerJoinedMessage playerJoinMessage = new PlayerJoinedMessage(game.getPlayerArray());
            channelServer.broadcast(playerJoinMessage);
        }

        if(countPlayer == 4){
            System.out.println("4 Player joined the game. Start the game.");
            StartGame();
        }
    }

    @Override
    public void MovePlayerMessageHandler(MovePlayerMessage message) {
        game.movePlayer(message.getPlayerName(), message.getDirection());
    }

    @Override
    public void DropBombMessageHandler(DropBombMessage message) {
        Bomb bomb = new Bomb(message.getPositionX(), message.getPositionY());
        game.placeBomb(bomb);
        BombWorker wkBomb = new BombWorker(this, bomb);
        Thread thBomb = new Thread(wkBomb);
        thBomb.start();
    }

    @Override
    public void BombExplodes(Bomb bomb) {
        System.out.println("Bomb explodes");
        game.bombExplode(bomb);
    }

    @Override
    public void UpdateGame() {
        System.out.println("Game update handler call");

        if(game.getDelta().size() <= 0){
            System.out.println("No delta to send to the clients");
            return;
        }

        if(game.getHowManyPlayersAreAlive() == 1){
            System.out.println("Only one player is alive. End the game");
            EndGame(game.getLastManStanding());
            return;
        }

        UpdateTilesMessage message = new UpdateTilesMessage();
        System.out.println("Delta array: " + game.getDelta());
        message.setTiles(game.getDelta().toArray(new Tile[0]));
        channelServer.broadcast(message);
        game.clearDelta();
        game.resetPlayerMotion();
    }

    /***
     * Does a few things to initialize the server. Further startup operations can be stored here.
     * @throws ServerInitializationFailureException
     */
    private void InitializeServer() throws ServerInitializationFailureException {
        // Setup the communication channel.
        try {
            channelServer.start();
        }catch (Exception e){
            throw new ServerInitializationFailureException("Channel start failed! message: " + e.getMessage());
        }

        // Setup highscore service.
        try {
            highScoreService = new InternalHighScoreService();
        } catch (IOException e) {
            throw new ServerInitializationFailureException("Highscore initialization failed: " + e.getMessage());
        } catch (SQLException e) {
            throw new ServerInitializationFailureException("Highscore initialization failed: " + e.getMessage());
        }

        // Create the playground, which are the basic start tiles like walls, destroyable walls and free tiles.
        game.createPlayground();
    }

    /***
     * Takes care of the needed steps to start a game.
     */
    private void StartGame() {
        System.out.println("Game Starts");

        // Prepare a message for the clients and pack the playground tiles into it.
        // After preparing the message, just send it.
        System.out.println("Prepare the tile array to send it with the start message to the clients.");
        Tile[] tiles = new Tile[game.getPlayground().length*game.getPlayground()[0].length];
        int counter = 0;
        for(int i = 0; i < game.getPlayground().length; i++){
            Tile[] tr = game.getPlayground()[i];
            for (Tile aTr : tr) {
                tiles[counter] = aTr;
                counter++;
            }
        }
        System.out.println("Tile array: " + tiles + "Size width: " + game.getPlaygroundColumnSize() + " height: " + game.getPlaygroundRowSize());
        IMessage message = new StartGameMessage(tiles, game.getPlaygroundColumnSize(), game.getPlaygroundRowSize());
        ((StartGameMessage) message).setTiles(tiles);
        channelServer.broadcast(message);

        // Check periodical if there are changes to the game world.
        // These happens when player move, bombs get dropped etc.
        // Thus we use a separate thread to time the periodical updates.
        worker = new GameUpdateWorker(this);
        updater = new Thread(worker);
        System.out.println("GameUpdateWorker started.");
        updater.start();
    }

    /***
     * Get called when the conditions are given to abort the game. The method calculates the highscore
     * and provide it to the database layer for storing.
     * Then the method broadcasts the EndGameMessage to the clients and stops the update thread.
     * @param lastPlayer
     */
    private void EndGame(Player lastPlayer){
        EndGameMessage endMessage = new EndGameMessage();
        Player[] players = game.getPlayerArray();
        ArrayList<Highscore> highscores = new ArrayList<>();

        for(Player player: players){
            if(lastPlayer != null) {
                try {
                    highScoreService.addHighscore(new Highscore.Builder()
                            .withDeathTime(player.getDeathTimeString())
                            .withGameId(1)
                            .withPoints(player.getScore())
                            .withRank(player.getRank())
                            .withPlayer(player)
                            .build());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            highscores.addAll(highScoreService.getHighscoresByPlayer(player));
            endMessage.setHighscores(highscores.toArray(new Highscore[0]));
        }

        channelServer.broadcast(endMessage);

        // Stop update worker.
        try {
            worker.stopWorker();
            updater.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
