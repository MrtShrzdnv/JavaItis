package utils;

import model.Message;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by KFU-user on 24.11.2016.
 */
public class MessageMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Message message = new Message();
        message.setId(resultSet.getInt("id"));
        message.setText(resultSet.getString("text"));
        message.setUserId(resultSet.getInt("user_id"));
        message.setChatId(resultSet.getInt("chat_id"));
        return message;
    }
}
