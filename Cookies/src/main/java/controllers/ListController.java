package controllers;

import models.Car;
import models.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import services.CarService;
import services.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by KFU-user on 09.11.2016.
 */
public class ListController implements Controller {
    UserService userService;
    CarService carService;
    ApplicationContext context;

    public ListController(){
        context = new ClassPathXmlApplicationContext("classpath:context.xml");
        userService = (UserService)context.getBean("userService");
        carService = (CarService)context.getBean("carService");
    }
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userService.findByToken(token);
                    List<Car> cars = carService.getAllByOwnerId(user.getId());
                    user.setCars(cars);
                    //request.setAttribute("MyUser", user.getCars());
                    modelAndView.setViewName("list");
                    modelAndView.addObject("MyUser", cars);
                }
            }
        }
        return modelAndView;
    }
}
