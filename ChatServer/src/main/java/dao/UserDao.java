package dao;

import model.User;

import java.util.List;

/**
 * Created by KFU-user on 24.11.2016.
 */
public interface UserDao {
    List<User> findAll();
    List<User> findById(int id);
    List<User> findByName(String name);
}
