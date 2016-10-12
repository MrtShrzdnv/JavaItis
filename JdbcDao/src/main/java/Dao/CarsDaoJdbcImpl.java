package Dao;

import models.Car;

import java.util.List;

/**
 * Created by KFU-user on 12.10.2016.
 */
public class CarsDaoJdbcImpl implements CarsDao {
    List<Car> listOfCar;

    public Car find(int id) {
        for(Car car : listOfCar) {
            if (car.getId() == id)
                return car;
        }
        return null;
    }

    public List<Car> getAll() {
        return null;
    }

    public void delete(int id) {
    }

    public void update(Car car) {

    }

    public void add(Car car) {

    }
}
