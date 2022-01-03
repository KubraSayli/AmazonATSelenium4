package helpers;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
    //This class will READ THE DATA FROM config.properties file
    //create a properties instance. Data type=Properties, instance name =properties
    private static Properties properties;
    static {
        //path of the config.properties file
        String path = "config.properties";
        try {
            //Opening the config.properties file
            FileInputStream fileInputStream = new FileInputStream(path);
            //loading the file
            properties = new Properties();
            properties.load(fileInputStream);
            //closing the file
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Create a method to READ
    //This method will get the KEY and return the VALUE
    public static String getProperty(String key){
        return properties.getProperty(key);
    }
}
