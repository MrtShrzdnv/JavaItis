package service;

import model.Chat;
import model.User;

import java.util.List;

/**
 * Created by KFU-user on 24.11.2016.
 */
public interface ChatService {
    Chat findById(int id);
    Chat findByName(String name);
    List<Chat> findAll();
    void add(Chat chat);
}
