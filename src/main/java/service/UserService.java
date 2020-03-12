package service;

import model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    void addUser(User user);
    void deleteUser(int id);
    void updateUser(User user);
}
