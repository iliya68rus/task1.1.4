package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.*;

public class Util {
    private static Util util;
    private static Connection connection;
    private static SessionFactory sessionFactory;
    private static Session session;

    private static final String url = "jdbc:mysql://localhost:3306/schema";
    private static final String login = "root";
    private static final String pass = "root";

    public static Session getSession() {
        if (sessionFactory == null || sessionFactory.isClosed()) {
            try {
                Configuration configuration = new Configuration()
                        .setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
                        .setProperty("hibernate.connection.url", url)
                        .setProperty("hibernate.connection.username", login)
                        .setProperty("hibernate.connection.password", pass)
                        .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                        .addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                session = sessionFactory.openSession();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (session.isConnected()) {
            return sessionFactory.openSession();
        }
        return session;
    }

    public static boolean offSession() {
        sessionFactory.close();
        return true;
    }


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
        }
        return null;
    }

    public static boolean disconnect() {
        try {
            connection.close();
            return true;
        } catch (SQLException e) {
        }
        return false;
    }
}
