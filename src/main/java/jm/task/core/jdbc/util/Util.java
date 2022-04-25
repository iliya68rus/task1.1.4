package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private static Util util;
    private static Connection connection;
    private static boolean connectionIsEstablished;

    private static final String url = "jdbc:mysql://localhost:3306/schema";
    private static final String login = "root";
    private static final String pass = "root";

    public static Connection connect() {
        if (util == null) {
            util = new Util();
        }
        if (!connectionIsEstablished) {
            try {
                Driver driver = new com.mysql.cj.jdbc.Driver();
                DriverManager.registerDriver(driver);
                connection = DriverManager.getConnection(url, login, pass);
                connectionIsEstablished = true;
                return connection;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            return connection;
        }
    }

    public static boolean disconnect() {
        try {
            connectionIsEstablished = false;
            connection.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
