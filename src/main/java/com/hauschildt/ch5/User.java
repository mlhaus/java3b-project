package com.hauschildt.ch5;

public class User {
    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String phone;

    public User() {
        this(0,"John","Doe","john@example.com","555-555-5555");
    }

    public User(int id, String first_name, String last_name, String email, String phone) {
        setId(id);
        setFirst_name(first_name);
        setLast_name(last_name);
        setEmail(email);
        setPhone(phone);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if(id < 0) {
            throw new IllegalArgumentException("Invalid User ID");
        }
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        // https://stackoverflow.com/questions/5963228/regex-for-names-with-special-characters-unicode
        if(!first_name.matches("^(?:[\\p{L}\\p{Mn}\\p{Pd}\\'\\x{2019}]+\\s[\\p{L}\\p{Mn}\\p{Pd}\\'\\x{2019}]+\\s?)+$")) {
            throw new IllegalArgumentException("Invalid characters in first name");
        }
        if(first_name.length() > 50) {
            throw new IllegalArgumentException("Last name cannot have more than 50 characters");
        }
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        if(!last_name.matches("^(?:[\\p{L}\\p{Mn}\\p{Pd}\\'\\x{2019}]+\\s[\\p{L}\\p{Mn}\\p{Pd}\\'\\x{2019}]+\\s?)+$")) {
            throw new IllegalArgumentException("Invalid characters in last name");
        }
        if(last_name.length() > 50) {
            throw new IllegalArgumentException("Last name cannot have more than 50 characters");
        }
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        // https://www.abstractapi.com/tools/email-regex-guide
        if(!email.matches("^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$")) {
            throw new IllegalArgumentException("Invalid email address");
        }
        if(email.length() > 100) {
            throw new IllegalArgumentException("Email cannot have more than 100 characters");
        }
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        // https://regex101.com/library/cFEhad
        if(!phone.matches("^(1\\s?)?(\\d{3}|\\(\\d{3}\\))[\\s\\-]?\\d{3}[\\s\\-]?\\d{4}$")) {
            throw new IllegalArgumentException("Invalid US phone number");
        }
        this.phone = phone;
    }
}
