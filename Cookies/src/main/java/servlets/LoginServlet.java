package servlets;

import factories.ServiceFactory;
import models.User;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by Marat_2 on 25.10.2016.
 */
public class LoginServlet extends HttpServlet {
    private UserService userService;
    @Override
    public void init() throws ServletException {
        super.init();
        userService = ServiceFactory.getInstance().getUserService();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        //try {
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            if (userService.isRegistred(login, password)) {
                User user = userService.findByLogin(login);
                String token = new BigInteger(130, new SecureRandom()).toString(32);
                Cookie cookie = new Cookie("token", token);
                userService.setToken(user, token);
            } else {
                doGet(request, response);
            }/*
        }catch (ServletException e) {
            throw new IllegalArgumentException(e);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        */
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        try {
            response.setContentType("text/html; charset=UTF-8");
            getServletContext().getRequestDispatcher("/login.jsp").forward(request,response);
        } catch (ServletException e) {
            throw new IllegalArgumentException(e);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
