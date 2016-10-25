package servlets;

import factories.ServiceFactory;
import models.User;
import services.CarService;
import services.UserService;

import javax.servlet.ServletException;
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
        userService = ServiceFactory.getInstance().getUserService();
        carService = ServiceFactory.getInstance().getCarService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("text/html; charset=UTF-8");
            List<User> users = userService.getAll();
            for (User user : users) {
                user.setCars(carService.getAllByOwnerId(user.getId()));
            }
            request.setAttribute("MyUsers", users);
            getServletContext().getRequestDispatcher("/list.jsp").forward(request, response);
        }catch (ServletException e) {
            throw new IllegalArgumentException(e);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

    }
}
