package service;

import dao.ChatDao;
import dao.MessageDao;
import dao.UserDao;
import model.Message;
import dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marat_2 on 23.11.2016.
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private ChatDao chatDao;

    private List<MessageDto> newMessages;

    public MessageServiceImpl() {
        newMessages = new ArrayList<MessageDto>();
    }

    public void hadleMessages(MessageDto newMessage){
        synchronized (newMessages){
            newMessages.add(newMessage);
            notifyAll();
        }
    }

    public List<MessageDto> getNewMessages() {
        return newMessages;
    }

    @Override
    public List<Message> findAll() {
        return messageDao.findAll();
    }

    @Override
    public List<Message> findAllByChatId(Integer id) {
        return messageDao.findAllByChatId(id);
    }

    @Override
    public List<Message> findAllByUserId(Integer id) {
        return messageDao.findAllByUserId(id);
    }

    @Override
    public void add(Message message) {
        messageDao.save(message);
    }
}
