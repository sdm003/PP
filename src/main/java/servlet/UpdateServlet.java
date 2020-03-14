package servlet;

import model.User;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/update"})
public class UpdateServlet extends HttpServlet {
    UserServiceImpl service = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(req.getParameter("name")!= null && req.getParameter("age")!=null){
            long id = Long.parseLong(req.getParameter("id"));
            String name = req.getParameter("name");
            int age = Integer.parseInt(req.getParameter("age"));
            service.updateUser(new User(id,name, age));
        }
            resp.sendRedirect(req.getContextPath() + "/menu");

    }

}
