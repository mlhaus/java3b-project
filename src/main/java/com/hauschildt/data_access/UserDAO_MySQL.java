package com.hauschildt.data_access;


import com.hauschildt.ch5.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO_MySQL implements DAO_MySQL<User> {
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try(Connection connection = getConnection()) {
            if(connection.isValid(2)) {
                // Step 1: make a statement (Statement or PreparedStatement)
                Statement statement = connection.createStatement();
                // Step 2: Execute a query (plain SQL or stored procedure) and return the results
                ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
                // Step 3: Get data from the results
                while(resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    String email = resultSet.getString("email");
                    String status = resultSet.getString("status");
                    String privileges = resultSet.getString("privileges");
                    User user = new User(id, firstName, lastName, email, status, privileges);
                    users.add(user);
                }
                resultSet.close();
                statement.close();
            }
        } catch(SQLException e) {
            System.out.println("Get all users failed");
            System.out.println(e.getMessage());
        }
        return users;
    }

    public User getUser(String email) {
        User user = null;
        try(Connection connection = getConnection()) {
            if(connection.isValid(2)) {
                // Step 1: make a statement (Statement, PreparedStatement, or CallableStatement)
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE email = ?");
                statement.setString(1, email);
                // Step 2: Execute a query (plain SQL or stored procedure) and return the results
                ResultSet resultSet = statement.executeQuery();
                // Step 3: Get data from the results
                if(resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    String password = resultSet.getString("password");
                    String status = resultSet.getString("status");
                    String privileges = resultSet.getString("privileges");
                    user = new User();
                    user.setFirst_name(firstName);
                    user.setLast_name(lastName);
                    user.setPasswordFromDB(password);
                    user.setStatus(status);
                    user.setPrivileges(privileges);
                }
                resultSet.close();
                statement.close();
            }
        } catch(SQLException e) {
            System.out.println("Get single user failed");
            System.out.println(e.getMessage());
        }
        return user;
    }
    
    public int add(User user) {
        int numRowsAffected = 0;
        try(Connection connection = getConnection()) {
            if(connection.isValid(2)) {
                String sql = "INSERT INTO users (first_name, last_name, email, password, status)" +
                        "VALUES (?,?,?,?,?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, user.getFirst_name());
                statement.setString(2, user.getLast_name());
                statement.setString(3, user.getEmail());
                statement.setString(4, BCrypt.hashpw(new String(user.getPassword()), BCrypt.gensalt()));
                statement.setString(5, "active"); // Use inactive if using Twilio to authenticate a user
                numRowsAffected = statement.executeUpdate();
                statement.close();
            }
        } catch(SQLException e) {
            System.out.println("Add user failed");
            System.out.println(e.getMessage());
        }
        return numRowsAffected;
    }
    
}
