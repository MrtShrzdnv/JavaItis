package ru.itis.controllers;

import ru.itis.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.services.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by Marat_2 on 09.11.2016.
 */
@Controller
public class LoginController {
    @Autowired
    UserService userService;
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getLogin(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView postLogin(HttpServletRequest request, HttpServletResponse response){
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        ModelAndView modelAndView = new ModelAndView();
        if (userService.isRegistred(login, password)) {
            User user = userService.findByLogin(login);
            String token = new BigInteger(130, new SecureRandom()).toString(32);
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for(Cookie cookie : cookies){
                    if (cookie.getName().equals("token")){
                        Cookie deleteCookie = new Cookie("token", null);
                        deleteCookie.setMaxAge(0);
                        deleteCookie.setPath("/");
                        response.addCookie(deleteCookie);
                        break;
                    }
                }
            }
            response.addCookie(new Cookie("token", token));
            userService.setToken(user, token);
            modelAndView.setViewName("list");
            return modelAndView;
        }
        modelAndView.setViewName("login");
        return modelAndView;
    }
}
