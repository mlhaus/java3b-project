package com.hauschildt.ch5;

import com.hauschildt.data_access.UserDAO_MySQL;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("loginFailed", false);
        request.getRequestDispatcher("WEB-INF/ch5/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("loginFailed", false);
        User whatever = (User)request.getAttribute("whatever");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserDAO_MySQL dao = new UserDAO_MySQL();
        User user = dao.getUser(email);
        if(email == null || email.equals("") ||
            password == null || password.equals("") ||
            user == null
        ) {
            request.setAttribute("loginFailed", true);
            request.getRequestDispatcher("WEB-INF/ch5/login.jsp").forward(request, response);
            return;
        } else {
            // User was found
            if(BCrypt.checkpw(password, String.valueOf(user.getPassword()))) {
                // Match!
                String whatever2 = request.getParameter("whatever");
                if(whatever2 == null) {
                    whatever2 = "";
                }
                HttpSession session = request.getSession();
                request.changeSessionId();
                user.unsetPassword();
                session.setAttribute("user", user);
                response.sendRedirect(request.getContextPath()); // redirects to the homepage
                return;
            } else {
                // Not match
                request.setAttribute("loginFailed", true);
                request.getRequestDispatcher("WEB-INF/ch5/login.jsp").forward(request, response);
                return;
            }
        }
        
    }
}
