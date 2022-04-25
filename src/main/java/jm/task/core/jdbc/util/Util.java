package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private static Util util;
    private static Connection connection;

    private static final String url = "jdbc:mysql://localhost:3306/schema";
    private static final String login = "root";
    private static final String pass = "root";

    public static Connection connect() {
        if (util == null) {
            util = new Util();
        }

        try {
            if (connection == null || connection.isClosed()) {
                Driver driver = new com.mysql.cj.jdbc.Driver();
                DriverManager.registerDriver(driver);
                connection = DriverManager.getConnection(url, login, pass);
            }
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean disconnect() {
        try {
            connection.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
