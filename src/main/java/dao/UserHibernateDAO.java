package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserHibernateDAO implements UserDao {

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

    @Override
    public boolean validateUser(String name, String password) {
        String hql = "SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u " +
                "WHERE u.name=:name and u.password=:password";
        Query query = session.createQuery(hql);
        query.setParameter("name",name);
        query.setParameter("password",password);
        boolean validate = (boolean) query.uniqueResult();
        return validate;
    }

    @Override
    public String getUserRole(String name) {
        String hql = "SELECT role FROM User WHERE name=:name";
        Query query = session.createQuery(hql);
        query.setParameter("name",name);
        return (String) query.uniqueResult();
    }

    public User getUserById(long id) {
        Query query = session.createQuery("FROM User WHERE id=:id");
        query.setParameter("id", id);
        return (User) query.uniqueResult();
    }

}
