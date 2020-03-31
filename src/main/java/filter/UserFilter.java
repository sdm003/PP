package filter;

import service.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.Filter;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/user/*")
public class UserFilter implements Filter {
    private UserServiceImpl service = UserServiceImpl.getInstance();

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession();

        String name = (String) session.getAttribute("name");
        String role = (String) session.getAttribute("role");

        if (name != null && role != null){
            if(!role.equals("user")){
                resp.sendRedirect(req.getContextPath() + "/login");
                return;
            }
        }
            filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
    }
}
