package dao;

import model.Message;

import java.util.List;

/**
 * Created by KFU-user on 24.11.2016.
 */
public interface MessageDao {
    List<Message> findAll();
    List<Message> findAllByChatId(int id);
    List<Message> findAllByUserId(int id);
    void save(Message message);
}
