package dao;


import dao.Mapper;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.util.Arrays;

public class QueryProducer<T> {

    private static Logger log = Logger.getLogger(Mapper.class.getName());

    private Field idField;
    private String tableName;
    private Field[] allFields;

    private final String sqlSelectQuery = "SELECT %s FROM %s WHERE %s = \"%s\"";
    private final String sqlSelectLikeQuery = "SELECT %s FROM %s WHERE %s LIKE \"%s\"";

    private final String sqlSelectAllQuery = "SELECT %s FROM %s";

    private final String SqlInsertQuery = "INSERT INTO %s (%s) values (%s)";
    private final String SqlUpdateQuery = "UPDATE %s SET %s WHERE id = \"%s\"";

    public QueryProducer(Field[] allFields, String tableName, Field idField) {
        this.tableName = tableName;
        this.allFields = allFields;
        this.idField = idField;
    }

    String getSqlSelectQuery(String fieldName, String fieldValue) {
        StringBuilder fieldsString = new StringBuilder("");

        String fields = getAllFields();
        String sqlQuery = String.format(sqlSelectQuery, fields, tableName, fieldName, fieldValue);

        return sqlQuery;
    }

    String getSqlSelectFieldQuery(String[] fields, String fieldRequest, String fieldValue) {
        StringBuilder fieldsString = new StringBuilder("");

        String field = Arrays
                .toString(fields)
                .replaceAll("\\[", " ")
                .replaceAll("]", " ");
        String sqlQuery = String.format(sqlSelectQuery, field, tableName, fieldRequest, fieldValue);

        return sqlQuery;
    }

    String getSqlSelectLikeQuery(String fieldName, String fieldValue) {
        StringBuilder fieldsString = new StringBuilder("");

        String fields = getAllFields();
        String sqlQuery = String.format(sqlSelectLikeQuery, fields, tableName, fieldName, "%" + fieldValue + "%");

        return sqlQuery;
    }

    String getSqlSelectAllQuery() {

        String fields = getAllFields();
        String sqlQuery = String.format(sqlSelectAllQuery, fields, tableName);

        return sqlQuery;

    }

    String getSqlInsertQuery(T objectToAdd) throws IllegalAccessException {
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

        return sqlQuery;
    }

    String getSqlUpdateQuery(T objectToUpdate) {
        String sqlQuery = "";
        StringBuilder sqlQueryColumns = new StringBuilder();
        try {
            sqlQueryColumns.append(getColumnsForIsnertion(objectToUpdate));

            sqlQuery = String.format(SqlUpdateQuery,
                    tableName,
                    sqlQueryColumns,
                    idField.get(objectToUpdate));
        } catch (IllegalAccessException e) {
            log.error("Cannot get acces to field", e);
        }
        return sqlQuery;
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

    private boolean fieldIsList(Field field) {
        return (field.getType().getName().equals("java.util.List") ||
                field.getType().getName().equals("dao.Mapper"));
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


}
