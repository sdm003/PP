package servlet;


import service.UserServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin/delete"})
public class DeleteServlet extends HttpServlet {
    UserServiceImpl service = UserServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(req.getParameter("id")!=null){
            int id = Integer.parseInt(req.getParameter("id"));
            service.deleteUser(id);
        }
        resp.sendRedirect(req.getContextPath() + "/admin");
    }
}
