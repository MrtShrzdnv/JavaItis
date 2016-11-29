package dao;

import model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import utils.mapper.LastMessageMapper;
import utils.mapper.MessageMapper;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by KFU-user on 24.11.2016.
 */
@Repository
public class MessageDaoJdbcImpl implements MessageDao {
    private static final String FIND_LAST_MESSAGE_QUERY = "SELECT * FROM user_message WHERE user_id = :userId and chat_id = :chatId";
    private static final String UPDATE_LAST_MESSAGE_QUERY = "UPDATE user_message SET last_message_id = :lastMessageId WHERE user_id = :userId, chat_id = :chatId";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private static final String FIND_ALL_QUERY = "SELECT * FROM message";
    private static final String FIND_BY_CHAT_ID_QUERY = "SELECT * FROM message WHERE chat_id = :chatId";
    private static final String FIND_BY_USER_ID_QUERY = "SELECT * FROM message WHERE user_id = :userId";
    private static final String ADD_QUERY = "INSERT INTO message (text, chat_id, user_id) VALUES (:text, :chatId, :userId)";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM message WHERE id = :messageId";

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
    public List<Message> findAllByChatId(Integer id) {
        Map namedParameters = new HashMap();
        namedParameters.put("userId", id);
        List<Message> messages = namedParameterJdbcTemplate.query(FIND_BY_USER_ID_QUERY, namedParameters, new MessageMapper());
        return messages;
    }

    @Override
    public List<Message> findAllByUserId(Integer id) {
        Map namedParameters = new HashMap();
        namedParameters.put("chatId", id);
        List<Message> messages = namedParameterJdbcTemplate.query(FIND_BY_CHAT_ID_QUERY, namedParameters, new MessageMapper());
        return messages;
    }

    @Override
    public Integer findLastMessage(Integer userId, Integer chatId) {
        Map namedParameters = new HashMap();
        namedParameters.put("userId", userId);
        namedParameters.put("chatId", chatId);
        Integer lastMessageId = (Integer)namedParameterJdbcTemplate.queryForObject(FIND_LAST_MESSAGE_QUERY, namedParameters, new LastMessageMapper());
        return lastMessageId;
    }

    @Override
    public void saveLastMessage(Integer userId, Integer chatId, Integer lastMessageId) {
        Map namedParameters = new HashMap();
        namedParameters.put("userId", userId);
        namedParameters.put("chatId", chatId);
        namedParameters.put("lastMessageId", lastMessageId);
        namedParameterJdbcTemplate.update(UPDATE_LAST_MESSAGE_QUERY, namedParameters);
    }

    @Override
    public void save(Message message) {
        Map namedParameters = new HashMap();
        namedParameters.put("text", message.getText());
        namedParameters.put("chatId", message.getChatId());
        namedParameters.put("userId", message.getUserId());
        namedParameterJdbcTemplate.update(ADD_QUERY, namedParameters);
    }

    @Override
    public void delete(int id) {
        Map namedParameters = new HashMap();
        namedParameters.put("messageId", id);
        namedParameterJdbcTemplate.update(DELETE_BY_ID_QUERY, namedParameters);
    }
}
