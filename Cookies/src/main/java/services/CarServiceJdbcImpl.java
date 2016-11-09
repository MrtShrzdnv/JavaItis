package services;

import dao.CarsDao;
import models.Car;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Marat_2 on 24.10.2016.
 */
@Service
public class CarServiceJdbcImpl implements CarService {
    private CarsDao carsDao;

    public CarServiceJdbcImpl(CarsDao carsDao) {
        this.carsDao = carsDao;
    }

    public void add(Car car) {
        carsDao.add(car);
    }

    public List<Car> getAllByOwnerId(int id) {
        return carsDao.getAllByOwnerId(id);
    }
}
