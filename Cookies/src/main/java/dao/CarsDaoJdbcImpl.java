package dao;

import models.Car;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import utils.CarMapper;
import utils.UserMapper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Marat_2 on 24.10.2016.
 */
public class CarsDaoJdbcImpl implements CarsDao {
    //private Connection connection;
    private static final String ADD_QUERY = "INSERT INTO cars (number, owner_id) VALUES (:number, :owner_id)";
    private static final String GET_ALL_QUERY_BY_OWNER_ID = "SELECT * FROM cars WHERE owner_id = :owner_id";

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    public CarsDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
    public void add(Car car) {
        Map namedParameters = new HashMap();
        namedParameters.put("number", car.getNumber());
        namedParameters.put("owner_id", car.getOwnerId());
        namedParameterJdbcTemplate.update(ADD_QUERY, namedParameters);
    }

    public List<Car> getAllByOwnerId(int id) {
        Map namedParameters = new HashMap();
        namedParameters.put("owner_id", id);
        List<Car> cars = namedParameterJdbcTemplate.query(GET_ALL_QUERY_BY_OWNER_ID, namedParameters, new CarMapper());
        return cars;
    }
}
