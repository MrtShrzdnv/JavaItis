package dao;

import models.Car;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import utils.UserMapper;

import javax.sql.DataSource;
import java.security.acl.Owner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Marat_2 on 24.10.2016.
 */
public class UsersDaoJdbcImpl implements UsersDao {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String IS_LOGIN_EXIST_QUERY = "SELECT * FROM users WHERE login = :login";
    private static final String FIND_BY_LOGIN_QUERY = "SELECT * FROM users WHERE login = :login";
    private static final String FIND_BY_TOKEN = "SELECT * FROM users WHERE token = :token";
    private static final String IS_TOKEN_EXIST_QUERY = "SELECT * FROM users WHERE token = :token";
    private static final String UPDATE_QUERY = "UPDATE users SET token = :token WHERE user_id = :user_id";
    //Connection connection;
    //private static final String ADD_QUERY = "INSERT INTO users (name, login, password, token) VALUES (?, ?, ?, ?)";
    private static final String ADD_QUERY = "INSERT INTO users (name, login, password) VALUES (:name, :login, :password)";
    private static final String GET_ALL_QUERY = "SELECT * FROM users";
    private static final String IS_REGISTRED = "SELECT * FROM users WHERE login = :login and password = :password";

    public UsersDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
            this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void add(User user) {
            Map namedParameters = new HashMap();
            namedParameters.put("name", user.getName());
            namedParameters.put("login", user.getLogin());
            namedParameters.put("password", user.getPassword());
            namedParameterJdbcTemplate.update(ADD_QUERY, namedParameters);
    }

    public void update(User user) {
        try {
            Map namedParameters = new HashMap();
            namedParameters.put("token", user.getToken());
            namedParameters.put("user_id", user.getId());
            namedParameterJdbcTemplate.update(UPDATE_QUERY, namedParameters);
        } catch (ClassCastException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public List<User> getAll() {
            List<User> users = namedParameterJdbcTemplate.query(GET_ALL_QUERY, new UserMapper());
            return users;
    }

    public User findByLogin(String login) {
            Map namedParameters = new HashMap();
            namedParameters.put("login", login);
            User user = (User)namedParameterJdbcTemplate.queryForObject(FIND_BY_LOGIN_QUERY, namedParameters, new UserMapper());
            return user;
    }

    public User findByToken(String token) {
        Map namedParameters = new HashMap();
        namedParameters.put("token", token);
        User user = (User)namedParameterJdbcTemplate.queryForObject(FIND_BY_TOKEN, namedParameters, new UserMapper());
        return user;
    }

    public boolean isRegistred(String login, String password) {
        Map namedParameters = new HashMap();
        namedParameters.put("login", login);
        namedParameters.put("password", password);
        List<User> users = namedParameterJdbcTemplate.query(IS_REGISTRED, namedParameters, new UserMapper());
        if (users.isEmpty())
            return false;
        else
            return true;
    }

    public boolean isLoginExist(String login) {
        Map namedParameters = new HashMap();
        namedParameters.put("login", login);
        List<User> users = namedParameterJdbcTemplate.query(IS_LOGIN_EXIST_QUERY, namedParameters, new UserMapper());
        if (users.isEmpty())
            return false;
        else
            return true;
    }

    public boolean isTokenExist(String token) {
        Map namedParameters = new HashMap();
        namedParameters.put("token", token);
        List<User> users = namedParameterJdbcTemplate.query(IS_TOKEN_EXIST_QUERY, namedParameters, new UserMapper());
        if (users.isEmpty())
            return false;
        else
            return true;
    }
}
