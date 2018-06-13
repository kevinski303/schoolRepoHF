package application.highscore.gruppe1;

public class HighscoreEntity {
    public static String ROW_ID = "ID";
    public static String ROW_USER_NAME = "USER_NAME";
    public static String ROW_DEATH_TIME = "DEATH_TIME";
    public static String ROW_POINTS = "POINTS";
    public static String ROW_RANK = "RANK";
    public static String ROW_GAME_ID = "GAME_ID";


    public static class Insert {
        public static String QUERY = "insert into T_HIGHSCORE (ID, USER_NAME, DEATH_TIME, POINTS, RANK, GAME_ID) values (?,?,?,?,?,?)";
        public static int PARAM_ID = 1;
        public static int PARAM_USER_NAME = 2;
        public static int PARAM_DEATH_TIME = 3;
        public static int PARAM_POINTS = 4;
        public static int PARAM_RANK = 5;
        public static int PARAM_GAME_ID = 6;
    }

    public static class SelectByUserName {
        public static String QUERY = "select ID, USER_NAME, DEATH_TIME, POINTS, RANK, GAME_ID FROM T_HIGHSCORE WHERE USER_NAME=?";
        public static int PARAM_USERNAME = 1;
    }
}
