package com.hauschildt.ch5;

import com.hauschildt.data_access.DAO_MySQL;
import com.hauschildt.data_access.UserDAO_MySQL;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ViewUsersServlet", value = "/view-users")
public class ViewUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Add this code in the doGet method of any servlet
        // that you want users to log in first before viewing
        HttpSession session = request.getSession();
        System.out.println(session.getId());
        System.out.println(session.getMaxInactiveInterval());
        if(session.getAttribute("user") == null) {
            response.sendRedirect("login");
            return;
        }
        
        UserDAO_MySQL user_data = new UserDAO_MySQL();
        request.setAttribute("users",user_data.getAll());
        User user = (User)session.getAttribute("user");
        if(user.getPrivileges().equals("admin")) {
            request.getRequestDispatcher("WEB-INF/ch5/view-users.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath()); // Send them to the homepage
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
