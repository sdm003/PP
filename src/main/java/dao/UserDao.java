package dao;

import model.User;

import java.util.List;

public interface UserDao {

    void addUser(User user);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(long id);
    boolean validateUser(String name, String password);
    String getUserRole(String name);

}
