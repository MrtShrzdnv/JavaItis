package service;

import model.User;

import java.util.List;

/**
 * Created by Marat_2 on 24.11.2016.
 */
public interface UserService {
    User findById(Integer id);
    User findByName(String name);
    List<User> findAll();
    User findByToken(String token);
    List<User> findAllByChatId(Integer id);
    void save(User user);
    void update(User user);
    void updateToken(Integer userId, String token);
}
