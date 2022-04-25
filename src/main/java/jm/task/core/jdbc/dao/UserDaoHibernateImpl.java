package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Session session = Util.getSession();
        try {
            Transaction transaction = session.beginTransaction();
            Query query = session
                    .createSQLQuery("CREATE TABLE users(id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                            "name VARCHAR(255), lastName VARCHAR(255), age INT)")
                    .addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            Session session = Util.getSession();
            Transaction transaction = session.beginTransaction();
            Query query = session
                    .createSQLQuery("DROP TABLE users")
                    .addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSession();
        session.beginTransaction();
        session.save(new User(name, lastName, age));
        session.getTransaction().commit();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSession();
        session.beginTransaction();
        User user = session.get(User.class, id);
        session.delete(user);
        session.getTransaction().commit();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> usersList;
        Session session = Util.getSession();
        session.beginTransaction();
        usersList = session
                .createQuery("from User")
                .getResultList();
        session.getTransaction().commit();
        return usersList;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session
                .createSQLQuery("TRUNCATE TABLE users")
                .addEntity(User.class);
        query.executeUpdate();
        transaction.commit();
    }
}
