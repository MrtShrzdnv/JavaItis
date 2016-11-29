package service;

import model.Chat;

import java.util.List;

/**
 * Created by KFU-user on 24.11.2016.
 */
public interface ChatService {
    Chat findById(Integer id);
    Chat findByName(String name);
    List<Chat> findAll();
    void save(Chat chat);
}
