package dao;

import model.Message;

import java.util.List;

/**
 * Created by KFU-user on 24.11.2016.
 */
public interface MessageDao {
    List<Message> findAll();
    List<Message> findAllByChatId(Integer id);
    List<Message> findAllByUserId(Integer id);
    Integer findLastMessage(Integer userId, Integer chatId);
    void saveLastMessage(Integer userId, Integer chatId, Integer lastMessageId);
    void save(Message message);
    void delete(int id);
}
