package dao;

import dao.exception.NoSuchFieldException;
import org.apache.log4j.Logger;

import java.lang.annotation.AnnotationFormatError;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Mapper<T> {

    private static Logger log = Logger.getLogger(Mapper.class.getName());

    private ConnectionController connectionController;
    private final Class<T> typeParameterClass;
    private Field[] allFields;
    private Field idField;
    private String tableName = null;

    private QueryProducer<T> queryProducer;

     public Mapper(Class<T> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
        allFields = typeParameterClass.getDeclaredFields();

        getTableName();

        for (Field field : allFields) {
            if (field.getName().equals("id")) {
                idField = field;
                idField.setAccessible(true);
            }
        }

        queryProducer = new QueryProducer<>(
                allFields,
                tableName,
                idField
        );
    }

    private void getTableName() {
        if (typeParameterClass.isAnnotationPresent(TableName.class)) {
            tableName = typeParameterClass.getAnnotation(TableName.class).name();

            if (tableName.length() < 1) {
                throw new AnnotationFormatError("table name cant be empty");
            }
        } else {
            throw new AnnotationFormatError("no annotation \"TableName\" or annotation parameter is not ok");
        }
    }

    private boolean checkFieldExist(String fieldName) {
        boolean hasFieldName = false;
        for (Field field : allFields) {
            if (field.getName().equals(fieldName)) {
                hasFieldName = true;
                break;
            }
        }
        return hasFieldName;
    }

    private T getInstance() {
        try {
            return typeParameterClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException e) {
            log.error("Cannot create new instance", e);
        } catch (InvocationTargetException e) {
            log.error("Cannot call such method", e);
        } catch (NoSuchMethodException e) {
            log.error("Cannot find such method", e);
        } catch (IllegalAccessException e) {
            log.error("Cannot get access to constructor", e);
        }
        return null;
    }

    private void setFieldValues(String fieldResultSet, Field field, T entityObject) {
        try {
            if (field.getType().getName().equals("java.lang.String")) {
                field.set(entityObject, fieldResultSet);
            }
            if (field.getType().getName().equals("int") || field.getType().getName().equals("Integer")) {
                field.set(entityObject, Integer.parseInt(fieldResultSet));
            }
            if (field.getType().getName().equals("java.util.List")) {
                recursiveMapTheList(field, entityObject);
            }
        } catch (IllegalAccessException e) {
            log.error("Cannot get acces to field", e);
        }
    }

    private boolean fieldIsList(Field field) {
        return (field.getType().getName().equals("java.util.List") ||
                field.getType().getName().equals("dao.Mapper"));
    }

    private List<T> getField(String fieldName, String fieldValue, String sqlQuery ) {
        List<T> entityList = new ArrayList<T>();


        if (fieldName == null || fieldValue == null) {
            throw new IllegalArgumentException();
        }
        if (!checkFieldExist(fieldName)) {
            throw new NoSuchFieldException("Field \'" + fieldName + "\' not found in the class : \"" + typeParameterClass + "\"");
        }

        try (Connection connection = (new ConnectionController()).getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                T entityObject = getInstance();
                if (entityObject == null) {
                    throw new IllegalArgumentException();
                }

                for (Field field : allFields) {
                    field.setAccessible(true);
                    String fieldResultSet = "";

                    if (!fieldIsList(field)) {
                        fieldResultSet = resultSet.getString(field.getName());
                    }

                    setFieldValues(fieldResultSet, field, entityObject);
                }
                entityList.add(entityObject);
            }
        } catch (SQLException e) {
            log.fatal("SQL error", e);
        }
        return entityList;
    }

    public List<T> getBy(String fieldName, String fieldValue){

        String sqlQuery = queryProducer.getSqlSelectQuery(fieldName, fieldValue);
        return getField(fieldName, fieldValue, sqlQuery);
    }

    public List<T> getLike(String fieldName, String fieldValue){

        String sqlQuery = queryProducer.getSqlSelectLikeQuery(fieldName, fieldValue);
        return getField(fieldName, fieldValue, sqlQuery);
    }

    private void recursiveMapTheList(Field field, T entityObject) throws IllegalAccessException {
        for (Field mapField : allFields) {
            if ((field.getName() + "Mapper").equals(mapField.getName())) {
                mapField.setAccessible(true);
                Mapper mapper = (Mapper) mapField.get(entityObject);
                String subTableName = tableName.toLowerCase() + "Id";
                String entityId = idField.get(entityObject).toString();
                field.set(entityObject, mapper.getBy(subTableName, entityId));
            }
        }
    }

    public void addField(T objectToAdd) {
        if (objectToAdd != null) {
            try {
                String sql = queryProducer.getSqlInsertQuery(objectToAdd);
                try (Connection connection = new ConnectionController().getConnection();
                     PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    statement.execute();
                }
            } catch (SQLException e) {
                log.fatal("SQL server error, Cannot create user", e);
            } catch (IllegalAccessException e) {
                log.error("Cannot get acces to field", e);
            }
        } else {
            log.error("argument cant be null");
            throw new IllegalArgumentException();
        }

    }

    public void updateField(T objectToUpdate) {
        if (objectToUpdate != null) {
            PreparedStatement statement = null;
            try {
                String idValue = idField.get(objectToUpdate) + "";
                if (getBy("id", idValue).size() < 1) {
                    throw new IllegalArgumentException("the is no such record in database to update");
                }
                connectionController = new ConnectionController();
                try (Connection connection = connectionController.getConnection()) {
                    String sql = queryProducer.getSqlUpdateQuery(objectToUpdate);
                    statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    statement.execute();
                    try (ResultSet resultSet = statement.getGeneratedKeys()) {
                        resultSet.next();
                    }
                }
            } catch (SQLException e) {
                log.fatal("SQL server error, Cannot create user", e);
            } catch (IllegalAccessException e) {
                log.error("Cannot get acces to field", e);
            }
        } else {
            log.error("argument cant be null");
            throw new IllegalArgumentException();
        }
    }

    public void deleteField(T objectToRemove, String
            condition) {
        if (objectToRemove != null) {
            PreparedStatement statement = null;
            try {
                if(condition.contains("id")) {
                    String idValue = idField.get(objectToRemove) + "";
                    if (getBy("id", idValue).size() < 1) {
                        throw new IllegalArgumentException("the is no such record in database to delete");
                    }
                }
                connectionController = new ConnectionController();
                try (Connection connection = connectionController.getConnection()) {
                    String sql = "DELETE FROM " + tableName + " where " + condition;
                    statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    statement.execute();
                    try (ResultSet resultSet = statement.getGeneratedKeys()) {
                        resultSet.next();
                    }
                }
            } catch (SQLException e) {
                log.fatal("SQL server error, Cannot delete object", e);
            } catch (IllegalAccessException e) {
                log.error("Cannot get acces to field", e);
            }
        } else {
            log.error("argument cant be null");
            throw new IllegalArgumentException();
        }
    }

    public List<T> getAll() {
        List<T> entityList = new ArrayList<T>();

        String sqlQuery = queryProducer.getSqlSelectAllQuery();
        try (Connection connection = (new ConnectionController()).getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                T entityObject = getInstance();
                if (entityObject == null) {
                    throw new IllegalArgumentException();
                }

                for (Field field : allFields) {
                    field.setAccessible(true);
                    String fieldResultSet = "";

                    if (!fieldIsList(field)) {
                        fieldResultSet = resultSet.getString(field.getName());
                    }

                    setFieldValues(fieldResultSet, field, entityObject);
                }
                entityList.add(entityObject);
            }
        } catch (SQLException e) {
            log.fatal("SQL error", e);
        }
        return entityList;
    }

}
