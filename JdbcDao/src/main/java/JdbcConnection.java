import java.sql.*;


/**
 * Created by KFU-user on 12.10.2016.
 */
public class JdbcConnection {
    private static JdbcConnection instance;
    private Connection connection;
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String NAME = "postgresql";
    private static final String PASSWORD = "postgresql";
    private JdbcConnection(){
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, NAME, PASSWORD);

            Statement statement = connection.createStatement();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    static{
        instance = new JdbcConnection();
    }
    Connection getConnection(){
        return connection;
    }
}
