package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static Connection connection;
    private static Statement statement;

    static {
        try {
            connection = Util.getConnection();
        } catch (SQLException e) {
            System.out.println("Не удалось подключиться " + e);
        }
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("Не удалось создать statement " + e);
        }
    }

    public void createUsersTable() {
        try {
            statement.executeUpdate("CREATE TABLE Users(" +
                    "Id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "Name VARCHAR(100), " +
                    "LastName VARCHAR(200), " +
                    "Age INT)");
        } catch (SQLException e) {
            System.out.println("Таблица уже создана");
        }
    }

    public void dropUsersTable() {
        try {
            statement.executeUpdate("DROP TABLE Users");
        } catch (SQLException e) {
            System.out.println("Таблица осутствует " + e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            statement.executeUpdate(String.format("INSERT Users(Name, LastName, Age) VALUES('%s', '%s', '%d')", name, lastName, age));
            System.out.printf("User с именем – %s добавлен в базу данных\n", name);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void removeUserById(long id) {
        try {
            statement.executeUpdate(String.format("DELETE FROM Users WHERE ID = %d", id));
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Users");
            while (resultSet.next()) {
                userList.add(new User(resultSet.getString(2), resultSet.getString(3), resultSet.getByte(4)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return userList;
    }

    public void cleanUsersTable() {
        try {
            statement.executeUpdate("TRUNCATE TABLE Users");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
