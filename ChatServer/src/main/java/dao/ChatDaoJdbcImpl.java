package dao;

import model.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import utils.ChatMapper;

import javax.sql.DataSource;

import java.util.List;


/**
 * Created by KFU-user on 24.11.2016.
 */
@Repository
public class ChatDaoJdbcImpl implements ChatDao {
    //private final String Find_BY_ID_QUERY = "";
    //private final String FIND_BY_NAME_QUERY = "";
    private final String FIND_ALL_QUERY = "SELECT * FROM chats";
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
}
