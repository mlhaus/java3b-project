package com.hauschildt.data_access;

import com.hauschildt.ch5.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
                    String phone = resultSet.getString("phone");
                    String email = resultSet.getString("email");
                    char[] password = resultSet.getString("password").toCharArray();
                    String status = resultSet.getString("status");
                    User user = new User(id, firstName, lastName, phone, email, password, status);
                    users.add(user);
                }
                resultSet.close();
                statement.close();
            }
        } catch(SQLException e) {
            System.out.println("Connection failed");
            System.out.println(e.getMessage());
        }
        return users;
    }
}
