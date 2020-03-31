package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import javax.transaction.Transactional;
import java.util.List;

public class UserHibernateDAO implements UserDao {

    SessionFactory sessionFactory;

    public UserHibernateDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        List<User> users = session.createQuery("FROM User").list();
        session.close();
        return users;
    }

    @Override
    public void addUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(user);
        if (tx.getStatus().equals(TransactionStatus.ACTIVE)) {
            tx.commit();
        }
        session.close();
    }

    @Override
    public void deleteUser(long id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(getUserById(id));
        if (tx.getStatus().equals(TransactionStatus.ACTIVE)) {
            tx.commit();
        }
        session.close();
    }

    @Override
    public void updateUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(user);
        if (tx.getStatus().equals(TransactionStatus.ACTIVE)) {
            tx.commit();
        }
        session.close();
    }

    @Override
    public boolean validateUser(String name, String password) {
        Session session = sessionFactory.openSession();
        String hql = "SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u " +
                "WHERE u.name=:name and u.password=:password";
        Query query = session.createQuery(hql);
        query.setParameter("name",name);
        query.setParameter("password",password);
        boolean validate = (boolean) query.uniqueResult();
        session.close();
        return validate;
    }

    @Override
    public String getUserRole(String name) {
        Session session = sessionFactory.openSession();
        String hql = "SELECT role FROM User WHERE name=:name";
        Query query = session.createQuery(hql);
        query.setParameter("name", name);
        String userRole = (String) query.uniqueResult();
        session.close();
        return userRole;
    }

    public User getUserById(long id) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM User WHERE id=:id");
        query.setParameter("id", id);
        User user = (User) query.uniqueResult();
        session.close();
        return user;
    }

}
