package dao;

import model.Chat;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import utils.UserIdMapper;
import utils.UserMapper;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by KFU-user on 24.11.2016.
 */
@Repository
public class UserDaoImpl implements UserDao {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private static final String FIND_ALL_QUERY = "SELECT * FROM chat_user";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM chat_user WHERE id = :id";
    private static final String FIND_BY_NAME_QUERY = "SELECT * FROM chat_user WHERE name = :name";
    private static final String ADD_QUERY = "INSERT INTO chat_user (name) VALUES (:name)";
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
    public void save(User user) {
        Map namedParameters = new HashMap();
        namedParameters.put("name", user.getName());
        namedParameterJdbcTemplate.update(ADD_QUERY, namedParameters);
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
    public void delete(int id) {
        Map namedParameters = new HashMap();
        namedParameters.put("userId", id);
        namedParameterJdbcTemplate.update(DELETE_BY_ID_QUERY, namedParameters);
    }

    @Override
    public void saveUserToChat(User user, Chat chat) {
        Map namedParameters = new HashMap();
        namedParameters.put("userId", user.getId());
        namedParameters.put("chatId", chat.getId());
        namedParameterJdbcTemplate.update(ADD_USER_TO_CHAT_QUERY, namedParameters);
    }
}
