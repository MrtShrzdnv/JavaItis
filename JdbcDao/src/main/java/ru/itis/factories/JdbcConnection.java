package ru.itis.factories;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


/**
 * Created by KFU-user on 12.10.2016.
 */
public class JdbcConnection {
    private static JdbcConnection instance;
    private Connection connection;
    private Properties properties;
    //private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    //private static final String NAME = "postgres";
    //private static final String PASSWORD = "postgresql";
    private JdbcConnection(){
        properties = new Properties();
        try {
            properties.load(new FileInputStream("C:\\Users\\Marat_2\\Desktop\\JavaItis\\JdbcDao\\src\\main\\resources\\Connection.properties"));
            String driver = properties.getProperty("jdbc.driver");
            Class.forName(driver);
            String url = properties.getProperty("jdbc.url");
            String username = properties.getProperty("jdbc.username");
            String password = properties.getProperty("jdbc.password");
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
    public static JdbcConnection getInstance() {
        return instance;
    }
    static{
        instance = new JdbcConnection();
    }
    public Connection getConnection(){
        return connection;
    }
}
