package global.models;

import java.time.LocalDateTime;

/**
 * Alle Klassen für Nachrichten, welche zwischen dem Bomberman-Server und den Bomberman-Clients
 * ausgetauscht werden, müssen dieses Interface implementieren.
 * 
 * @author Andres Scheidegger
 *
 */
public class Player extends Tile {

    public Player(String name, int id, int h, int v){
        super(h, v);
        this.name = name;
        this.score = 0;
        this.isAlive = true;
        this.id = id;
        this.rank = 0;
        this.setPlayerMoving(false);
    }

    private boolean playerMoveing;

    private boolean isAlive;

    private String name;

    private int score;

    private int rank;

    private LocalDateTime deathTime;

    public void move(Direction direction){
        switch (direction){
            case UP:
                position.vertical--;
                break;
            case DOWN:
                position.vertical++;
                break;
            case LEFT:
                position.horizontal--;
                break;
            case RIGHT:
                position.horizontal++;
        }
        this.setPlayerMoving(true);
    }

    public void Die(){
        this.deathTime = LocalDateTime.now();
        this.isAlive = false;
        this.rank++;
    }

    public void Score(){
        this.score++;
    }

    public void SetStartPosition(int x, int y){
        this.position = new Position();
        this.position.horizontal = x;
        this.position.vertical = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPlayerMoveing() {
        return playerMoveing;
    }

    public void setPlayerMoving(boolean playerMoveing) {
        this.playerMoveing = playerMoveing;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public String getDeathTimeString(){
        if(deathTime == null){
            return "";
        }
        return deathTime.toString();
    }

    public int getScore(){
        return score;
    }

    public int getRank() {
        return rank;
    }
}
