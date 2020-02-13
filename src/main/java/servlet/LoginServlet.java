package servlet;


import servlet.entity.UserDto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static domain.entity.EntityMapper.viewToUser;
import static domain.service.AuthenticationService.validateUser;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        UserDto user = (UserDto)session.getAttribute("user");

        if (!validateUser(viewToUser(user))) {
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
        if (!validateUser(viewToUser((UserDto) session.getAttribute("user")))) {
            UserDto user = new UserDto(username, password);

            if (validateUser(viewToUser(user))) {
                session.setAttribute("user", user);
                response.sendRedirect("/profile?username=" + username);
                //System.out.println(" ITS OKAY");

            } else {
                request.setAttribute("text-result", "Error! Login or password is not right");
                RequestDispatcher dispatcher = request.getRequestDispatcher("penpencil/login.jsp");
                dispatcher.forward(request, response);
            }
        }
    }
}