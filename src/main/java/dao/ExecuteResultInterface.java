package dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@FunctionalInterface
public interface ExecuteResultInterface<T> {
        List<T> funcInterface(ResultSet resultSet) throws SQLException;
}
