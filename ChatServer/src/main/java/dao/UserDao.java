package dao;

import model.Chat;
import model.User;

import java.util.List;

/**
 * Created by KFU-user on 24.11.2016.
 */
public interface UserDao {
    List<User> findAll();
    List<User> findAllByChatId(int id);
    User findById(int id);
    User findByName(String name);
    void add(User user);
    void addUserToChat(User user, Chat chat);
}
