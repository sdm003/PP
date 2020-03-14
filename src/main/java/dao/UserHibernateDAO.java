package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserHibernateDAO implements UserJdbcDao {

    private Session session;

    public UserHibernateDAO(Session session) {
        this.session = session;
        session.beginTransaction();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = session.createQuery("FROM User").list();
        return users;
    }

    @Override
    public void addUser(User user) {
        Transaction transaction = session.getTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteUser(long id) {
        Transaction transaction = session.getTransaction();
        session.delete(getUserById(id));
        transaction.commit();
        session.close();
    }

    @Override
    public void updateUser(User user) {
        Transaction transaction = session.getTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }

    public User getUserById(long id) {
        Query query = session.createQuery("FROM User WHERE id=:id");
        query.setParameter("id", id);
        return (User) query.uniqueResult();
    }

}
