package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Вася", "Уточкин", (byte) 2);
        userService.saveUser("Андрей", "Харитонов", (byte) 21);
        userService.saveUser("Петя", "Паханов", (byte) 3);
        userService.saveUser("Цифра", "Буква", (byte) 120);
        for (User u : userService.getAllUsers()) {
            System.out.println(u);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
