package ru.itis.servlets;

import ru.itis.factories.ServiceFactory;
import ru.itis.models.Car;
import ru.itis.models.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.itis.services.CarService;
import ru.itis.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Marat_2 on 25.10.2016.
 */
public class ListServlet extends HttpServlet {
    private UserService userService;
    private CarService carService;
    @Override
    public void init() throws ServletException {
        super.init();
        //userService = ServiceFactory.getInstance().getUserService();
        //carService = ServiceFactory.getInstance().getCarService();
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:context.xml");
        carService = (CarService)context.getBean("carService");
        userService = (UserService)context.getBean("userService");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("text/html; charset=UTF-8");
            Cookie[] cookies = request.getCookies();
            if(cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("token")) {
                        String token = cookie.getValue();
                        User user = userService.findByToken(token);
                        List<Car> cars = carService.getAllByOwnerId(user.getId());
                        user.setCars(cars);
                        request.setAttribute("MyUser", user.getCars());
                    }
                }
            }
            getServletContext().getRequestDispatcher("/list.jsp").forward(request, response);
        }catch (ServletException e) {
            throw new IllegalArgumentException(e);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

    }
}
