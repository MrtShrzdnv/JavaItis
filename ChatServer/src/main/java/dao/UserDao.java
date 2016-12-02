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
    User findByLoginAndPassword(String login, String hash_password);
    void save(User user);
    void update(User user);
    void saveToken(Integer userId, String token);
    void updateToken(Integer userId, String token);
    void saveLastMessage(Integer userId, Integer chatId);
    void updateLastMessage(Integer userId, Integer chatId, Integer messageId);
    void delete(int id);
    void saveUserToChat(Integer userId, Integer chatId);
    boolean isRegistred(String login, String hash_password);
    boolean isLoginExist(String login);
    boolean isTokenExist(String token);
    Integer findUserIdByToken(String token);
    Integer findLastMessageId(Integer userId, Integer chatId);
}
