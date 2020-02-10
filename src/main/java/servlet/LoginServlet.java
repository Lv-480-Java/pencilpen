package servlet;


import domain.entity.User;
import domain.entity.UserRegistered;
import domain.service.AuthenticationService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static domain.service.AuthenticationService.validate;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        UserRegistered user = (UserRegistered)session.getAttribute("user");

        if (!validate(user)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("penpencil/login.jsp");
            dispatcher.forward(request, response);
        } else {
            session.invalidate();
            response.sendRedirect("/login");
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();
        if (!validate((UserRegistered) session.getAttribute("user"))) {
            UserRegistered user = new UserRegistered(username, password);

            if (validate(user)) {
                session.setAttribute("user", user);
                response.sendRedirect("/profile?username=" + username);
                System.out.println(" ITS OKAY");

            } else {
                request.setAttribute("text-result", "Error! Login or password is not right");
                RequestDispatcher dispatcher = request.getRequestDispatcher("penpencil/login.jsp");
                dispatcher.forward(request, response);
            }
        }
    }
}