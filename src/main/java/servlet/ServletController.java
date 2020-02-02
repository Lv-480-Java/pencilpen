package servlet;

import dao.layer.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class ServletController extends HttpServlet {
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {

//        List<User> usersList = new ArrayList<>();
//        Connection connection;
//        Statement statement;
//
//        try{
//        System.out.println("Connecting to database...");
//        connection = DriverManager.getConnection(Property.DB_URL,Property.USER,Property.PASS);
//
//        System.out.println("Creating statement...");
//        statement = connection.createStatement();
//
//        String sql = "SELECT * FROM users";
//        ResultSet resultSet = statement.executeQuery(sql);
//
//        while(resultSet.next()){
//
//            int id  = resultSet.getInt("id");
//            String nickname = resultSet.getString("nickname");
//            String pass = resultSet.getString("pass");
//            String email = resultSet.getString("email");
//            int level = resultSet.getInt("level");
//
//            usersList.add(
//                    new User(id,nickname,pass,email,level)
//            );
//
//        }
//
//        resultSet.close();
//        statement.close();
//        connection.close();
//        }catch(SQLException se){
//
//            se.printStackTrace();
//        }catch(Exception e){
//
//            e.printStackTrace();
//        }

        String name = httpServletRequest.getParameter("name");
        System.out.println((new UserDao()).getUserByLevel(0).toString());
        httpServletResponse.getWriter().print("<html><body>mai DEAR HELIO FROM SERRVVLET " + (new UserDao()).getById(2));


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
