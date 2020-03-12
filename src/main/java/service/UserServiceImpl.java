package service;

import dao.UserJdbcDaoImpl;
import model.User;
import util.DBHelper;

import java.util.List;

public class UserServiceImpl implements UserService {

    private static UserServiceImpl userServiceImpl;
    private UserJdbcDaoImpl userJdbcDAO;

    private UserServiceImpl() {
        userJdbcDAO = new UserJdbcDaoImpl(DBHelper.getInstance().getConnection());
    }

    public static UserServiceImpl getInstance() {
        if (userServiceImpl == null) {
            userServiceImpl = new UserServiceImpl();
        }
        return userServiceImpl;
    }
    @Override
    public List<User> getAllUsers() {
        return userJdbcDAO.getAllUsers();
    }
    @Override
    public void addUser(User user) {
        userJdbcDAO.addUser(user);
    }
    @Override
    public void deleteUser(int id) {
        userJdbcDAO.deleteUser(id);
    }
    @Override
    public void updateUser(User user) {
        userJdbcDAO.updateUser(user);
    }

}
