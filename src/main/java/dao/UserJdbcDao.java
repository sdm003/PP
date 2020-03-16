package dao;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserJdbcDao {

    void addUser(User user) throws SQLException;
    List<User> getAllUsers();
    void updateUser(User user) throws SQLException;
    void deleteUser(long id) throws SQLException;

}
