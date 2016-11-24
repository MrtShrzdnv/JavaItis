package service;

import model.User;

import java.util.List;

/**
 * Created by Marat_2 on 24.11.2016.
 */
public interface UserService {
    User findById(int id);
    User findByName(String name);
    List<User> findAll();
    void add(User user);
}
