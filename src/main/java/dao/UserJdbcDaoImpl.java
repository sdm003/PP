package dao;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDaoImpl implements UserJdbcDao {
    private Connection connection;

    public UserJdbcDaoImpl(Connection connection)  {
        try{
            this.connection = connection;
            createTable();

        }catch (SQLException e){
        }
    }
    @Override
    public void createTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("create table if not exists users (id bigint auto_increment, name varchar(256), age integer , primary key (id))");
        stmt.close();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Statement stmt = connection.createStatement(); ResultSet r = stmt.executeQuery("SELECT * FROM users")) {
            while (r.next()) {
                list.add(new User(r.getInt(1), r.getString(2), r.getInt(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void addUser(User user) {
        String query = "INSERT INTO users (name,age) VALUES (?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getName());
            stmt.setInt(2, user.getAge());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int id) {
        String query = "DELETE FROM users WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {
        String query = "UPDATE users SET name=?, age=? WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getName());
            stmt.setInt(2, user.getAge());
            stmt.setInt(3, user.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
