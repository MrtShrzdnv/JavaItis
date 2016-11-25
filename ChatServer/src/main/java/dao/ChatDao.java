package dao;

import model.Chat;

import java.util.List;

/**
 * Created by KFU-user on 24.11.2016.
 */
public interface ChatDao {
    List<Chat> findAll();
    Chat findById(int id);
    Chat findByName(String name);
    void save(Chat chat);
}
