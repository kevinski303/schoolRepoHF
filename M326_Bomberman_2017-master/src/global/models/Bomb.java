package global.models;

import global.constants.GameSettings;

public class Bomb extends Tile {
    private int timer;

    public Bomb(int h, int v){
        super(h, v);
        timer = GameSettings.bombTimer;
    }

    public void CountDown() {
        timer--;
    }

    public boolean explode() {
        return timer <= 0;
    }
}
