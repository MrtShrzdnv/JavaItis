package servlets;

import models.Car;
import models.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.CarService;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Marat_2 on 02.11.2016.
 */
public class JsonServlet extends HttpServlet {
    private class RestRequest {
        private Pattern regExAllPattern = Pattern.compile("/users");
        private Pattern regExIdPattern = Pattern.compile("/users/([0-9])+/cars");
        private Pattern regExNamePattern = Pattern.compile("^/users/(\\w+)$");

        private Integer id = null;
        private String name = null;

        public RestRequest(String pathInfo) throws ServletException {
            Matcher matcher;

            matcher = regExIdPattern.matcher(pathInfo);
            if (matcher.find()) {
                id = Integer.parseInt(matcher.group(1));
                return;
            }
            matcher = regExNamePattern.matcher(pathInfo);
            if (matcher.find()) {
                name = matcher.group(1);
                return;
            }
            matcher = regExAllPattern.matcher(pathInfo);
            if (matcher.find()) return;

            throw new ServletException("Invalid URI");
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    private CarService carService;
    private UserService userService;
    private RestRequest restRequest;
    public void init() throws ServletException {
        super.init();
        //carService = ServiceFactory.getInstance().getCarService();
        //userService = ServiceFactory.getInstance().getUserService();
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:context.xml");
        carService = (CarService)context.getBean("carService");
        userService = (UserService)context.getBean("userService");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrintWriter out = response.getWriter();
            String path = request.getServletPath();
            restRequest = new RestRequest(path);
            String name = restRequest.getName();
            Integer id = restRequest.getId();
            response.setContentType("application/json");
            if (name != null){
                User user = userService.findByLogin(name);
                out.write(user.toString());
            }else if (id != null){
                List<Car> cars = carService.getAllByOwnerId(id);
                for(Car car : cars)
                    out.write(car.toString());
            }else{
                List<User> users = userService.getAll();
                for(User user : users)
                    out.write(user.toString());
            }
            //getServletContext().getRequestDispatcher("/addAuto.jsp").forward(request, response);
        } catch (ServletException e) {
            throw new IllegalArgumentException(e);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
