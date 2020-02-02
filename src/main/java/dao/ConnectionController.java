package dao;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionController {


    private String dbUrl;
    private String dbUsername;
    private String dbPassword;
    private String driver;

    public ConnectionController(String propertiesDir) throws FileNotFoundException {
        FileInputStream fis;
        Properties property = new Properties();
        FileInputStream fileInputStream ;

        try {
            fileInputStream = new FileInputStream(propertiesDir);
            property.load(fileInputStream);

            if (!propertiesDir.isEmpty()) {
                driver = property.getProperty("JDBC_DRIVER");
                dbUrl = property.getProperty("DB_URL");
                dbUsername = property.getProperty("DB_USERNAME");
                dbPassword = property.getProperty("DB_PASSWORD");
                System.out.println(driver+dbUrl+dbUsername+dbPassword);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Connection getConnection() {
        try {
            try {
                Class.forName(driver);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (SQLException e) {
            throw new RuntimeException("CANNOT CONECT TO DATABASE!!", e);
        }
    }

    public static void disconnect(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void close(Object... objects) {
        if (objects != null) {
            for (Object o : objects) {
                ;
            }
        }
    }



}
