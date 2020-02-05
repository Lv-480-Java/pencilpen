package servlet;


import domain.logic.AuthenticationController;

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
        String passwordAtribute = (String) session.getAttribute("password");
        if (passwordAtribute == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("penpencil/login.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("/gallery");
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        AuthenticationController authenticationController = new AuthenticationController();
        HttpSession session = request.getSession();
        String passwordAtribute = (String) session.getAttribute("password");
        if (passwordAtribute == null) {
            if (authenticationController.login(username, password)) {
                session.setAttribute("password", password);
                session.setAttribute("username", username);

                response.sendRedirect("/gallery");
                System.out.println(" ITS OKAY");

            } else {
                request.setAttribute("text-result", "Error! Login or password is not right");
                RequestDispatcher dispatcher = request.getRequestDispatcher("penpencil/login.jsp");
                System.out.println("ERROR");
                dispatcher.forward(request, response);
            }
        }
    }
}