package dao;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserJdbcDao {

    void addUser(User user);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(long id);

}
