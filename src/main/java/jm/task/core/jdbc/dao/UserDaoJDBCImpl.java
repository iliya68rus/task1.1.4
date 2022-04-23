package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Util util;

    public UserDaoJDBCImpl() {
        util = new Util();
    }

    public void createUsersTable() {
        Connection connection = util.connect();
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE users(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), lastName VARCHAR(255), age INT)");
        } catch (SQLException e) {
        } finally {
            util.disconnect(connection);
        }
    }

    public void dropUsersTable() {
        Connection connection = util.connect();
        try (Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE users");
        } catch (SQLException e) {
        } finally {
            util.disconnect(connection);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Connection connection = util.connect();
        String sqlRequest = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try (PreparedStatement preSta = connection.prepareStatement(sqlRequest)) {
            preSta.setString(1, name);
            preSta.setString(2, lastName);
            preSta.setByte(3, age);
            preSta.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.disconnect(connection);
        }
    }

    public void removeUserById(long id) {
        Connection connection = util.connect();
        String sqlRequest = "DELETE FROM users WHERE id = (?)";
        try (PreparedStatement preSta = connection.prepareStatement(sqlRequest)) {
            preSta.setLong(1, id);
            preSta.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.disconnect(connection);
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        Connection connection = util.connect();
        String sqlRequest = "SELECT * FROM users";
        try (PreparedStatement preSta = connection.prepareStatement(sqlRequest)) {
            ResultSet resultSet = preSta.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");
                list.add(new User(name, lastName, age));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.disconnect(connection);
        }
        return list;
    }

    public void cleanUsersTable() {
        Connection connection = util.connect();
        try (Statement statement = connection.createStatement()) {
            statement.execute("TRUNCATE TABLE users");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.disconnect(connection);
        }
    }
}
