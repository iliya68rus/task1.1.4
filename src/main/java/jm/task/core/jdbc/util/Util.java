package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {

    private String url = "jdbc:mysql://localhost:3306/schema";
    private String login = "root";
    private String pass = "root";

    public Connection connect() {
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            return DriverManager.getConnection(url, login, pass);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean disconnect(Connection connection) {
        try {
            connection.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
