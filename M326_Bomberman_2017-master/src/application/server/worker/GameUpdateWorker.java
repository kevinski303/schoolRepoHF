package application.server.worker;

import application.server.IServerThreadEventHandler;

public class GameUpdateWorker implements Runnable {

    private IServerThreadEventHandler handler;

    private boolean shouldRun = true;

    public GameUpdateWorker(IServerThreadEventHandler handler){
        this.handler = handler;
    }

    public void stopWorker(){
        shouldRun = false;
    }

    @Override
    public void run() {
        System.out.println("Update Thread started");
        while (shouldRun){
            try {
                System.out.println("Update thread cycle");
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.UpdateGame();
        }
    }
}
