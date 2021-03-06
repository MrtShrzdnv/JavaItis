package ru.itis.factories;

import ru.itis.dao.OwnersDao;
import ru.itis.services.OwnerService;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.util.Properties;

/**
 * Created by Marat_2 on 13.10.2016.
 */
public class ServiceFactory {
    private static ServiceFactory instance;

    private Properties properties;

    private OwnerService ownerService;

    private ServiceFactory() {
        this.properties = new Properties();
        try {
            properties.load(new FileInputStream("C:\\Users\\Marat_2\\Desktop\\JavaItis\\JdbcDao\\src\\main\\resources\\Service.properties"));

            String serviceClassName = properties.getProperty("service.class");

            Class ownerServiceClass = Class.forName(serviceClassName);

            Constructor constructor = ownerServiceClass.getConstructor(OwnersDao.class);

            this.ownerService = (OwnerService)constructor.newInstance(DaoFactory.getInstance().getOwnersDao());
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

    public OwnerService getOwnerService() {
        return this.ownerService;
    }
}
