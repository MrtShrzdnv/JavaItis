package service;

import model.MessageDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marat_2 on 23.11.2016.
 */
@Service
public class MessagesService {
    private List<MessageDto> newMessages;

    public MessagesService() {
        newMessages = new ArrayList<MessageDto>();
    }

    public void hadleMessages(MessageDto newMessage){
        synchronized (newMessages){
            newMessages.add(newMessage);
            notify();
        }
    }

    public List<MessageDto> getNewMessages() {
        return newMessages;
    }
}
