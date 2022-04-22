package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    public void createUsersTable() {
        try {
            Util util = new Util();
            Connection connection = util.connect();
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE users(" +
                    "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(255) NOT NULL," +
                    "lastName VARCHAR(255) NOT NULL," +
                    "age INTEGER NOT NULL )", 1);
            util.disconnect(connection);
        } catch (SQLException e) {
            UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
            userDaoJDBC.createUsersTable();
        }
    }

    public void dropUsersTable() {
        try {
            Util util = new Util();
            Connection connection = util.connect();
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE users");
            util.disconnect(connection);
        } catch (SQLException e) {
            UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
            userDaoJDBC.dropUsersTable();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        try {
            Util util = new Util();
            Connection connection = util.connect();
            PreparedStatement preSta = connection.prepareStatement("INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)");
            preSta.setString(1, name);
            preSta.setString(2, lastName);
            preSta.setByte(3, age);
            preSta.executeUpdate();
            util.disconnect(connection);
        } catch (SQLException e) {
            UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
            userDaoJDBC.saveUser(name, lastName, age);
        }
    }

    public void removeUserById(long id) {
        try {
            Util util = new Util();
            Connection connection = util.connect();
            PreparedStatement preSta = connection.prepareStatement("DELETE FROM users WHERE id = (?)");
            preSta.setLong(1, id);
            preSta.executeUpdate();
            util.disconnect(connection);
        } catch (SQLException e) {
            UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
            userDaoJDBC.removeUserById(id);
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try {
            Util util = new Util();
            Connection connection = util.connect();
            PreparedStatement preSta = connection.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = preSta.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");
                list.add(new User(name,lastName, age));
            }
            util.disconnect(connection);
        } catch (SQLException e) {
            UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
            userDaoJDBC.getAllUsers();
        }
        return list;
    }

    public void cleanUsersTable() {
        try {
            Util util = new Util();
            Connection connection = util.connect();
            Statement statement = connection.createStatement();
            statement.execute("TRUNCATE TABLE users");
            util.disconnect(connection);
        } catch (SQLException e) {
            UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
            userDaoJDBC.cleanUsersTable();
        }
    }
}
