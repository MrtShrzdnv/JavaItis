package dao;

import models.Car;
import models.User;

import java.security.acl.Owner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marat_2 on 24.10.2016.
 */
public class UsersDaoJdbcImpl implements UsersDao {
    private static final String IS_LOGIN_EXIST_QUERY = "SELECT * FROM users WHERE login = ?";
    private static final String FIND_BY_TOKEN = "SELECT * FROM users WHERE token = ?";
    private static final String UPDATE_QUERY = "UPDATE users SET token = ? WHERE user_id = ?";
    Connection connection;
    private static final String ADD_QUERY = "INSERT INTO users (name, login, password, token) VALUES (?, ?, ?, ?)";
    private static final String GET_ALL_QUERY = "SELECT * FROM users";
    private static final String IS_REGISTRED = "SELECT * FROM users WHERE login = ? and password = ?";
    public UsersDaoJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    public void add(User user) {
        try {
            PreparedStatement ps = connection.prepareStatement(ADD_QUERY);
            ps.setString(1, user.getName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getToken());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
            statement.setString(1, user.getToken());
            statement.setInt(2, user.getId());
            statement.execute();
        } catch (ClassCastException e) {
            throw new IllegalArgumentException(e);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public List<User> getAll() {
        try {
            ResultSet result = connection.createStatement().executeQuery(GET_ALL_QUERY);
            User user;
            List<User> users = new ArrayList<User>();
            while (result.next()) {
                user = new User(result.getInt("user_id"), result.getString("name"), result.getString("login"), result.getString("password"), result.getString("token"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public User findByLogin(String login) {
        try {
            PreparedStatement statement = connection.prepareStatement(IS_LOGIN_EXIST_QUERY);
            statement.setString(1, login);
            ResultSet result = statement.executeQuery();
            result.next();
            return new User(result.getInt("user_id"), result.getString("name"), result.getString("login"), result.getString("password"));
        } catch (SQLException e) {
            System.out.print("not found");
            throw new IllegalArgumentException(e);
        }
    }

    public User findByToken(String token) {
        try {
            PreparedStatement statement = connection.prepareStatement(FIND_BY_TOKEN);
            statement.setString(1, token);
            ResultSet result = statement.executeQuery();
            result.next();
            return new User(result.getInt("user_id"), result.getString("name"), result.getString("login"), result.getString("password"), result.getString("token"));
        } catch (SQLException e) {
            System.out.print("not found");
            throw new IllegalArgumentException(e);
        }
    }

    public boolean isRegistred(String login, String password) {
        try {
            PreparedStatement ps = connection.prepareStatement(IS_REGISTRED);
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet result = ps.executeQuery();
            return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isLoginExist(String login) {
        try {
            ResultSet result = connection.createStatement().executeQuery(IS_LOGIN_EXIST_QUERY + login);
            return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
