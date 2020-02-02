package dao.mapper;

import dao.ConnectionController;
import dao.mapper.exceptions.NoSuchFieldException;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.lang.annotation.AnnotationFormatError;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Mapper<T> {

    private Logger log = Logger.getLogger(Mapper.class.getName());
    private String log4jConfPath = "src\\main\\resources\\log4j.properties";
    private ConnectionController connectionController;
    private final Class<T> typeParameterClass;
    private Field[] allFields;
    private String tableName = null;

    public Mapper(Class<T> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
        allFields = typeParameterClass.getDeclaredFields();
        getTableName();
    }

    private void getTableName(){
        if (typeParameterClass.isAnnotationPresent(TableName.class)) {
            tableName = typeParameterClass.getAnnotation(TableName.class).name();
            if (tableName.length() < 1)
                throw new AnnotationFormatError("table name cant be empty");
        } else {
            throw new AnnotationFormatError("no annotation \"TableName\" or annotation parameter is not ok");
        }
    }

    private String getSqlSelectQuery(String fieldName, String fieldValue){
        StringBuilder sqlQuery = new StringBuilder("SELECT");
        if(allFields!=null){
            for (Field field : allFields) {

                if (!(field.getType().getName().equals("java.util.List") ||
                        field.getType().getName().equals("dao.mapper.Mapper"))) {
                    sqlQuery.append(", ").append(field.getName());
                }
            }
            sqlQuery = new StringBuilder(sqlQuery.toString().replaceFirst(",", ""));
            sqlQuery.append(" ").append("FROM ");
            sqlQuery.append(tableName);
            sqlQuery.append(" WHERE ").append(fieldName).append("=");
            sqlQuery.append("\"" + fieldValue + "\"");
            System.out.println(sqlQuery);
            return sqlQuery.toString();
        }else{
            throw new IllegalArgumentException("Field field cant be null");
        }
    }

    public List<T> getBy(String fieldName, String fieldValue) {
        List<T> entityList = new ArrayList<T>();

        boolean hasFieldName = false;
        for (Field field : allFields) {
            if (field.getName().equals(fieldName)) {
                hasFieldName = true;
                break;
            }
        }
        if (!hasFieldName) {
            throw new NoSuchFieldException("Field \'" + fieldName + "\' not found in the class : \""+typeParameterClass+"\"");
        }

        String sqlQuery = getSqlSelectQuery(fieldName, fieldValue);
        try {
            connectionController = new ConnectionController("src/main/resources/database.properties");
            try (Connection connection = connectionController.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)
            ) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    T entityObject = null;
                    try {
                        entityObject = typeParameterClass.getDeclaredConstructor().newInstance();
                    } catch (InstantiationException | IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException | NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    for (Field field : allFields) {
                        field.setAccessible(true);
                        String fieldResultSet="";
                        if (!(field.getType().getName().equals("java.util.List") ||
                                field.getType().getName().equals("dao.mapper.Mapper"))) {
                            fieldResultSet = resultSet.getString(field.getName());
                        }
                        if(field.getType().getName().equals("int") || field.getType().getName().equals("Integer")) {
                            field.set(entityObject, Integer.parseInt(fieldResultSet));
                        }else if(field.getType().getName().equals("java.lang.String")){
                            field.set(entityObject,fieldResultSet);
                        }else if (field.getType().getName().equals("java.util.List")){

                            for (Field mapField : allFields) {
                                if((field.getName()+"Mapper").equals(mapField.getName())) {
                                    mapField.setAccessible(true);
                                    Mapper mapper = (Mapper)mapField.get(entityObject);
                                    String subTableName =tableName.toLowerCase()+"Id";
                                    for (Field idField : allFields) {
                                        idField.setAccessible(true);
                                        if(idField.getName().equals("id")){
                                            String entityId = idField.get(entityObject).toString();
                                            field.set(entityObject, mapper.getBy(subTableName, entityId));
                                        }
                                    }
                                }
                            }
                        }
                    }
                    entityList.add(entityObject);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            log.fatal("SQL error", e);
        } catch (FileNotFoundException e) {
            log.fatal("FileNotFound error", e);
        }
        return entityList;
    }

}
