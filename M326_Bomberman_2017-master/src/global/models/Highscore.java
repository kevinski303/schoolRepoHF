package global.models;

import java.util.UUID;

/**
 * Alle Klassen für Nachrichten, welche zwischen dem Bomberman-Server und den Bomberman-Clients
 * ausgetauscht werden, müssen dieses Interface implementieren.
 * 
 * @author Andres Scheidegger
 *
 */
public class Highscore {

    private UUID id;
    private String deathTime;

    private Player player;

    private int points;
    private int rank;
    private int gameId; // die Game ID identifiziert  das Spiel

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDeathTime() {
        return deathTime;
    }

    public void setDeathTime(String deathTime) {
        this.deathTime = deathTime;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public static final class Builder {
        private String deathTime;
        private Player player;
        private int points;
        private int rank;
        private int gameId;

        public Builder withDeathTime(String deathTime) {
            this.deathTime = deathTime;
            return this;
        }

        public Builder withPlayer(Player player) {
            this.player = player;
            return this;
        }

        public Builder withPoints(int points) {
            this.points = points;
            return this;
        }

        public Builder withRank(int rank) {
            this.rank = rank;
            return this;
        }

        public Builder withGameId(int gameId) {
            this.gameId = gameId;
            return this;
        }

        public Highscore build() {

            Highscore highscore = new Highscore();

            highscore.id = UUID.randomUUID();
            highscore.gameId = this.gameId;
            highscore.deathTime = this.deathTime;
            highscore.player = this.player;
            highscore.points = this.points;
            highscore.rank = this.rank;
            return highscore;
        }
    }
}
