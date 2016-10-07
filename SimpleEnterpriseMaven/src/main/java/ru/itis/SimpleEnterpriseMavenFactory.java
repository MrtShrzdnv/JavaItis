package ru.itis;

import ru.itis.dao.UsersDao;
import ru.itis.service.SimpleUsersService;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by KFU-user on 07.10.2016.
 */
public class SimpleEnterpriseMavenFactory {
    private static SimpleEnterpriseMavenFactory instance;
    private Properties properties;
    private UsersDao usersDao;
    private SimpleUsersService simpleUsersService;

    public static SimpleEnterpriseMavenFactory getInstance() {
        return instance;
    }

    public UsersDao getUsersDao() {
        return usersDao;
    }

    public SimpleUsersService getSimpleUsersService() {
        return simpleUsersService;
    }

    private SimpleEnterpriseMavenFactory(){
        try {
            properties = new Properties();
            properties.load(new FileInputStream("C:\\Users\\KFU-user\\Desktop\\JavaItis\\SimpleEnterpriseMaven\\src\\main\\java\\ru\\itis\\resources\\SimpleInterprise"));
            String dao = properties.getProperty("dao.class");
            String usersService = properties.getProperty("usersService.class");
            this.usersDao = (UsersDao)Class.forName(dao).newInstance();
            this.simpleUsersService = (SimpleUsersService)Class.forName(usersService).newInstance();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        } catch (InstantiationException e) {
            throw new IllegalArgumentException(e);

        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }
    static{
        instance = new SimpleEnterpriseMavenFactory();
    }
}
