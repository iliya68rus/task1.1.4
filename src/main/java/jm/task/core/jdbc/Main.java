package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Array;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Ivan", "Petrov", (byte) 45);
        userService.saveUser("Kosta", "Avshalom", (byte) 12);
        userService.saveUser("Petr", "Kingsly", (byte) 31);
        userService.saveUser("Vasa", "Luck", (byte) 25);
        userService.saveUser("Vera", "Korshynova", (byte) 37);
        userService.removeUserById(1);
        userService.dropUsersTable();
        userService.cleanUsersTable();
        List<User> userList = userService.getAllUsers();
        System.out.println(userList);
    }
}
