package ru.itis.dao;

import ru.itis.models.Car;

import java.util.List;

/**
 * Created by Marat_2 on 24.10.2016.
 */
public interface CarsDao {
    void add(Car car);
    List<Car> getAllByOwnerId(int id);
}
