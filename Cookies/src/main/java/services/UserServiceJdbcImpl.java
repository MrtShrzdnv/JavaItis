package services;

import dao.UsersDao;
import models.User;

import java.util.List;

/**
 * Created by Marat_2 on 24.10.2016.
 */
public class UserServiceJdbcImpl implements UserService {
    private UsersDao usersDao;

    public UserServiceJdbcImpl(UsersDao usersDao) {

        this.usersDao = usersDao;
    }

    public void add(User user) {
        usersDao.add(user);
    }

    public void setToken(User user, String token) {
        user.setToken(token);
        usersDao.update(user);
    }

    public List<User> getAll() {
        return usersDao.getAll();
    }

    public User findByLogin(String login) {
        return usersDao.findByLogin(login);
    }

    public User findByToken(String token) {
        return usersDao.findByToken(token);
    }

    public boolean isRegistred(String login, String password) {
        return usersDao.isRegistred(login, password);
    }

    public boolean isLoginExist(String login) {
        return usersDao.isLoginExist(login);
    }

    public boolean isTokenExist(String token) {
        return usersDao.isTokenExist(token);
    }
}
