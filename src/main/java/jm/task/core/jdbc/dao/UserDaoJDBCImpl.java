package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection;
    {
        try {
            connection = Util.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
       try (Statement st = connection.createStatement()) {
           String SQL = "CREATE TABLE Users (id SERIAL, name varchar(20), lastName varchar(20), age int)";
           st.executeUpdate(SQL);
           connection.commit();
       } catch (SQLException e) {
           try {
               connection.rollback();
           } catch (SQLException ex) {
               throw new RuntimeException(ex);
           }
       }
    }

    public void dropUsersTable() {
        try (Statement st = connection.createStatement()) {
            String SQL = "DROP TABLE Users";
            st.executeUpdate(SQL);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
       try (PreparedStatement ps = connection.prepareStatement("INSERT INTO Users(name, lastName, age) VALUES (?, ?, ?)")) {
           ps.setString(1, name);
           ps.setString(2, lastName);
           ps.setByte(3, age);
           ps.executeUpdate();
           connection.commit();
           System.out.println("User с именем - " + name + " добавлен в таблицу");
       } catch (SQLException e) {
           try {
               connection.rollback();
           } catch (SQLException ex) {
               throw new RuntimeException(ex);
           }
           throw new RuntimeException(e);
       }
    }

    public void removeUserById(long id) {
       try (PreparedStatement ps = connection.prepareStatement("DELETE FROM Users WHERE Id = ?")) {
           ps.setLong(1, id);
           ps.executeUpdate();
           connection.commit();
       } catch (SQLException e) {
           try {
               connection.rollback();
           } catch (SQLException ex) {
               throw new RuntimeException(ex);
           }
           throw new RuntimeException(e);
       }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
           ResultSet rs = statement.executeQuery("SELECT * FROM Users");
           while (rs.next()) {
               User user = new User();
               user.setId(rs.getLong(1));
               user.setName(rs.getString(2));
               user.setLastName(rs.getString(3));
               user.setAge(rs.getByte(4));
               userList.add(user);
       }
        } catch (SQLException e) {
           throw new RuntimeException(e);
       }
        return userList;
    }

    public void cleanUsersTable() {
       try (Statement statement = connection.createStatement()) {
           statement.executeUpdate("TRUNCATE TABLE Users");
           connection.commit();
       } catch (SQLException e) {
           try {
               connection.rollback();
           } catch (SQLException ex) {
               throw new RuntimeException(ex);
           }
           throw new RuntimeException(e);
       }
    }
}
