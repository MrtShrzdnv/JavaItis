package dao;

import model.Chat;
import model.ChatDto;
import model.User;

import java.util.List;

/**
 * Created by KFU-user on 24.11.2016.
 */
public interface ChatDao {
    List<Chat> findAll();
    Chat findById(int id);
    Chat findByName(String name);
    void add(Chat chat);
}
