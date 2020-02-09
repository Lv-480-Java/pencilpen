package servlet;


import domain.service.Authentication;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String passwordAttribute = (String) session.getAttribute("password");

        if (passwordAttribute == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("penpencil/login.jsp");
            dispatcher.forward(request, response);
        } else {
            session.invalidate();
            response.sendRedirect("/login");
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String passwordAtribute = (String) session.getAttribute("password");
        Authentication authentication = new Authentication();

        if (passwordAtribute == null) {
            session = request.getSession(true);
            if (authentication.validate(username, password)) {

                session.setAttribute("password", password);
                session.setAttribute("username", username);

                response.sendRedirect("/profile");
                System.out.println(" ITS OKAY");

            } else {
                request.setAttribute("text-result", "Error! Login or password is not right");

                RequestDispatcher dispatcher = request.getRequestDispatcher("penpencil/login.jsp");
                dispatcher.forward(request, response);
            }
        }
    }
}