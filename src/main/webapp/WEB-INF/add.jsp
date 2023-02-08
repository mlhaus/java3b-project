<%@ page import="java.time.*" %>
<%@ page import="java.time.format.*" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
    ZonedDateTime today = ZonedDateTime.now();
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
    String currentDate = today.format(dateFormatter);
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM);
    String currentTime = today.format(timeFormatter);
%>
<%
    Map<String,String> results = (Map<String,String>)request.getAttribute("results");
    if(results == null) {
        results = new HashMap<>();
    }
//    String firstNumber = results.containsKey("firstNumber") ? results.get("firstNumber") : "";
//    String secondNumber = results.containsKey("secondNumber") ? results.get("secondNumber") : "";
//    String num1Error = results.containsKey("num1Error") ? results.get("num1Error") : "";
//    String num2Error = results.containsKey("num2Error") ? results.get("num2Error") : "";
//    String num1IsInvalid = results.containsKey("num1Error") ? "is-invalid" : "";
//    String num2IsInvalid = results.containsKey("num2Error") ? "is-invalid" : "";
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>The Adding App</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<div class="container my-4">
    <div class="row">
        <div class="col-3">
            <h1>Add</h1>
            <p class="lead">Enter two numbers and press submit to calculate the sum.</p>
<%-- The action attribute must be the path to the servlet --%>
            <form method="post" action="add">
                <div class="form-group mb-2">
                    <label for="firstNumber">First Number:</label>
                    <div class="input-group has-validation">
                        <input type="text" class="form-control <%= num1IsInvalid %>" id="firstNumber" name="firstNumber" value="<%= firstNumber %>">
                        <div class="invalid-feedback"><%= num1Error %></div>
                    </div>
                </div>
                <div class="form-group mb-2">
                    <label for="secondNumber">Second Number:</label>
                    <div class="input-group has-validation">
                        <input type="text" class="form-control <%= num2IsInvalid %>" id="secondNumber" name="secondNumber" value="<%= secondNumber %>">
                        <div class="invalid-feedback"><%= num2Error %></div>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div><%-- End of the column --%>

        <% if(results.containsKey("sum")) { %>
        <p><%= results.get("firstNumber") %> + <%= results.get("secondNumber") %> = <%= results.get("sum") %></p>
        <% } else { %>
        <p><%= currentDate %> <%= currentTime %></p>
        <% } %>
    </div><%-- End of the row --%>
</div><%-- End of the container --%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>