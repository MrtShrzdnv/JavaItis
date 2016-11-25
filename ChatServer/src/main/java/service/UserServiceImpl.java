package service;

import dao.ChatDao;
import dao.MessageDao;
import dao.UserDao;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Marat_2 on 24.11.2016.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private ChatDao chatDao;
    @Override
    public User findById(int id) {
        User user = userDao.findById(id);
        return user;
    }

    @Override
    public User findByName(String name) {
        User user = userDao.findByName(name);
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users = userDao.findAll();
        return users;
    }

    @Override
    public void add(User user) {
        userDao.save(user);
    }
}
