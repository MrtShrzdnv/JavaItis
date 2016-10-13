package dao;

import models.Car;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 * Created by KFU-user on 12.10.2016.
 */
public class CarsDaoJdbcImpl implements CarsDao {
    private Connection connection;
    private static final String FIND_QUERY = "SELECT * FROM cars WHERE car_id = ";
    private static final String GET_ALL_QUERY = "SELECT * FROM cars";
    private static final String DELETE_QUERY = "DELETE FROM cars WHERE car_id = ?";
    private static final String UPDATE_QUERY = "UPDATE cars SET mileage = ? WHERE car_id = ?";
    private static final String ADD_QUERY = "INSERT INTO cars (car_id, mileage) VALUES (?, ?)";

    public CarsDaoJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    public Car find(int id) {
        try {
            ResultSet result = connection.createStatement().executeQuery(FIND_QUERY + id);
            Car car;
            if (result.next()) {
                car = new Car(result.getInt("car_id"), result.getInt("mileage"));
                return car;
            }
            else
                return null;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Car> getAll() {
        try {
            ResultSet result = connection.createStatement().executeQuery(GET_ALL_QUERY);
            Car car;
            List<Car> cars = new ArrayList<Car>();
            while (result.next()) {
                car = new Car(result.getInt("car_id"), result.getInt("mileage"));
                cars.add(car);
            }
            return cars;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void update(Car car) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
            preparedStatement.setInt(1, car.getId());
            preparedStatement.setInt(2, car.getMileage());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void add(Car car) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_QUERY);
            preparedStatement.setInt(1, car.getMileage());
            preparedStatement.setInt(2, car.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
