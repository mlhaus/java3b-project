<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Map<String,String> results = (Map<String,String>)request.getAttribute("results");
    if(results == null) {
        results = new HashMap<>();
    }
    String height = results.containsKey("height") ? results.get("height") : "";
    String weight = results.containsKey("weight") ? results.get("weight") : "";
    String heightFilled = results.containsKey("height") ? "filled" : "";
    String weightFilled = results.containsKey("weight") ? "filled" : "";

    String heightError = results.containsKey("heightError") ? results.get("heightError") : "";
    String weightError = results.containsKey("weightError") ? results.get("weightError") : "";
    String bmi = results.containsKey("bmi") ? results.get("bmi") : "";
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>BMI Calculator</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
    <link rel="stylesheet" href="styles/bmi.css">
</head>
<body>
<header>
    <h1>BMI Calculator</h1>
    <p>Please enter your height and weight to calculate your Body Mass Index (BMI)</p>
</header>
<div class="form-container">
    <form action="bmi" method="post" class="cool-form">
        <div class="input-container">
            <input type="text" name="height" id="height" class="<%= heightFilled %>" value="<%= height %>" />
            <label for="height">Height in inches</label>
        </div>
        <div class="message-container">
            <p><%= heightError %></p>
        </div>
        <div class="input-container">
            <input type="text" name="weight" id="weight" class="<%= weightFilled %>" value="<%= weight %>" />
            <label for="weight">Weight in pounds</label>
        </div>
        <div class="message-container">
            <p><%= weightError %></p>
        </div>
        <input type="submit" value="Calculate BMI" />
    </form>
    <% if(!bmi.equals("")) { %>
    <p>Your body mass index is <%= bmi %></p>
    <% } %>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js" integrity="sha512-STof4xm1wgkfm7heWqFJVn58Hm3EtS31XFaagaa8VMReCXAkQnJZ+jEy8PCC/iT18dFy95WcExNHFTqLyp72eQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="scripts/bmi.js"></script>
</body>
</html>