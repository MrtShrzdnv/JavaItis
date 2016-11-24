package dao;

import model.Message;

import java.util.List;

/**
 * Created by KFU-user on 24.11.2016.
 */
public interface MessageDao {
    List<Message> findAll();
    List<Message> findByChatId(int id);
    List<Message> findByUserId(int id);
}
