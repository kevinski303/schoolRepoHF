package application.highscore.gruppe1;


import global.models.Highscore;
import global.models.Player;
import global.utils.PropertyHandler;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InternalHighScoreService extends DatabaseConnector {
    private Connection connection;

    public InternalHighScoreService() throws IOException, SQLException {
        this.connection = super.connect();
        initDatabsae();
    }


    public void addHighscore(Highscore highscore) throws SQLException {

        PreparedStatement preparedStatement = this.connection.prepareStatement(HighscoreEntity.Insert.QUERY);
        preparedStatement.setString(HighscoreEntity.Insert.PARAM_ID, highscore.getId().toString());
        preparedStatement.setString(HighscoreEntity.Insert.PARAM_USER_NAME, highscore.getPlayer().getName());
        preparedStatement.setString(HighscoreEntity.Insert.PARAM_DEATH_TIME, highscore.getDeathTime());
        preparedStatement.setInt(HighscoreEntity.Insert.PARAM_POINTS, highscore.getPoints());
        preparedStatement.setInt(HighscoreEntity.Insert.PARAM_RANK, highscore.getRank());
        preparedStatement.setInt(HighscoreEntity.Insert.PARAM_GAME_ID, highscore.getGameId());

        preparedStatement.execute();
    }

    public List<Highscore> getHighscoresByPlayer(Player player) {
        List<Highscore> highscores = new ArrayList<>();
        try {
            PreparedStatement preparedStatemen = this.connection.prepareStatement(HighscoreEntity.SelectByUserName.QUERY);
            preparedStatemen.setString(HighscoreEntity.SelectByUserName.PARAM_USERNAME, player.getName());
            ResultSet resultSet = preparedStatemen.executeQuery();

            while (resultSet.next()) {
                highscores.add(new Highscore.Builder()
                        .withDeathTime(resultSet.getString(HighscoreEntity.ROW_DEATH_TIME))
                        .withGameId(resultSet.getInt(HighscoreEntity.ROW_GAME_ID))
                        .withPoints(resultSet.getInt(HighscoreEntity.ROW_POINTS))
                        .withRank(resultSet.getInt(HighscoreEntity.ROW_RANK))
                        .withPlayer(player)
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return highscores;
    }


    public void initDatabsae() {
        try {
            String sql = PropertyHandler.getProperty(DatabaseConnector.DB_INIT);
            Statement stmt = this.connection.createStatement();
            stmt.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
