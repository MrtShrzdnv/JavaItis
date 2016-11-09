package controllers;

import models.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import services.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by KFU-user on 09.11.2016.
 */

public class LoginController implements Controller {
    UserService userService;
    ApplicationContext context;

    public LoginController(){
        context = new ClassPathXmlApplicationContext("classpath:context.xml");
        userService = (UserService)context.getBean("userService");
    }
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        if (request.getMethod().equals("POST")){
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            if (userService.isRegistred(login, password)) {
                User user = userService.findByLogin(login);
                String token = new BigInteger(130, new SecureRandom()).toString(32);
                //Cookie cookie = new Cookie("token", token);
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
                response.sendRedirect("/list");

            }
        }
        return modelAndView;
    }
}
