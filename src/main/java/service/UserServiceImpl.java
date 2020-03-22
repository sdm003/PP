package service;

import dao.UserDaoFactory;
import dao.UserDao;
import model.User;

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

    public UserDao getUserDAO() {
        return factory.createUserDAO();
    }

    @Override
    public boolean validateUser(String name, String password) {
        return getUserDAO().validateUser(name,password);
    }

    @Override
    public String getUserRole(String name) {
        return getUserDAO().getUserRole(name);
    }

}
