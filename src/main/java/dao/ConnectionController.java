package dao;


import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionController {

    private Logger log = Logger.getLogger(Mapper.class.getName());
    private String dbUrl;
    private String dbUsername;
    private String dbPassword;
    private String driver;
    Connection connection;

    private void initializeParameters() {
        Properties property = new Properties();

        FileInputStream fileInputStream;
        String propertiesDir = "src/main/resources/database.properties";
        try {
            fileInputStream = new FileInputStream(propertiesDir);
            property.load(fileInputStream);

            driver = property.getProperty("JDBC_DRIVER");
            dbUrl = property.getProperty("DB_URL");
            dbUsername = property.getProperty("DB_USERNAME");
            dbPassword = property.getProperty("DB_PASSWORD");

        } catch (IOException e) {
            log.error("File propertis cannot be opened ", e);
        }
    }

    private void initializeDriver() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            log.error("Cannot register JDBC Driver", e);
        }
    }

    Connection getConnection() {
        initializeParameters();
        initializeDriver();
        try {
            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (SQLException e) {
            log.error("Cannot create connection", e);
        }
        return connection;
    }
}
