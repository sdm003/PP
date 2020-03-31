package filter;

import service.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/login")
public class LoginFilter implements javax.servlet.Filter{
    private UserServiceImpl service = UserServiceImpl.getInstance();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession(false);

        String password = servletRequest.getParameter("password");
        String name = servletRequest.getParameter("name");

        if(password != null && name != null){
            if (service.validateUser(name, password)) {
                String role = service.getUserRole(name);
                if (role.equals("user")) {
                    session.setAttribute("name", name);
                    session.setAttribute("role", role);
                    resp.sendRedirect(req.getContextPath() + "/user");
                    return;
                } else if (role.equals("admin")) {
                    session.setAttribute("name", name);
                    session.setAttribute("role", role);
                    resp.sendRedirect(req.getContextPath() + "/admin");
                    return;
                }
            }
        }

        filterChain.doFilter(req,resp);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
