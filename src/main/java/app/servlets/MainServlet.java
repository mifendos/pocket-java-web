package app.servlets;

import app.entities.Messages;
import app.entities.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static app.entities.User.me;


public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/messenger.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        req.setCharacterEncoding ("UTF-8");

        if (req.getParameter("sendMessage") != null) {

            String content = req.getParameter("msg");
            Messages.text.add(content);

        } else if (req.getParameter("addUser") != null) {

            String name = req.getParameter("addUser");
            if (!name.isEmpty()) {
                User user = new User(name);
                me.contacts.add(user);
            }
        } else {
            System.out.println("Something else happened");
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/messenger.jsp");
        requestDispatcher.forward(req, resp);

    }

}
