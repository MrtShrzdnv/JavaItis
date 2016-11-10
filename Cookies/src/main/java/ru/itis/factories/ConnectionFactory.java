package ru.itis.factories;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Created by Marat_2 on 24.10.2016.
 */
public class ConnectionFactory {
    private Properties properties;
    private Connection connection;
    private static ConnectionFactory instance = new ConnectionFactory();
    public static ConnectionFactory getInstance() {
        return instance;
    }
    public Connection getConnection(){
        return this.connection;
    }
    private ConnectionFactory() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("C:\\Users\\Marat_2\\Desktop\\JavaItis\\Cookies\\src\\main\\resources\\Connection.properties"));
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
}
