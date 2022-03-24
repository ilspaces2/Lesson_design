package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Коннект к БД
 * <ui>
 * <li> Class.forName("org.postgresql.Driver"); -> регистрация в системе
 * <li> url=jdbc:postgresql://localhost:5432/system_users -> подключаемся к postgres через jdbc // по адресу и порту / имя БД
 * <li> Connection connection = DriverManager.getConnection(url, login, password) ->
 * (адрес /логин/пароль) получаем коннект (если не null то значит законнектились)
 * <li> DatabaseMetaData metaData = connection.getMetaData() - можно получить некоторую информацию о ДБ
 * </ul>
 */

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Config config = new Config("app.properties");
        config.load();
        Class.forName(config.value("hibernate.connection.driver_class"));
        String url = config.value("hibernate.connection.url");
        String login = config.value("hibernate.connection.username");
        String password = config.value("hibernate.connection.password");
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}
