package com.hauschildt.ch3and4;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

public class BMIServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/bmi.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String heightInInches = request.getParameter("height");
        String weightInPounds = request.getParameter("weight");
        Map<String, String> results = new HashMap<>();
        Person person = new Person();
        try {
            person.setHeightInInches(Double.parseDouble(heightInInches));
        } catch(NumberFormatException e) {
            results.put("heightError", "Invalid height");
        } catch(IllegalArgumentException e) {
            results.put("heightError", e.getMessage());
        }
        try {
            person.setWeightInPounds(Double.parseDouble(weightInPounds));
        } catch(NumberFormatException e) {
            results.put("weightError", "Invalid weight");
        } catch(IllegalArgumentException e) {
            results.put("weightError", e.getMessage());
        }
        if(!results.containsKey("heightError") && !results.containsKey("weightError")) {
            results.put("bmi", person.getBMI());
        }
        results.put("height", heightInInches);
        results.put("weight", weightInPounds);
        request.setAttribute("results", results);
        request.getRequestDispatcher("WEB-INF/bmi.jsp").forward(request, response);
    }
}
