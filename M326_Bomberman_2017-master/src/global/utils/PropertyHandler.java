package global.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyHandler {

    public static String getProperty(String key) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("./resources/config.properties"));
        return properties.getProperty(key);
    }
}
