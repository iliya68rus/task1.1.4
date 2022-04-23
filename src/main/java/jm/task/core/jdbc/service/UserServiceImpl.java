package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDaoJDBCImpl udji;

    public UserServiceImpl(){
        udji = new UserDaoJDBCImpl();
    }
    public void createUsersTable() {
        udji.createUsersTable();
    }

    public void dropUsersTable() {
        udji.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        udji.saveUser(name, lastName, age);
        System.out.println("A user named " + name + " has been added to the database");
    }

    public void removeUserById(long id) {
        udji.removeUserById(id);
    }

    public List<User> getAllUsers() {
        List<User> list = udji.getAllUsers();
        System.out.println(list);
        return list;
    }

    public void cleanUsersTable() {
        udji.cleanUsersTable();
    }
}
