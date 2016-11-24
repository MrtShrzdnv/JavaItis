package dao;

import model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import utils.MessageMapper;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by KFU-user on 24.11.2016.
 */
public class MessageDaoJdbcImpl implements MessageDao {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final String FIND_ALL_QUERY = "SELECT * FROM messages";
    private final String FIND_BY_CHAT_ID_QUERY = "SELECT * FROM messages WHERE chat_id = :chatId";
    private final String FIND_BY_USER_ID_QUERY = "SELECT * FROM messages WHERE user_id = :userId";



    @Autowired
    public MessageDaoJdbcImpl(DataSource dataSource){
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

    }
    @Override
    public List<Message> findAll() {
        List<Message> messages = namedParameterJdbcTemplate.query(FIND_ALL_QUERY, new MessageMapper());
        return messages;
    }

    @Override
    public List<Message> findByChatId(int id) {
        Map namedParameters = new HashMap();
        namedParameters.put("userId", id);
        List<Message> messages = namedParameterJdbcTemplate.query(FIND_BY_USER_ID_QUERY, namedParameters, new MessageMapper());
        return messages;
    }

    @Override
    public List<Message> findByUserId(int id) {
        Map namedParameters = new HashMap();
        namedParameters.put("chatId", id);
        List<Message> messages = namedParameterJdbcTemplate.query(FIND_BY_CHAT_ID_QUERY, namedParameters, new MessageMapper());
        return messages;    }
}
