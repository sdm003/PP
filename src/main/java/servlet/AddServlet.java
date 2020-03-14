package servlet;

import model.User;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/add"})
public class AddServlet extends HttpServlet {

    private UserServiceImpl service = UserServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(req.getParameter("name")!= null && req.getParameter("age")!=null){
            String name = req.getParameter("name");
            int age = Integer.parseInt(req.getParameter("age"));
            User user = new User(name, age);
            service.addUser(user);
        }
        resp.sendRedirect(req.getContextPath() + "/menu");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/add.jsp").forward(req, resp);
    }
}
