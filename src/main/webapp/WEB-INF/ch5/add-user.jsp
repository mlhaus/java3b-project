<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Add a user</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <link href='https://fonts.googleapis.com/css?family=Roboto:500,900,100,300,700,400' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="styles/nav.css">
</head>
<body class="bg-light">
<header>
    <nav class="shift">
        <ul>
            <li><a href="<%= request.getContextPath() %>/">Home</a></li>
            <li><a href="<%= request.getContextPath() %>/signup">Register</a></li>
            <li><a href="<%= request.getContextPath() %>/login">Login</a></li>
        </ul>
    </nav>
</header>

<div class="container">
    <main>
        <div class="pt-4 pb-2 text-center">
            <h2>Register New User</h2>
            <p class="lead">Enter personal information below to create a new user account.</p>
        </div>

        <div class="row">
            <div class="col-md-8 mx-auto">
                ${results["userAddSuccess"]}
                
                <form method="post" action="signup">
                    <div class="row">
                        <div class="col-sm-6 mb-2">
                            <label for="firstName" class="form-label">First name</label>
                            <input type="text" class="form-control ${results["firstNameInvalid"]}" id="firstName" name="firstName" value="${results["firstName"]}">
                            <div class="invalid-feedback">${results["firstNameError"]}</div>
                        </div>

                        <div class="col-sm-6 mb-2">
                            <label for="lastName" class="form-label">Last name</label>
                            <input type="text" class="form-control ${results["lastNameInvalid"]}" id="lastName" name="lastName" value="${results["lastName"]}">
                            <div class="invalid-feedback">${results["lastNameError"]}</div>
                        </div>

                        <div class="col-sm-12 mb-2">
                            <label for="email" class="form-label">Email</label>
                            <input type="text" class="form-control ${results["emailInvalid"]}" id="email" name="email" placeholder="you@example.com" value="${results["email"]}">
                            <div class="invalid-feedback">${results["emailError"]}</div>
                        </div>

                        <div class="col-sm-6 mb-2">
                            <label for="password1" class="form-label">Password</label>
                            <input type="password" class="form-control ${results["password1Invalid"]}" id="password1" name="password1" value="${results["password1"]}">
                            <div class="invalid-feedback">${results["password1Error"]}</div>
                        </div>

                        <div class="col-sm-6 mb-2">
                            <label for="password2" class="form-label">Re-enter Password</label>
                            <input type="password" class="form-control ${results["password2Invalid"]}" name="password2" id="password2" value="${results["password2"]}">
                            <div class="invalid-feedback">${results["password2Error"]}</div>
                        </div>
                    </div>

                    <div class="col-12 form-check my-4">
                        <input type="checkbox" class="form-check-input ${results["agreeInvalid"]}" id="agree-to-terms" name="agree-to-terms" value="agree" ${results["agreeChecked"]}>
                        <label class="form-check-label" for="agree-to-terms">I agree to the <a href="#">terms and conditions</a>.</label>
                        <div class="invalid-feedback">${results["agreeError"]}</div>
                    </div>

                    <button class="w-100 btn btn-primary btn-lg" type="submit">Create new user</button>
                </form>
            </div>
        </div>
    </main>

    <footer class="my-5 pt-5 text-muted text-center text-small">
        <p class="mb-1">&copy; 2017-<script>document.write(new Date().getFullYear())</script> Company Name</p>
        <ul class="list-inline">
            <li class="list-inline-item"><a href="#">Privacy Policy</a></li>
            <li class="list-inline-item"><a href="#">Terms and Conditions</a></li>
            <li class="list-inline-item"><a href="#">Support</a></li>
        </ul>
    </footer>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>