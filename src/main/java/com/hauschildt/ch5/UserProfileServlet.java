package com.hauschildt.ch5;

import com.hauschildt.communication.AzureComm;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserProfileServlet", value = "/profile")
public class UserProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("user") == null) {
            response.sendRedirect("login");
            return;
        }
        request.getRequestDispatcher("WEB-INF/ch5/user-profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        String formName = request.getParameter("name");
        if(formName.equals("testEmail")) {
            AzureComm.sendMail(user.getEmail(),
                    "This is a test",
                    "Dear " + user.getFirst_name() + ", this is the test you requested from our website");
            request.setAttribute("emailSentMsg", "<div class=\"alert alert-success\">Message sent. Please check your email.</div>");
        }
        request.getRequestDispatcher("WEB-INF/ch5/user-profile.jsp").forward(request, response);
    }
}
