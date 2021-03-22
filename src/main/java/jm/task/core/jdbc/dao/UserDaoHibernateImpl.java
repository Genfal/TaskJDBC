package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;


public class UserDaoHibernateImpl implements UserDao {


    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = Util.getSesstionFactory().openSession();
        String sql = "CREATE TABLE IF NOT EXISTS USER(" +
                "Id INT PRIMARY KEY AUTO_INCREMENT, " +
                "Name VARCHAR(45), " +
                "LastName VARCHAR(45), " +
                "Age INT)";
        Query query = session.createSQLQuery(sql).addEntity(User.class);
        query.executeUpdate();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSesstionFactory().openSession();
        String sql = "DROP TABLE IF EXISTS USER";
        Query query = session.createSQLQuery(sql).addEntity(User.class);
        query.executeUpdate();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSesstionFactory().openSession();
        session.save(new User(name, lastName, age));
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSesstionFactory().openSession();
        session.delete(id);
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSesstionFactory().openSession();
        List<User> userList = session.createQuery("FROM " + User.class.getSimpleName()).list();
        session.close();
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSesstionFactory().openSession();
        String sql = "TRUNCATE TABLE " + User.class.getSimpleName();
        Query query = session.createSQLQuery(sql);
        query.executeUpdate();
        session.close();
    }
}
