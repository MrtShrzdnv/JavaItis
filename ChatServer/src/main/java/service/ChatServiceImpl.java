package service;

import dao.ChatDao;
import dao.MessageDao;
import model.Chat;
import model.Message;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by KFU-user on 24.11.2016.
 */
@Service
public class ChatServiceImpl implements ChatService{

    @Autowired
    private UserService userService;

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private ChatDao chatDao;

    @Override
    public Chat findById(Integer id) {
        Chat chat = chatDao.findById(id);
        List<Message> messagesOfChat = messageDao.findAllByChatId(id);
        List<User> usersOfChat = userService.findAllByChatId(id);
        chat.setMessages(messagesOfChat);
        chat.setUsersList(usersOfChat);
        return chat;
    }

    @Override
    public Chat findByName(String name) {
        Chat chat = chatDao.findByName(name);
        return findById(chat.getId());
    }

    @Override
    public List<Chat> findAll() {
        List<Chat> chats = chatDao.findAll();
        List<Chat> resultChat = null;
        for (Chat chat : chats){
            resultChat.add(findById(chat.getId()));
        }
        return resultChat;
    }

    @Override
    public void add(Chat chat) {
        chatDao.save(chat);
    }
}
