package servlet;

import domain.entity.User;
import domain.service.Authentication;

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
        Authentication authenticationController = new Authentication();

        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repeatedPassword = request.getParameter("password-repeat");

        User userToRegister = new User(email,username,password);
        authenticationController.register(userToRegister,repeatedPassword);

        RequestDispatcher dispatcher = request.getRequestDispatcher("penpencil/login.jsp");
        request.setAttribute("text-result", "Success! Now you can login with your data");
        dispatcher.forward(request, response);
    }
}
