package service;

import model.Message;
import model.MessageDto;

import java.util.List;

/**
 * Created by KFU-user on 24.11.2016.
 */
public interface MessageService {
    void hadleMessages(MessageDto messageDto);
    List<MessageDto> getNewMessages();
    List<Message> findAll();
    List<Message> findAllByChatId(int id);
    List<Message> findAllByUserId(int id);
    void add(Message message);
}
