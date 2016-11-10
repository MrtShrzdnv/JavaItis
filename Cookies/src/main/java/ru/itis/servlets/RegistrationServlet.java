package ru.itis.servlets;

import ru.itis.factories.ServiceFactory;
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
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by Marat_2 on 24.10.2016.
 */
public class RegistrationServlet extends HttpServlet {
    private UserService userService;
    @Override
    public void init() throws ServletException {
        super.init();
        //userService = ServiceFactory.getInstance().getUserService();
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:context.xml");
        //carService = (CarService)context.getBean("carService");
        userService = (UserService)context.getBean("userService");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  {
        try {
            String name = request.getParameter("name");
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            if (!userService.isLoginExist(login)) {
                User user = new User(name, login, password);
                userService.add(user);
                response.sendRedirect("/login");
            } else {
                doGet(request, response);
            }
        }catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)  {
        try {
            response.setContentType("text/html; charset=UTF-8");
            getServletContext().getRequestDispatcher("/registration.jsp").forward(request,response);
        } catch (ServletException e) {
            throw new IllegalArgumentException(e);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
