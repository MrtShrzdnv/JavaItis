package dao;

import models.User;

import java.util.List;

/**
 * Created by Marat_2 on 24.10.2016.
 */
public interface UsersDao {
    void add(User user);
    void update(User user);
    List<User> getAll();
    User findByLogin(String login);
    User findByToken(String token);
    boolean isRegistred(String login, String password);
    boolean isLoginExist(String login);
}
