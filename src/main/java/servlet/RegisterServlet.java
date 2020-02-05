package servlet;

import domain.logic.AuthenticationController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("penpencil/register.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AuthenticationController authenticationController = new AuthenticationController();
        authenticationController.register(request);
        RequestDispatcher dispatcher = request.getRequestDispatcher("penpencil/login.jsp");
        request.setAttribute("text-result", "Success! Now you can login with your data");
        dispatcher.forward(request, response);
    }
}
