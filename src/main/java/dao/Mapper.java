package dao;

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
    private final String sqlSelectQuery = "SELECT %s FROM %s WHERE %s = \"%s\"";
    private final String sqlSelectAllQuery = "SELECT %s FROM %s";

    private final String SqlInsertQuery = "INSERT INTO %s (%s) values (%s)";
    private final String SqlUpdateQuery = "UPDATE %s SET %s WHERE id = \"%s\"";

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

    private String getAllFields() {
        StringBuilder fieldsString = new StringBuilder("");

        for (Field field : allFields) {
            if (!fieldIsList(field)) {
                fieldsString
                        .append(", ")
                        .append(field.getName());
            }
        }
        StringBuilder fields = new StringBuilder(
                fieldsString
                        .toString()
                        .replaceFirst(",", "")
        );
        return fields.toString();
    }

    private String getSqlSelectQuery(String fieldName, String fieldValue) {
        StringBuilder fieldsString = new StringBuilder("");

        String fields = getAllFields();
        String sqlQuery = String.format(sqlSelectQuery, fields, tableName, fieldName, fieldValue);

        System.out.println(sqlQuery);
        return sqlQuery;
    }

    private String getSqlSelectAllQuery() {

        String fields = getAllFields();
        String sqlQuery = String.format(sqlSelectAllQuery, fields, tableName);

        System.out.println(sqlQuery);
        return sqlQuery;

    }

    private String getSqlInsertQuery(T objectToAdd) throws IllegalAccessException {
        StringBuilder sqlQueryColumns = new StringBuilder();
        StringBuilder sqlQueryValues = new StringBuilder();

        for (Field field : allFields) {
            field.setAccessible(true);

            if (!(fieldIsList(field) ||
                  field.getName().equals("id") ||
                  field.get(objectToAdd) == null
            )) {
                sqlQueryColumns.append(", ").append(field.getName());
                try {
                    sqlQueryValues.append(", \"").append(field.get(objectToAdd)).append("\"");
                } catch (IllegalAccessException e) {
                    log.error("Cannot get acces to field", e);
                }
            }
        }
        sqlQueryColumns = new StringBuilder(sqlQueryColumns.toString().replaceFirst(",", ""));
        sqlQueryValues = new StringBuilder(sqlQueryValues.toString().replaceFirst(",", ""));

        String sqlQuery = String.format(SqlInsertQuery, tableName, sqlQueryColumns, sqlQueryValues);

        System.out.println(sqlQuery);
        return sqlQuery.toString();
    }

    private String getColumnsForIsnertion(T objectToUpdate) throws IllegalAccessException {
        StringBuilder sqlQueryColumns = new StringBuilder();

        for (Field field : allFields) {
            if (!(field.getType().getName().equals("java.util.List") ||
                    field.getType().getName().equals("dao.Mapper") ||
                    field.getName().equals("id")
            )) {
                field.setAccessible(true);
                sqlQueryColumns.append(", ")
                               .append(field.getName())
                               .append(" = \"")
                               .append(field.get(objectToUpdate))
                               .append("\"");
            }
        }
        sqlQueryColumns = new StringBuilder(sqlQueryColumns.toString().replaceFirst(",", ""));
        return sqlQueryColumns.toString();
    }

    private String getSqlUpdateQuery(T objectToUpdate) {
        String sqlQuery = "";
        StringBuilder sqlQueryColumns = new StringBuilder();
        try {
            sqlQueryColumns.append(getColumnsForIsnertion(objectToUpdate));

            sqlQuery = String.format(SqlUpdateQuery,
                                     tableName,
                                     sqlQueryColumns,
                                     idField.get(objectToUpdate));
            System.out.println(sqlQuery);
        } catch (IllegalAccessException e) {
            log.error("Cannot get acces to field", e);
        }
        return sqlQuery;
    }

    private boolean checkFieldExist(String fieldName){
        boolean hasFieldName = false;
        for (Field field : allFields) {
            if (field.getName().equals(fieldName)) {
                hasFieldName = true;
                break;
            }
        }
        return hasFieldName;
    }

    private T getInstance(){
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

    private boolean fieldIsList(Field field){
        return (field.getType().getName().equals("java.util.List") ||
                field.getType().getName().equals("dao.Mapper"));
    }

    public List<T> getBy(String fieldName, String fieldValue) {
        List<T> entityList = new ArrayList<T>();

        String sqlQuery = getSqlSelectQuery(fieldName, fieldValue);

        if(fieldName==null || fieldValue==null){throw new IllegalArgumentException();}
        if (!checkFieldExist(fieldName)) {
            throw new NoSuchFieldException("Field \'" + fieldName + "\' not found in the class : \"" + typeParameterClass + "\"");
        }

        try (Connection connection = (new ConnectionController()).getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    T entityObject = getInstance();
                    if(entityObject==null){throw new IllegalArgumentException();}

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
                String sql = getSqlInsertQuery(objectToAdd);
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
                    String sql = getSqlUpdateQuery(objectToUpdate);
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

    public void deleteField(T objectToRemove) {
        if (objectToRemove != null) {
            PreparedStatement statement = null;
            try {
                String idValue = idField.get(objectToRemove) + "";
                if (getBy("id", idValue).size() < 1) {
                    throw new IllegalArgumentException("the is no such record in database to delete");
                }
                connectionController = new ConnectionController();
                try (Connection connection = connectionController.getConnection()) {
                    String sql = "DELETE FROM " + tableName + " where id=\"" + idValue + "\"";
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

        String sqlQuery = getSqlSelectAllQuery();
        try (Connection connection = (new ConnectionController()).getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                T entityObject = getInstance();
                if(entityObject==null){throw new IllegalArgumentException();}

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
