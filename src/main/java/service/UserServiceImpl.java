package service;

import dao.UserDaoFactory;
import dao.UserJdbcDao;
import dao.UserJdbcDaoImpl;
import model.User;
import util.DBHelper;

import java.util.List;

public class UserServiceImpl implements UserService {

    private static UserServiceImpl userServiceImpl;
    private UserDaoFactory factory;

    private UserServiceImpl() {
        factory = new UserDaoFactory();
    }

    public static UserServiceImpl getInstance() {
        if (userServiceImpl == null) {
            userServiceImpl = new UserServiceImpl();
        }
        return userServiceImpl;
    }
    @Override
    public List<User> getAllUsers() {
        return getUserDAO().getAllUsers();
    }
    @Override
    public void addUser(User user) {
        getUserDAO().addUser(user);
    }
    @Override
    public void deleteUser(int id) {
        getUserDAO().deleteUser(id);
    }
    @Override
    public void updateUser(User user) {
        getUserDAO().updateUser(user);
    }

    public UserJdbcDao getUserDAO() {
        return factory.createUserDAO();
    }

}
