package filters;

//import com.sun.deploy.net.HttpResponse;
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
    private UserService userService;
    private boolean check;
    private FilterConfig filterConfig = null;
    public void init(FilterConfig filterConfig) throws ServletException {
        userService = ServiceFactory.getInstance().getUserService();
        check = false;
        this.filterConfig = filterConfig;
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String uri = ((HttpServletRequest)servletRequest).getRequestURI();
        Cookie[] cookies = ((HttpServletRequest)servletRequest).getCookies();
        if (cookies != null){
            for(Cookie cookie : cookies){
                if (cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    if(userService.isTokenExist(token))
                        check = true;
                    break;
                }
            }
        }
        if((!check) && ((uri.equals("/list")) ||(uri.equals("/addAuto")))){
            ((HttpServletResponse)servletResponse).sendRedirect("/registration");
        }else
            filterChain.doFilter(servletRequest,servletResponse);
    }

    public void destroy() {

    }
}
