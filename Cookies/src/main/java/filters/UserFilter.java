package filters;

import com.sun.deploy.net.HttpResponse;
import factories.ServiceFactory;
import services.UserService;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Marat_2 on 25.10.2016.
 */
public class UserFilter implements javax.servlet.Filter {
    UserService userService;
    public void init(FilterConfig filterConfig) throws ServletException {
        userService = ServiceFactory.getInstance().getUserService();
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String path = ((HttpServletRequest)servletRequest).getRequestURI();
        Cookie[] cookies = ((HttpServletRequest)servletRequest).getCookies();
        boolean check = false;
        if (cookies != null){
            for(Cookie cookie : cookies){
                if (cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    if(userService.findByToken(token) != null)
                        check = true;
                    return;
                }
            }
        }
        if(!check && ((path.equals("/list")) ||(path.equals("addAuto")))){
            ((HttpServletResponse)servletResponse).sendRedirect("/registration");
        }
    }

    public void destroy() {

    }
}
