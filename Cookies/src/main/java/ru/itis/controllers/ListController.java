package ru.itis.controllers;

import ru.itis.models.Car;
import ru.itis.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.services.CarService;
import ru.itis.services.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Marat_2 on 09.11.2016.
 */
@Controller
public class ListController {
    @Autowired
    UserService userService;
    @Autowired
    CarService carService;
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView getList(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("list");
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userService.findByToken(token);
                    List<Car> cars = carService.getAllByOwnerId(user.getId());
                    user.setCars(cars);
                    modelAndView.addObject("MyUser", cars);
                }
            }
        }
        return modelAndView;
    }
}
