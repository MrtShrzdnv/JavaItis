package dao;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import utils.mapper.TokenMapper;
import utils.mapper.UserIdMapper;
import utils.mapper.UserMapper;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by KFU-user on 24.11.2016.
 */
@Repository
public class UserDaoImpl implements UserDao {
    private static final String IS_REGISTRED_QUERY = "SELECT * FROM chat_user WHERE login = :login and hash_password = :hash_password";
    private static final String IS_LOGIN_EXIST_QUERY = "SELECT * FROM chat_user WHERE login = :login";
    private static final String FIND_USER_ID_BY_TOKEN_QUERY = "SELECT * FROM auth WHERE token = :token";
    private static final String FIND_BY_LOGIN_AND_PASSWORD_QUERY = "SELECT * FROM chat_user WHERE login = :login and hash_password = :hash_password";;
    private static final String FIND_LAST_MESSAGE_ID_QUERY = "SELECT * FROM user_message WHERE userId = :userId and chatId = :chatId";
    private static final String SAVE_LAST_MESSAGE_TOKEN_QUERY = "INSERT INTO user_message (user_id, chat_id) VALUES (:userId, :chatId)";
    private static final String UPDATE_LAST_MESSAGE_TOKEN_QUERY = "UPDATE user_message SET last_message_id = :messageId WHERE user_id = :userId and chat_id = :chatId";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private static final String FIND_ALL_QUERY = "SELECT * FROM chat_user";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM chat_user WHERE id = :id";
    private static final String FIND_BY_NAME_QUERY = "SELECT * FROM chat_user WHERE name = :name";
    private static final String ADD_QUERY = "INSERT INTO chat_user (name, login, hash_password) VALUES (:name, :login, :hash_password)";
    private static final String UPDATE_QUERY = "UPDATE chat_user SET name = :name, login = :login, hash_password = :hash_password WHERE id = :userId";
    private static final String ADD_USER_TO_CHAT_QUERY = "INSERT INTO user_feat_chat (chat_id, user_id) VALUES ( :chatId, :userId)";
    private static final String FIND_ALL_BY_CHAT_ID_QUERY = "SELECT user_id FROM user_feat_chat WHERE chat_id = :chatId";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM chat_user WHERE id = :userId";
    private static final String SAVE_TOKEN_QUERY = "INSERT INTO auth (user_id, token) VALUES (:userId, token)";
    private static final String UPDATE_TOKEN_QUERY = "UPDATE auth SET token = :token WHERE user_id = :userId";


