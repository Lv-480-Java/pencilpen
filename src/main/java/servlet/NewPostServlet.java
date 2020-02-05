package servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.IOException;

import domain.logic.NewPostController;
import org.apache.commons.codec.binary.Base64;

import java.io.PrintWriter;
import java.util.UUID;
import java.util.stream.Collectors;


@WebServlet("/add")
public class NewPostServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String passwordAtribute = (String) session.getAttribute("password");
        String usernameAtribute = (String) session.getAttribute("username");
        PrintWriter writer = response.getWriter();
        if(passwordAtribute!=null && usernameAtribute!=null && request.getParameter("description")!=null){
            String uniqueID = UUID.randomUUID().toString();
            NewPostController newPostController = new NewPostController();
            newPostController.fetchPicture(session, request,uniqueID);
            newPostController.addPost(request, session, uniqueID);
        }else {
            writer.write("hello, GEEST");
        }


    }
}