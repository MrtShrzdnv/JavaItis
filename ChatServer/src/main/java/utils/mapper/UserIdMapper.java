package utils.mapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Marat_2 on 24.11.2016.
 */
public class UserIdMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Integer id = resultSet.getInt("user_id");
        return id;
    }
}
