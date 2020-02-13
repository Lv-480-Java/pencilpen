package servlet;

import domain.service.AuthenticationService;
import servlet.entity.UserDto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static domain.EntityMapper.viewToUser;


@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("penpencil/register.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AuthenticationService authentication = new AuthenticationService();

        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repeatedPassword = request.getParameter("password-repeat");

        UserDto userToRegister = new UserDto(email,username,password);
        boolean isSuccessRegistered=false;

        String result = null;
        try {
            authentication.register(viewToUser(userToRegister), repeatedPassword);
            result = "Success! Now you can login with your data";
            request.setAttribute("text-result", result );

            RequestDispatcher dispatcher = request.getRequestDispatcher("penpencil/login.jsp");
            dispatcher.forward(request, response);
        }catch (Exception e){

            result = e.getMessage();
            request.setAttribute("text-result", result );

            RequestDispatcher dispatcher = request.getRequestDispatcher("penpencil/register.jsp");
            dispatcher.forward(request, response);
        }
    }
}
