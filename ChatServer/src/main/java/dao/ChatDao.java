package dao;

import model.Chat;
import model.ChatDto;

import java.util.List;

/**
 * Created by KFU-user on 24.11.2016.
 */
public interface ChatDao {
    List<Chat> findAll();
}