    @Autowired
    public UserDaoImpl(DataSource dataSource){
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    @Override
    public List<User> findAll() {
        List<User> users = namedParameterJdbcTemplate.query(FIND_ALL_QUERY, new UserMapper());
        return users;
    }

    @Override
    public List<Integer> findAllByChatId(Integer id) {
        Map namedParameters = new HashMap();
        namedParameters.put("chatId", id);
        List<Integer> usersId = namedParameterJdbcTemplate.query(FIND_ALL_BY_CHAT_ID_QUERY, namedParameters, new UserIdMapper());
        return usersId;
    }

    @Override
    public User findById(Integer id) {
        Map namedParameters = new HashMap();
        namedParameters.put("id", id);
        User users = (User)namedParameterJdbcTemplate.queryForObject(FIND_BY_ID_QUERY, namedParameters, new UserMapper());
        return users;
    }

    @Override
    public User findByName(String name){
        Map namedParameters = new HashMap();
        namedParameters.put("name", name);
        User users = (User)namedParameterJdbcTemplate.queryForObject(FIND_BY_NAME_QUERY, namedParameters, new UserMapper());
        return users;
    }

    @Override
    public User findByLoginAndPassword(String login, String hash_password) {
        Map namedParameters = new HashMap();
        namedParameters.put("login", login);
        namedParameters.put("password", hash_password);
        User users = (User)namedParameterJdbcTemplate.queryForObject(FIND_BY_LOGIN_AND_PASSWORD_QUERY, namedParameters, new UserMapper());
        return users;
    }

    @Override
    public void save(User user) {
        Map namedParameters = new HashMap();
        namedParameters.put("name", user.getName());
        namedParameters.put("login", user.getLogin());
        namedParameters.put("hash_password", user.getHashPassword());
        namedParameterJdbcTemplate.update(ADD_QUERY, namedParameters);
    }

    @Override
    public void update(User user) {
        Map namedParameters = new HashMap();
        namedParameters.put("userId", user.getId());
        namedParameters.put("name", user.getName());
        namedParameters.put("login", user.getLogin());
        namedParameters.put("hash_password", user.getHashPassword());
        namedParameterJdbcTemplate.update(UPDATE_QUERY, namedParameters);
    }

    @Override
    public void saveToken(Integer userId, String token) {
        Map namedParameters = new HashMap();
        namedParameters.put("userId", userId);
        namedParameters.put("token", token);
        namedParameterJdbcTemplate.update(SAVE_TOKEN_QUERY, namedParameters);
    }

    @Override
    public void updateToken(Integer userId, String token) {
        Map namedParameters = new HashMap();
        namedParameters.put("userId", userId);
        namedParameters.put("token", token);
        namedParameterJdbcTemplate.update(UPDATE_TOKEN_QUERY, namedParameters);
    }

    @Override
    public void saveLastMessage(Integer userId, Integer chatId) {
        Map namedParameters = new HashMap();
        namedParameters.put("userId", userId);
        namedParameters.put("chatId", chatId);
        namedParameterJdbcTemplate.update(SAVE_LAST_MESSAGE_TOKEN_QUERY, namedParameters);

    }

    @Override
    public void updateLastMessage(Integer userId, Integer chatId, Integer messageId) {
        Map namedParameters = new HashMap();
        namedParameters.put("userId", userId);
        namedParameters.put("chatId", chatId);
        namedParameters.put("messageId", messageId);
        namedParameterJdbcTemplate.update(UPDATE_LAST_MESSAGE_TOKEN_QUERY, namedParameters);
    }

    @Override
    public void delete(int id) {
        Map namedParameters = new HashMap();
        namedParameters.put("userId", id);
        namedParameterJdbcTemplate.update(DELETE_BY_ID_QUERY, namedParameters);
    }

    @Override
    public void saveUserToChat(Integer userId, Integer chatId) {
        Map namedParameters = new HashMap();
        namedParameters.put("userId", userId);
        namedParameters.put("chatId", chatId);
        namedParameterJdbcTemplate.update(ADD_USER_TO_CHAT_QUERY, namedParameters);
    }

    @Override
    public boolean isRegistred(String login, String hash_password) {
        Map namedParameters = new HashMap();
        namedParameters.put("login", login);
        namedParameters.put("hash_password", hash_password);
        List<User> users = namedParameterJdbcTemplate.query(IS_REGISTRED_QUERY, namedParameters, new UserMapper());
        if (users.isEmpty())
            return false;
        else
            return true;
    }

    @Override
    public boolean isLoginExist(String login) {
        Map namedParameters = new HashMap();
        namedParameters.put("login", login);
        List<User> users = namedParameterJdbcTemplate.query(IS_LOGIN_EXIST_QUERY, namedParameters, new UserMapper());
        if (users.isEmpty())
            return false;
        else
            return true;
    }

    @Override
    public boolean isTokenExist(String token) {
        Map namedParameters = new HashMap();
        namedParameters.put("token", token);
        Integer tokenR = (Integer)namedParameterJdbcTemplate.queryForObject(FIND_USER_ID_BY_TOKEN_QUERY, namedParameters, new TokenMapper());
        if (tokenR == null)
            return false;
        else
            return true;
    }

    @Override
    public Integer findUserIdByToken(String token) {
        Map namedParameters = new HashMap();
        namedParameters.put("token", token);
        Integer id = (Integer)namedParameterJdbcTemplate.queryForObject(FIND_USER_ID_BY_TOKEN_QUERY, namedParameters, new UserIdMapper());
        return id;
    }

    @Override
    public Integer findLastMessageId(Integer userId, Integer chatId) {
        Map namedParameter = new HashMap();
        namedParameter.put("userId", userId);
        namedParameter.put("chatId", chatId);
        List<Integer> id = namedParameterJdbcTemplate.queryForList(FIND_LAST_MESSAGE_ID_QUERY, namedParameter);
        return id.get(0);
    }
}
