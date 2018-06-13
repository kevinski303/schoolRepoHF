package application.highscore.gruppe1;

import global.utils.PropertyHandler;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

 class DatabaseConnector {

     private static String DBFILE_KEY = "application.highscore.gruppe1.dbfile";
     public static String DB_INIT = "application.highscore.gruppe1.sql.initdatabase";


     public Connection connect() throws IOException, SQLException {
        String url = PropertyHandler.getProperty(DBFILE_KEY);
        return DriverManager.getConnection(url);
    }
}
