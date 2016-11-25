package dao;

import model.Chat;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import utils.ChatMapper;

import javax.sql.DataSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by KFU-user on 24.11.2016.
 */
@Repository
public class ChatDaoJdbcImpl implements ChatDao {
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM chat WHERE id = :id";
    private static final String FIND_BY_NAME_QUERY = "SELECT * FROM chat WHERE name = :name";
    private static final String FIND_ALL_QUERY = "SELECT * FROM chat";
    private static final String ADD_QUERY = "INSERT INTO chat (name) VALUES (:name)";


    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    public ChatDaoJdbcImpl(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Chat> findAll() {
        List<Chat> chats = namedParameterJdbcTemplate.query(FIND_ALL_QUERY, new ChatMapper());
        return chats;
    }

    @Override
    public Chat findById(int id) {
        Map namedParameters = new HashMap();
        namedParameters.put("id", id);
        Chat chats = (Chat)namedParameterJdbcTemplate.queryForObject(FIND_BY_ID_QUERY, namedParameters, new ChatMapper());
        return chats;
    }

    @Override
    public Chat findByName(String name) {
        Map namedParameters = new HashMap();
        namedParameters.put("name", name);
        Chat chats = (Chat)namedParameterJdbcTemplate.queryForObject(FIND_BY_NAME_QUERY, namedParameters, new ChatMapper());
        return chats;
    }

    @Override
    public void save(Chat chat) {
        Map namedParameters = new HashMap();
        namedParameters.put("name", chat.getName());
        namedParameterJdbcTemplate.update(ADD_QUERY, namedParameters);
    }

}
