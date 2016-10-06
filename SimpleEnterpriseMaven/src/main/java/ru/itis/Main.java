package ru.itis;

import ru.itis.dao.UsersDao;
import ru.itis.dao.UsersDaoFileBasedImpl;
import ru.itis.models.User;
import ru.itis.service.SimpleUsersService;
import ru.itis.service.SimpleUsersServiceImpl;

public class Main {

    public static void main(String[] args) {
        UsersDao usersDao = new UsersDaoFileBasedImpl("C:\\Users\\Marat_2\\Desktop\\JavaItis\\SimpleEnterpriseMaven\\users.txt");
        SimpleUsersService service = new SimpleUsersServiceImpl(usersDao);
        User user = new User("Marat", "qwerty", 22, 0004);
        usersDao.save(user);
        //System.out.println(service.isRegistered("Marsel", "qwerty008"));
    }
}
