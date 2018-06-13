package application.server;

import global.models.Bomb;

/**
 * Defines the methods a class must implement to handle events called from threads.
 */
public interface IServerThreadEventHandler {

    /**
     * Gets called when the bomb timer thread is over and the bomb detonates.
     * @param bomb The bomb that explodes.
     */
    void BombExplodes(Bomb bomb);

    /**
     * Gets called from the game update timer, when the next round of changes should be send to the clients.
     */
    void UpdateGame();
}
