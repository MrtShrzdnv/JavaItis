package dao;

import model.Chat;
import model.User;

import java.util.List;

/**
 * Created by KFU-user on 24.11.2016.
 */
public interface UserDao {
    List<User> findAll();
    List<Integer> findAllByChatId(Integer id);
    User findById(Integer id);
    User findByName(String name);
    void save(User user);
    void update(User user);
    void saveToken(Integer userId, String token);
    void updateToken(Integer userId, String token);
    void delete(int id);
    void saveUserToChat(User user, Chat chat);
    boolean isRegistred(String login, String hash_password);
    boolean isLoginExist(String login);
    Integer findUserIdByToken(String token);
}
