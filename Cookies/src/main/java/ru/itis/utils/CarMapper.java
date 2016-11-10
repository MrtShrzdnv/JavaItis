package ru.itis.utils;

import ru.itis.models.Car;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Marat_2 on 02.11.2016.
 */
public class CarMapper implements RowMapper {
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Car car = new Car();
        car.setId(resultSet.getInt("car_id"));
        car.setNumber(resultSet.getString("number"));
        car.setOwnerId(resultSet.getInt("owner_id"));
        return car;
    }
}
