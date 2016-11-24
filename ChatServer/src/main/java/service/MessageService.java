package service;

import model.MessageDto;

import java.util.List;

/**
 * Created by KFU-user on 24.11.2016.
 */
public interface MessageService {
    void hadleMessages(MessageDto messageDto);
    List<MessageDto> getNewMessages();
}
