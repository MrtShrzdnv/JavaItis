package dao;

import models.Car;
import models.User;

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
    private static final String ADD_QUERY = "INSERT INTO cars (car_id, number, owner_id) VALUES (?, ?, ?)";
    private static final String GET_ALL_QUERY_BY_OWNER_ID = "SELECT * FROM cars WHERE owner_id = ?";
    public CarsDaoJdbcImpl(Connection connection){
        this.connection = connection;
    }
    public void add(Car car) {
        try {
            PreparedStatement ps = connection.prepareStatement(ADD_QUERY);
            ps.setInt(1, car.getId());
            ps.setString(2, car.getNumber());
            ps.setInt(3, car.getOwnerId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Car> getAllByOwnerId(int id) {
        try {
            ResultSet result = connection.createStatement().executeQuery(GET_ALL_QUERY_BY_OWNER_ID);
            Car car;
            List<Car> cars = new ArrayList<Car>();
            while (result.next()) {
                car = new Car(result.getString("number"), result.getInt("owner_id"));
                cars.add(car);
            }
            return cars;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
}
