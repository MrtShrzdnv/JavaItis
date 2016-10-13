package dao;

import models.Car;

import java.util.List;

/**
 * Created by KFU-user on 12.10.2016.
 */
public interface CarsDao {
    Car find(int id);
    List<Car> getAll();
    void delete(int id);
    void update(Car car);
    void add(Car car);
}
