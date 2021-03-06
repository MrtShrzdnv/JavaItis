package ru.itis.services;

import ru.itis.dao.UsersDao;
import ru.itis.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Marat_2 on 24.10.2016.
 */
@Service
public class UserServiceJdbcImpl implements UserService {
    @Autowired
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

    public User findById(int id) {
        return usersDao.findById(id);
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
