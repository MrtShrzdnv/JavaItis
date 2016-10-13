package factories;

import dao.CarsDao;
import dao.OwnersDao;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.util.Properties;

/**
 * Created by KFU-user on 13.10.2016.
 */
public class DaoFactory {
    private Properties properties;
    private static DaoFactory instance;
    private DaoFactory daofactory;
    private OwnersDao ownersDao;
    private CarsDao carsDao;

    private DaoFactory(){
        properties = new Properties();
        try{
            properties.load(new FileInputStream("C:\\Users\\KFU-user\\Desktop\\JavaItis\\JdbcDao\\src\\main\\resources\\dao.properties"));
            String daoOwnerClassName = properties.getProperty("daoOwner.class");
            String daoCarClassName = properties.getProperty("daoCar.class");
            Class ownerClass = Class.forName(daoOwnerClassName);
            Class carClass = Class.forName(daoCarClassName);
            Constructor ownerConstructor = ownerClass.getConstructor(Connection.class);
            Constructor carConstructor = carClass.getConstructor(Connection.class);
            ownersDao = (OwnersDao)ownerConstructor.newInstance(JdbcConnection.getInstance().getConnection());
            carsDao = (CarsDao)carConstructor.newInstance(JdbcConnection.getInstance().getConnection());

        }catch (Exception e){
            System.out.println(e);
        }
    }
    static{
        instance = new DaoFactory();
    }
    public static DaoFactory getInstance(){
        return instance;
    }
    public OwnersDao getOwnersDao(){
        return this.ownersDao;
    }
    public CarsDao getCarsDao(){
        return this.carsDao;
    }
}
