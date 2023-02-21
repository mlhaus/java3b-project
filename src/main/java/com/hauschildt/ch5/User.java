package com.hauschildt.ch5;

public class User {
    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private char[] password;
    private String status;

    public User() {
        this(0,"John","Doe","john@example.com","555-555-5555", "Passw0rd".toCharArray(), "inactive");
    }

    public User(int id, String first_name, String last_name, String email, String phone, char[] password, String status) {
        setId(id);
        setFirst_name(first_name);
        setLast_name(last_name);
        setEmail(email);
        setPhone(phone);
        setPassword(password);
        setStatus(status);
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
        if(first_name.length() == 0) {
            throw new IllegalArgumentException("First name required");
        }
        // https://stackoverflow.com/questions/5963228/regex-for-names-with-special-characters-unicode
//        if(!first_name.matches("[a-zA-Z\\xC0-\\uFFFF]")) {
//            throw new IllegalArgumentException("Invalid characters in first name");
//        }
        if(first_name.length() > 50) {
            throw new IllegalArgumentException("Last name cannot have more than 50 characters");
        }
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        if(last_name.length() == 0) {
            throw new IllegalArgumentException("Last name required");
        }
//        if(!last_name.matches("^(?:[\\p{L}\\p{Mn}\\p{Pd}\\'\\x{2019}]+\\s[\\p{L}\\p{Mn}\\p{Pd}\\'\\x{2019}]+\\s?)+$")) {
//            throw new IllegalArgumentException("Invalid characters in last name");
//        }
        if(last_name.length() > 50) {
            throw new IllegalArgumentException("Last name cannot have more than 50 characters");
        }
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        // https://stackoverflow.com/questions/201323/how-can-i-validate-an-email-address-using-a-regular-expression
//        if(!email.matches("^([\\w\\!\\#$\\%\\&\\'\\*\\+\\-\\/\\=\\?\\^\\`{\\|\\}\\~]+\\.)*[\\w\\!\\#$\\%\\&\\'\\*\\+\\-\\/\\=\\?\\^\\`{\\|\\}\\~]+@((((([a-z0-9]{1}[a-z0-9\\-]{0,62}[a-z0-9]{1})|[a-z])\\.)+[a-z]{2,6})|(\\d{1,3}\\.){3}\\d{1,3}(\\:\\d{1,5})?)$")) {
//            throw new IllegalArgumentException("Invalid email address");
//        }
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
//        if(!phone.matches("^(1\\s?)?(\\d{3}|\\(\\d{3}\\))[\\s\\-]?\\d{3}[\\s\\-]?\\d{4}$")) {
//            throw new IllegalArgumentException("Invalid US phone number");
//        }
        this.phone = phone;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        // https://stackoverflow.com/questions/19605150/regex-for-password-must-contain-at-least-eight-characters-at-least-one-number-a
        // https://stackoverflow.com/questions/7655127/how-to-convert-a-char-array-back-to-a-string
        String passwordStr = String.valueOf(password);
//        if(!passwordStr.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$")) {
//            throw new IllegalArgumentException("Password must include a minimum of eight characters, at least one uppercase letter, one lowercase letter, and one number");
//        }
        this.password = passwordStr.toCharArray();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if(!status.equals("active") && !status.equals("inactive") && !status.equals("locked")) {
            throw new IllegalArgumentException("Invalid status");
        }
        this.status = status;
    }
}
