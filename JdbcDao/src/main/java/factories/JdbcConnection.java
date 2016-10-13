package factories;

import java.io.FileInputStream;
import java.sql.*;
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
            properties.load(new FileInputStream("C:\\Users\\KFU-user\\Desktop\\JavaItis\\JdbcDao\\src\\main\\resources\\Connection.properties"));
            Class.forName(properties.getProperty("jdbc.driver"));
            String url = properties.getProperty("jdbc.url");
            String username = properties.getProperty("jdbc.username");
            String password = properties.getProperty("jdbc.password");
            connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
        } catch (Exception e) {
            System.out.println(e);
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
