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
        userService = ServiceFactory.getInstance().getUserService();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  {
        try {
            String name = request.getParameter("name");
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            if (!userService.isLoginExist(login)) {
                User user = new User(name, login, password);
                //String token = new BigInteger(130, new SecureRandom()).toString(32);
                //Cookie cookie = new Cookie("token", token);
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
