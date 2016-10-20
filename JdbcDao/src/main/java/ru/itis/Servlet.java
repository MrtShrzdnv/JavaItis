package ru.itis;

import ru.itis.factories.ServiceFactory;
import ru.itis.models.Owner;
import ru.itis.services.OwnerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by KFU-user on 20.10.2016.
 */
public class Servlet extends HttpServlet {
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response){
        try{
        response.setContentType("text/html");
        //String message = request.getParameterValues("message")[0];
        //PrintWriter out = null;
        //out = response.getWriter();
        OwnerService ownerService = ServiceFactory.getInstance().getOwnerService();
        List<Owner> list = ownerService.getAll();

        request.setAttribute("myUsers", list);
        getServletContext().getRequestDispatcher("/users.jsp").forward(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
