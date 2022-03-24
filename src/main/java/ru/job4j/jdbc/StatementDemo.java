package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.StringJoiner;

public class StatementDemo {

    /**
     * Созаем коннект к базе данных
     *
     * @param path файл с настройками БД
     * @return возвращаем коннект к базе данных. если null то не законектились
     */
    private static Connection getConnection(String path) throws Exception {
        Config config = new Config(path);
        config.load();
        Class.forName(config.value("hibernate.connection.driver_class"));
        String url = config.value("hibernate.connection.url");
        String login = config.value("hibernate.connection.username");
        String password = config.value("hibernate.connection.password");
        return DriverManager.getConnection(url, login, password);
    }

    /**
     * Печатаем в консоль таблицу.
     * В ResultSet (statement.executeQuery() после выполнения команды) можем получить данные полученные из БД
     */
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

    /**
     * Statement statement = connection.createStatement() -создали объект для запросов в бд
     * statement.executeUpdate(sql); -выполняем запрос на создание таблицы
     * executeUpdate() - используется для редактирования данных и для создания /удаления таблиц
     * executeQuery()  - используется для выполнения операции SELECT и возвращает объект ResultSet,
     * который позволяет пройтись по результатам запроса.
     * execute() -используется для любых команд. Получить ResultSet или количество строк getResultSet() или getUpdateCount()
     */
    public static void main(String[] args) throws Exception {
        String path = "app.properties";
        try (Connection connection = getConnection(path);
             Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "create table if not exists demo_table(%s, %s);",
                    "id serial primary key",
                    "name text");
            statement.executeUpdate(sql);
            System.out.println(getTableScheme(connection, "demo_table"));
        }
    }
}
