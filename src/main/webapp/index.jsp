<%@ page import="com.hauschildt.ch5.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    User user = (User)session.getAttribute("user");

%>
<!DOCTYPE html>
<html>
<head>
    <title>Table of Contents</title>
</head>
<body>
<h1>Table of Contents</h1>
<h3>Chapters 3 and 4</h3>
<ul>
    <li><a href="add">Adding App</a></li>
    <li><a href="temp">Temperature Converter</a></li>
    <li><a href="bmi">BMI Calculator</a></li>
    <li><a href="send-message">Send a Message</a></li>
</ul>
<h3>Chapters 5 and 6</h3>
<ul>
    <% if(user != null && user.getPrivileges().equals("admin")){ %>
    <li><a href="view-users">View All Users</a></li>
    <% } %>

    <% if(user == null) { %>
    <li><a href="signup">Register User</a></li>
    <li><a href="login">Login</a></li>
    <% } else { %>
    <li><a href="profile">View Profile</a></li>
    <li><a href="logout">Logout</a></li>
    <% } %>
</ul>
<h3>Fun Stuff</h3>
<ul>
    <li><a href="countries">Countries App</a></li>
    <li><a href="artist">Spotify App</a></li>
    <li><a href="chat">Chat App</a></li>
    <li><a href="tictactoe">Tic Tac Toe App</a></li>
</ul>

</body>
</html>