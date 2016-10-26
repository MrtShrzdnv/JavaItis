package factories;

import dao.CarsDao;
import dao.UsersDao;
import services.CarService;
import services.UserService;
import java.lang.reflect.Constructor;
import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.util.Properties;

/**
 * Created by Marat_2 on 24.10.2016.
 */
public class ServiceFactory {
    private static ServiceFactory instance;

    private Properties properties;

    private UserService userService;
    private CarService carService;

    private ServiceFactory() {
        this.properties = new Properties();
        try {
            properties.load(new FileInputStream("C:\\Users\\KFU-user\\Desktop\\JavaItis\\Cookies\\src\\main\\resources\\Services.properties"));

            String serviceUserClassName = properties.getProperty("serviceUser.class");
            String serviceCarClassName = properties.getProperty("serviceCar.class");
            Class userServiceClass = Class.forName(serviceUserClassName);
            Class carServiceClass = Class.forName(serviceCarClassName);
            Constructor userConstructor = userServiceClass.getConstructor(UsersDao.class);
            Constructor carConstructor = carServiceClass.getConstructor(CarsDao.class);
            this.userService = (UserService)userConstructor.newInstance(DaoFactory.getInstance().getUsersDao());
            this.carService = (CarService)carConstructor.newInstance(DaoFactory.getInstance().getCarsDao());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static {
        instance = new ServiceFactory();
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return this.userService;
    }

    public CarService getCarService() {
        return carService;
    }
}
