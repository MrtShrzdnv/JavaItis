package ru.itis.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Marat_2 on 09.11.2016.
 */
@Controller
public class LogoutController {
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView getLogout(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("logout");
        return modelAndView;
    }
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ModelAndView postLogout(HttpServletRequest request, HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
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
        return modelAndView;
    }
}
