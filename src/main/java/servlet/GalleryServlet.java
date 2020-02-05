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
import java.io.PrintWriter;

@WebServlet("/gallery")
public class GalleryServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        String passwordAtribute = (String) session.getAttribute("password");
        String usernameAtribute = (String) session.getAttribute("username");

        PrintWriter writer = response.getWriter();
        if(passwordAtribute!=null && usernameAtribute!=null){
            writer.write("hello, "+usernameAtribute);
        }else {
            writer.write("hello, GEEST");
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
