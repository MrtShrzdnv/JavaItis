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
    User findByLoginAndPassword(String login, String password);
    List<User> findAllByChatId(Integer id);
    List<Integer> findAllUsersIdByChatId(Integer id);
    Integer findLastMessageId(Integer userId, Integer chatId);
    void updateLastMessageId(Integer userId, Integer chatId, Integer messageId);
    void save(User user);
    void saveUserToChat(Integer userId, Integer chatId);
    void update(User user);
    void updateToken(Integer userId, String token);
}
