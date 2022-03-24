package ru.job4j.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private static final Logger LOG = LoggerFactory.getLogger(TableEditor.class.getName());

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        try {
            Class.forName(properties.getProperty("hibernate.connection.driver_class"));
            connection = DriverManager.getConnection(
                    properties.getProperty("hibernate.connection.url"),
                    properties.getProperty("hibernate.connection.username"),
                    properties.getProperty("hibernate.connection.password")
            );
        } catch (Exception err) {
            LOG.error("Exception", err);
        }
    }

    private void statement(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (Exception err) {
            LOG.error("statement", err);
        }
    }

    public void createTable(String tableName) {
        statement(String.format("create table if not exists %s();", tableName));
    }

    public void dropTable(String tableName) {
        statement(String.format("drop table if exists %s;", tableName));
    }

    public void addColumn(String tableName, String columnName, String type) {
        statement(String.format("alter table if exists %s add %s %s;", tableName, columnName, type));
    }

    public void dropColumn(String tableName, String columnName) {
        statement(String.format("alter table if exists %s drop column %s;", tableName, columnName));
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        statement(String.format("alter table if exists %s rename column %s to %s;", tableName, columnName, newColumnName));
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) {
        String newTable = "test";
        String pathProperties = "app.properties";
        Properties properties = new Properties();
        try (BufferedReader reader = (new BufferedReader(new FileReader(pathProperties)))) {
            properties.load(reader);
        } catch (Exception err) {
            LOG.error("reader", err);
        }
        try (TableEditor tableEditor = new TableEditor(properties)) {
            tableEditor.createTable(newTable);
            System.out.println("create:\n" + getTableScheme(tableEditor.connection, newTable));
            tableEditor.addColumn(newTable, "number", "int");
            tableEditor.addColumn(newTable, "name", "varchar(30)");
            System.out.println("addColumn:\n" + getTableScheme(tableEditor.connection, newTable));
            tableEditor.dropColumn(newTable, "number");
            System.out.println("dropColumn:\n" + getTableScheme(tableEditor.connection, newTable));
            tableEditor.renameColumn(newTable, "name", "new_name");
            System.out.println("renameColumn:\n" + getTableScheme(tableEditor.connection, newTable));
            tableEditor.dropTable(newTable);
        } catch (Exception err) {
            LOG.error("table editor", err);
        }
    }
}