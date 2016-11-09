package controllers;

import models.Car;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.CarService;
import services.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Marat_2 on 09.11.2016.
 */
@Controller
public class AddAutoController {
    @Autowired
    UserService userService;
    @Autowired
    CarService carService;
    @RequestMapping(value = "/addAuto", method = RequestMethod.GET)
    public ModelAndView getAddAuto(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addAuto");
        return modelAndView;
    }
    @RequestMapping(value = "/addAuto", method = RequestMethod.POST)
    public ModelAndView postAddAuto(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addAuto");
        String number = request.getParameter("number");
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    if (!token.equals(null)) {
                        User user = userService.findByToken(token);
                        carService.add(new Car(number, user.getId()));
                    }
                    break;
                }
            }
        }
        return modelAndView;
    }
}
