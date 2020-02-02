package dao.layer;

import dao.ConnectionController;
import dao.DaoException;
import dao.layer.interfaces.DaoInterface;
import domain.entity.User;
import domain.entity.UserRegistered;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements DaoInterface<User> {

    private Logger log = Logger.getLogger(UserDao.class.getName());
    private String log4jConfPath = "src\\main\\resources\\log4j.properties";
    private ConnectionController connectionController;

    @Override
    public UserRegistered create(User user) {
        PropertyConfigurator.configure(log4jConfPath);

        if (user != null) {
            PreparedStatement statement = null;
            try {
                connectionController = new ConnectionController("src/main/resources/database.properties");
                try (Connection connection = connectionController.getConnection()) {
                    String sql = "insert into users ( email, nickname, pass) values ( ?, ?, ?)";
                    statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    statement.setString(1, user.getEmail());
                    statement.setString(2, user.getNickname());
                    statement.setString(3, user.getPass());
                    statement.execute();
                    try (ResultSet resultSet = statement.getGeneratedKeys()) {
                        resultSet.next();
                    }
                }
            } catch (SQLException e) {
                log.fatal("SQL server error, Cannot create user", e);
            } catch (FileNotFoundException e) {
                log.fatal("FileNotFound error, Cannot create user", e);
            }
        } else {
            log.error("argument cant be null");
            throw new IllegalArgumentException();
        }
        return getUserByNickname(user.getNickname());
    }

    public UserRegistered getById(int id) throws DaoException {
        PropertyConfigurator.configure(log4jConfPath);
        log.error("Fetching user by id ");

        UserRegistered user = null;
        String sql = "SELECT id, nickname, pass, email, level FROM users WHERE id = ?";

        try {
            connectionController = new ConnectionController("src/main/resources/database.properties");
            try (Connection connection = connectionController.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)
            ) {
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    user = new UserRegistered(
                            resultSet.getInt("id"),
                            resultSet.getString("nickname"),
                            resultSet.getString("pass"),
                            resultSet.getString("email"),
                            resultSet.getInt("level")
                    );
                }else {
                    return null;
                }
            }
        } catch (SQLException e) {
            log.fatal("SQL server error, Cannot create user", e);
        } catch (FileNotFoundException e) {
            log.fatal("FileNotFound error, Cannot create user", e);
        }
        return user;
    }

    public UserRegistered getUserByNickname(String nickname) {
        PropertyConfigurator.configure(log4jConfPath);
        log.info("Fetching user by nickname ");

        UserRegistered user = null;
        String sql = "SELECT id, nickname, pass, email, level FROM users WHERE nickname = ?";

        try {
            connectionController = new ConnectionController("src/main/resources/database.properties");
            try (Connection connection = connectionController.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)
            ) {
                preparedStatement.setString(1, nickname);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    user = new UserRegistered(
                            resultSet.getInt("id"),
                            resultSet.getString("nickname"),
                            resultSet.getString("pass"),
                            resultSet.getString("email"),
                            resultSet.getInt("level")
                    );
                }else {
                    return null;
                }
            }
        } catch (SQLException e) {
            log.fatal("SQL server error, Cannot create user", e);
        } catch (FileNotFoundException e) {
            log.fatal("FileNotFound error, Cannot create user", e);
        }
        return user;
    }

    public UserRegistered getUserByEmail(String email) {
        PropertyConfigurator.configure(log4jConfPath);
        log.info("Fetching user by email ");

        UserRegistered user = null;
        String sql = "SELECT id, nickname, pass, email, level FROM users WHERE email = ?";

        try {
            connectionController = new ConnectionController("src/main/resources/database.properties");
            try (Connection connection = connectionController.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)
            ) {
                preparedStatement.setString(1, email);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    user = new UserRegistered(
                            resultSet.getInt("id"),
                            resultSet.getString("nickname"),
                            resultSet.getString("pass"),
                            resultSet.getString("email"),
                            resultSet.getInt("level")
                    );
                }else {
                    return null;
                }
            }
        } catch (SQLException e) {
            log.fatal("SQL server error, Cannot create user", e);
        } catch (FileNotFoundException e) {
            log.fatal("FileNotFound error, Cannot create user", e);
        }
        return user;
    }

    public List<UserRegistered> getUserByLevel(int level) {

        PropertyConfigurator.configure(log4jConfPath);
        log.info("Creating new user");

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        UserRegistered user = null;
        List<UserRegistered> userList = new ArrayList<>();

        String sql = "SELECT id, nickname, pass, email, level FROM users WHERE level = " + level + ";";

        try {
            connectionController = new ConnectionController("src/main/resources/database.properties");
            connection = connectionController.getConnection();
            statement = connection.createStatement();
            log.info("statement succes");

            log.info("Getting resultset");
            resultSet = statement.executeQuery(sql);

            log.info("Result set creation Succes");
            while (resultSet.next()) {
                userList.add(
                        new UserRegistered(
                                resultSet.getInt("id"),
                                resultSet.getString("nickname"),
                                resultSet.getString("pass"),
                                resultSet.getString("email"),
                                resultSet.getInt("level")
                        ));
            }
            log.info("User fetching Succes");
        } catch (SQLException e) {
            log.fatal("SQL server error, Cannot create user", e);
        } catch (FileNotFoundException e) {
            log.fatal("FileNotFound error, Cannot create user", e);
        } finally {
            connectionController.close(resultSet, statement, connection);
        }
        return userList;
    }

    @Override
    public void delete(int id) {
        PropertyConfigurator.configure(log4jConfPath);
        log.info("Deleting user");

        Connection connection = null;
        Statement statement = null;

        User user = null;
        List<User> userList = new ArrayList<>();

        String sql = "DELETE FROM users where id= " + id + ";";

        try {
            connectionController = new ConnectionController("src/main/resources/database.properties");
            connection = connectionController.getConnection();
            statement = connection.createStatement();
            log.info("statement succes");

            statement.executeUpdate(sql);
            log.info("User Deleting Succes");

        } catch (SQLException e) {
            log.fatal("SQL server error, Cannot create user", e);
        } catch (FileNotFoundException e) {
            log.fatal("FileNotFound error, Cannot create user", e);
        } finally {
            connectionController.close(statement, connection);
        }
    }

    public void deleteUser(String nickname) {
        PropertyConfigurator.configure(log4jConfPath);
        log.info("Deleting user");

        Connection connection = null;
        Statement statement = null;

        User user = null;
        List<User> userList = new ArrayList<>();

        String sql = "DELETE FROM users where id= " + nickname + ";";

        try {
            connectionController = new ConnectionController("src/main/resources/database.properties");
            connection = connectionController.getConnection();
            statement = connection.createStatement();

            statement.executeUpdate(sql);
        } catch (SQLException e) {
            log.fatal("SQL server error, Cannot create user", e);
        } catch (FileNotFoundException e) {
            log.fatal("FileNotFound error, Cannot create user", e);
        } finally {
            connectionController.close(statement, connection);
        }
    }
}

