package utils.mapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Marat_2 on 29.11.2016.
 */
public class TokenMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        String token;
        token = resultSet.getString("token");
        return token;
    }
}
