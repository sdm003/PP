package dao.factory;

import dao.UserDao;
import dao.UserJDBCDao;
import dao.factory.UserDaoFactory;
import util.DBHelper;

public class UserDaoFactoryJDBC implements UserDaoFactory {

    private DBHelper dbHelper = DBHelper.getInstance();

    @Override
    public UserDao createUserDAO() {
        return new UserJDBCDao(dbHelper.getConnection());
    }
}
