package ru.itis;

import ru.itis.factories.ServiceFactory;
import ru.itis.models.Owner;
import ru.itis.services.OwnerService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Servlet extends HttpServlet {
    private OwnerService usersService;
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response){
        try{
        response.setContentType("text/html");
        //String message = request.getParameterValues("message")[0];
        //PrintWriter out = null;
        //out = response.getWriter();
        usersService = ServiceFactory.getInstance().getOwnerService();
        List<Owner> list = usersService.getAll();
        request.setAttribute("myUsers", list);
        getServletContext().getRequestDispatcher("/user_form.jsp").forward(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String residence = req.getParameter("city");
        String age = req.getParameter("age");
        Owner owner = new Owner(name, Integer.parseInt(age), residence);
        usersService.addOwner(owner);
        //Cookie cookie = new Cookie("LastAddedOwnersName", name);
        //resp.addCookie(cookie);
        List<Owner> users = usersService.getAll();
        if (users != null) {
            req.setAttribute("myUsers", users);
            getServletContext().getRequestDispatcher("/user_form.jsp").forward(req, resp);
        }
    }
}
