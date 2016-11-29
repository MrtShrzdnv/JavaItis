package service;

import model.Message;
import dto.MessageDto;

import java.util.List;

/**
 * Created by KFU-user on 24.11.2016.
 */
public interface MessageService {
    void hadleMessages(MessageDto messageDto);
    List<MessageDto> getNewMessages();
    List<Message> findAll();
    List<Message> findAllByChatId(Integer id);
    List<Message> findAllByUserId(Integer id);
    void add(Message message);
}
