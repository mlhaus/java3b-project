package com.hauschildt.ch5;
import com.hauschildt.data_access.UserDAO_MySQL;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "AddUserServlet", value = "/signup")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/ch5/add-user.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        String[] agreeToTerms = request.getParameterValues("agree-to-terms"); // use with checkboxes
        Map<String, String> results = new HashMap<>();
        User user = new User();
        try {
            user.setFirst_name(firstName);
        } catch(IllegalArgumentException e) {
            results.put("firstNameError", e.getMessage());
            results.put("firstNameInvalid", "is-invalid");
        }
        try {
            user.setLast_name(lastName);
        } catch(IllegalArgumentException e) {
            results.put("lastNameError", e.getMessage());
            results.put("lastNameInvalid", "is-invalid");
        }
        try {
            user.setEmail(email);
        } catch(IllegalArgumentException e) {
            results.put("emailError", e.getMessage());
            results.put("emailInvalid", "is-invalid");
        }
        try {
            user.setPassword(password1.toCharArray());
        } catch(IllegalArgumentException e) {
            results.put("password1Error", e.getMessage());
            results.put("password1Invalid", "is-invalid");
        }
        if(password2.equals("")) {
            results.put("password2Error", "This input is required");
            results.put("password2Invalid", "is-invalid");
        }
        if(!password1.equals(password2)) {
            results.put("password2Error", "Passwords don't match");
            results.put("password2Invalid", "is-invalid");
        }
        if(agreeToTerms == null || !agreeToTerms[0].equals("agree")){
            results.put("agreeError", "You must agree to our terms and conditions");
            results.put("agreeInvalid", "is-invalid");
        }
        if(!results.containsKey("firstNameError") && !results.containsKey("lastNameError")
                && !results.containsKey("emailError") && !results.containsKey("phoneError")
                && !results.containsKey("password1Error") && !results.containsKey("password2Error")
                && !results.containsKey("agreeError")
        ) {
            UserDAO_MySQL dao = new UserDAO_MySQL();
            int numRowsAffected = dao.add(user);
            if(numRowsAffected == 1) {
                results.put("userAddSuccess", "<div class=\"alert alert-success mb-2\" role=\"alert\">New user added. Please login to continue.</div>");
                // TO DO
            }
        } else {
            results.put("firstName", firstName);
            results.put("lastName", lastName);
            results.put("email", email);
            results.put("password1", password1);
            results.put("password2", password2);
            if (agreeToTerms != null && agreeToTerms[0].equals("agree")) {
                results.put("agreeToTerms", agreeToTerms[0]);
            }
        }
        request.setAttribute("results", results);
        request.getRequestDispatcher("WEB-INF/ch5/add-user.jsp").forward(request, response);
    }
}
