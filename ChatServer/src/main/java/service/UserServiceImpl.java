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
    public User findById(Integer id) {
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
    public List<User> findAllByChatId(Integer id) {
        List<Integer> usersId = userDao.findAllByChatId(id);
        List<User> users = null;
        for(Integer userId : usersId){
            users.add(findById(userId));
        }
        return users;
    }

    @Override
    public void save(User user) {
        userDao.save(user);
        userDao.saveToken(user.getId(), "");
    }

    @Override
    public void updateToken(Integer userId, String token) {
        userDao.updateToken(userId, token);
    }
}
