package ru.itis.controllers;

import ru.itis.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.services.CarService;
import ru.itis.services.UserService;

import java.util.List;

/**
 * Created by Marat_2 on 09.11.2016.
 */
@Controller
public class UsersListController {
    @Autowired
    UserService userService;
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView getUsersList(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("usersList");
        List<User> users = userService.getAll();
        modelAndView.addObject("MyUsers", users);
        return modelAndView;
    }

}
