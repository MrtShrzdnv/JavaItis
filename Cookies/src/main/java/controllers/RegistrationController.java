package controllers;

import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.UserService;

import javax.servlet.Registration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by KFU-user on 09.11.2016.
 */
@Controller
public class RegistrationController {
    @Autowired
    UserService userService;
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView getRegistration(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registration");
        return modelAndView;
    }
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView postRegistration(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
            String name = request.getParameter("name");
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            if (!userService.isLoginExist(login)) {
                User user = new User(name, login, password);
                userService.add(user);
                modelAndView.setViewName("login");
                return modelAndView;
            }
            modelAndView.setViewName("registration");
            return modelAndView;
    }

}
