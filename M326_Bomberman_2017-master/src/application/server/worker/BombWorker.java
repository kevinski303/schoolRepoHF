package application.server.worker;

import application.server.IServerThreadEventHandler;
import global.models.Bomb;

public class BombWorker implements Runnable {

    private IServerThreadEventHandler serverHandler;
    private Bomb bomb;

    public BombWorker(IServerThreadEventHandler serverHandler, Bomb bomb){
        this.serverHandler = serverHandler;
        this.bomb = bomb;
    }

    @Override
    public void run() {
        while (true){
            if(bomb.explode()){
                serverHandler.BombExplodes(bomb);
                return;
            }

            bomb.CountDown();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
