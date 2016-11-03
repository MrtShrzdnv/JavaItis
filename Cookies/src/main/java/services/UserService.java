package services;

import models.User;

import java.util.List;

/**
 * Created by Marat_2 on 24.10.2016.
 */
public interface UserService {
    void add(User user);
    void setToken(User user, String token);
    List<User> getAll();
    User findByLogin(String login);
    User findByToken(String token);
    User findById(int id);
    boolean isRegistred(String login, String password);
    boolean isLoginExist(String login);
    boolean isTokenExist(String token);
}
