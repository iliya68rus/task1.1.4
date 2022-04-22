package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        throw new RuntimeException();
    }

    public void dropUsersTable() {
        System.out.println("Error");
    }

    public void saveUser(String name, String lastName, byte age) {
        throw new RuntimeException();
    }

    public void removeUserById(long id) {
        throw new RuntimeException();
    }

    public List<User> getAllUsers() {
        throw new RuntimeException();
    }

    public void cleanUsersTable() {
        throw new RuntimeException();
    }
}
