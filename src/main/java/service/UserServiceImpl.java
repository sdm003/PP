package service;

import dao.*;
import dao.factory.UserDaoFactory;
import dao.factory.UserDaoFactoryHibernate;
import dao.factory.UserDaoFactoryJDBC;
import model.User;
import util.DBHelper;
import java.util.List;

public class UserServiceImpl implements UserService {

    private static UserServiceImpl userServiceImpl;
    private UserDaoFactory userDaoFactory = DBHelper.getUserDaoByConfig();
    private UserDao userDao;

    private UserServiceImpl() {userDao = userDaoFactory.createUserDAO();}

    public static UserServiceImpl getInstance() {
        if (userServiceImpl == null) {
            userServiceImpl = new UserServiceImpl();
        }
        return userServiceImpl;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }
    @Override
    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public boolean validateUser(String name, String password) {
        return userDao.validateUser(name,password);
    }

    @Override
    public String getUserRole(String name) {
        return userDao.getUserRole(name);
    }

}
