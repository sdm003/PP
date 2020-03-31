package dao.factory;

import dao.UserDao;
import dao.UserHibernateDAO;
import util.DBHelper;

public class UserDaoFactoryHibernate implements UserDaoFactory {
    private DBHelper dbHelper = DBHelper.getInstance();

    @Override
    public UserDao createUserDAO() {
        return new UserHibernateDAO(dbHelper.getSessionFactory());
    }
}
