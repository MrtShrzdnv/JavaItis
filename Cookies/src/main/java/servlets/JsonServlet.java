package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Marat_2 on 02.11.2016.
 */
public class JsonServlet extends HttpServlet {
    private class RestRequest {
        private Pattern regExAllPattern = Pattern.compile("/users");
        private Pattern regExIdPattern = Pattern.compile("/users/([0-9]+/cars)");
        private Pattern regExNamePattern = Pattern.compile("/users/\\w+");

        private Integer id;
        private String name;

        public RestRequest(String pathInfo) throws ServletException {
            Matcher matcher;

            matcher = regExIdPattern.matcher(pathInfo);
            if (matcher.find()) {
                id = Integer.parseInt(matcher.group(1));
                return;
            }
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
