package utils.mapper;

import model.Chat;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by KFU-user on 24.11.2016.
 */
public class ChatMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Chat chat = new Chat();
        chat.setId(resultSet.getInt("id"));
        chat.setName(resultSet.getString("name"));
        chat.setOwnerId(resultSet.getInt("owner_id"));
        return chat;
    }
}
