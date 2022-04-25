package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Ivan", "Petrov", (byte) 45);
        userService.saveUser("Kosta", "Avshalom", (byte) 12);
        userService.saveUser("Petr", "Kingsly", (byte) 31);
        userService.saveUser("Vasa", "Luck", (byte) 25);
        userService.removeUserById(1);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
