package servlets;

import factories.ServiceFactory;
import models.Car;
import models.User;
import services.CarService;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Marat_2 on 25.10.2016.
 */
public class AddAutoServlet extends HttpServlet {
    private CarService carService;
    private UserService userService;
    @Override
    public void init() throws ServletException {
        super.init();
        carService = ServiceFactory.getInstance().getCarService();
        userService = ServiceFactory.getInstance().getUserService();

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        try{
            String number = request.getParameter("number");
            Cookie[] cookies = request.getCookies();
            if(cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("token")) {
                        String token = cookie.getValue();
                        User user = userService.findByToken(token);
                        carService.add(new Car(number, user.getId()));
                        getServletContext().getRequestDispatcher("/addAuto.jsp").forward(request, response);
                    }
                }
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        try {
            response.setContentType("text/html; charset=UTF-8");
            getServletContext().getRequestDispatcher("/addAuto.jsp").forward(request, response);
        } catch (ServletException e) {
            throw new IllegalArgumentException(e);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
