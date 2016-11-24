package utils;

import model.Chat;
import model.ChatDto;
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
        return chat;
    }
}
