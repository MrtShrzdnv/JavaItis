package ru.itis;

import ru.itis.dao.UsersDao;
import ru.itis.dao.UsersDaoFileBasedImpl;
import ru.itis.models.User;
import ru.itis.service.SimpleUsersService;
import ru.itis.service.SimpleUsersServiceImpl;

public class Main {

    public static void main(String[] args) {
        UsersDao usersDao = SimpleEnterpriseMavenFactory.getInstance().getUsersDao();
        SimpleUsersService service = SimpleEnterpriseMavenFactory.getInstance().getSimpleUsersService();
        service.set(usersDao);
        User user = new User("Marat", "qwerty", 22, 0004);
        usersDao.save(user);
        //System.out.println(service.isRegistered("Marsel", "qwerty008"));
    }
}
