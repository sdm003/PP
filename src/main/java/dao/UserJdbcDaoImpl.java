package dao;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDaoImpl implements UserJdbcDao {
    private Connection connection;

    public UserJdbcDaoImpl(Connection connection) throws SQLException {
        this.connection = connection;
        this.connection.setAutoCommit(false);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Statement stmt = connection.createStatement(); ResultSet r = stmt.executeQuery("SELECT * FROM users")) {
            while (r.next()) {
                list.add(new User(r.getString(2), r.getInt(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void addUser(User user) throws SQLException {
        String query = "INSERT INTO users (name,age) VALUES (?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getName());
            stmt.setInt(2, user.getAge());
            stmt.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(long id) throws SQLException {
        String query = "DELETE FROM users WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) throws SQLException {
        String query = "UPDATE users SET name=?, age=? WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getName());
            stmt.setInt(2, user.getAge());
            stmt.setLong(3, user.getId());
            stmt.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        }
    }
}
