package dao;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by KFU-user on 24.11.2016.
 */
public class UserDaoImpl implements UserDao {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final String FIND_ALL_QUERY = "SELECT * FROM messages";
    private final String FIND_BY_ID_QUERY = "SELECT * FROM messages WHERE id = :id";
    private final String FIND_BY_NAME_QUERY = "SELECT * FROM messages WHERE name = :name";
    @Autowired
    public UserDaoImpl(DataSource dataSource){
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    @Override
    public List<User> findAll() {
        List<User> users = namedParameterJdbcTemplate.query(FIND_ALL_QUERY, new UserMapper());
        return users;    }

    @Override
    public List<User> findById(int id) {
        return null;
    }

    @Override
    public List<User> findByName(String name) {
        return null;
    }
}
