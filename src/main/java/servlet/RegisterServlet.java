package servlet;

import domain.entity.User;
import domain.exception.registration.AlreadyExistsException;
import domain.exception.registration.PasswordException;
import domain.service.AuthenticationService;

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
        AuthenticationService authenticationServiceController = new AuthenticationService();

        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repeatedPassword = request.getParameter("password-repeat");

        User userToRegister = new User(email,username,password);
        boolean isSuccessRegistered=false;
        try {
            isSuccessRegistered = authenticationServiceController.register(userToRegister, repeatedPassword);
        }catch (IllegalArgumentException | PasswordException e){
            request.setAttribute("text-result", "Error! password is too weak");
        }catch (AlreadyExistsException e){
            request.setAttribute("text-result", "email or username allready exists");
        }
        if(isSuccessRegistered){
            RequestDispatcher dispatcher = request.getRequestDispatcher("penpencil/login.jsp");
            request.setAttribute("text-result", "Success! Now you can login with your data");
            dispatcher.forward(request, response);
        }else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("penpencil/register.jsp");
            dispatcher.forward(request, response);
        }
    }
}
