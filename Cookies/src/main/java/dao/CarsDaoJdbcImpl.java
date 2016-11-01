package dao;

import models.Car;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marat_2 on 24.10.2016.
 */
public class CarsDaoJdbcImpl implements CarsDao {
    private Connection connection;
    private static final String ADD_QUERY = "INSERT INTO cars (number, owner_id) VALUES (?, ?)";
    private static final String GET_ALL_QUERY_BY_OWNER_ID = "SELECT * FROM cars WHERE owner_id = ?";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    public CarsDaoJdbcImpl(DataSource dataSource){
        try {
            this.connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void add(Car car) {
        try {
            PreparedStatement ps = connection.prepareStatement(ADD_QUERY);
            ps.setString(1, car.getNumber());
            ps.setInt(2, car.getOwnerId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Car> getAllByOwnerId(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement(GET_ALL_QUERY_BY_OWNER_ID);
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            Car car;
            List<Car> cars = new ArrayList<Car>();
            while (result.next()) {
                car = new Car(result.getInt("car_id"), result.getString("number"), result.getInt("owner_id"));
                cars.add(car);
            }
            return cars;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
}
