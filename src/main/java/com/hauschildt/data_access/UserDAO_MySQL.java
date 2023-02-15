package com.hauschildt.data_access;

import com.hauschildt.ch5.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO_MySQL implements DAO_MySQL<User> {
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try(Connection connection = getConnection()) {
            if(connection.isValid(2)) {
                System.out.println("Connection successful");
            }
        } catch(SQLException e) {
            System.out.println("Connection failed");
            System.out.println(e.getMessage());
        }
        return users;
    }
}
