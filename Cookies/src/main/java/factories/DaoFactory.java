package factories;

import dao.CarsDao;
import dao.UsersDao;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.util.Properties;

/**
 * Created by Marat_2 on 24.10.2016.
 */
public class DaoFactory {
    private Properties properties;
    private DaoFactory daoFactory;
    private CarsDao carsDao;

    public CarsDao getCarsDao() {
        return carsDao;
    }

    public UsersDao getUsersDao() {
        return usersDao;
    }

    private UsersDao usersDao;

    private static DaoFactory instance = new DaoFactory();

    public static DaoFactory getInstance() {
        return instance;
    }

    private DaoFactory() {
        properties = new Properties();
        try{
            properties.load(new FileInputStream("C:\\Users\\KFU-user\\Desktop\\JavaItis\\Cookies\\src\\main\\resources\\Dao.properties"));
            String daoUserClassName = properties.getProperty("daoUser.class");
            String daoCarClassName = properties.getProperty("daoCar.class");
            Class userClass = Class.forName(daoUserClassName);
            Class carClass = Class.forName(daoCarClassName);
            Constructor userConstructor = userClass.getConstructor(Connection.class);
            Constructor carConstructor = carClass.getConstructor(Connection.class);
            usersDao = (UsersDao)userConstructor.newInstance(ConnectionFactory.getInstance().getConnection());
            carsDao = (CarsDao)carConstructor.newInstance(ConnectionFactory.getInstance().getConnection());
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
