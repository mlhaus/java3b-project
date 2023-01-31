package com.hauschildt.ch3and4;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@WebServlet(
        name = "AddServlet"
//        , value = "/add"
        , urlPatterns = {"/add", "/sum", "/addition"}
        , loadOnStartup = 1
)
public class AddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/add.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstNumber = request.getParameter("firstNumber");
        String secondNumber = request.getParameter("secondNumber");
        Map<String,String> results = add(firstNumber, secondNumber);
        request.setAttribute("results", results);
        request.getRequestDispatcher("WEB-INF/add.jsp").forward(request,response);
    }
     private Map<String, String> add(String num1, String num2) {
        Map<String, String> results = new HashMap<>();
        boolean num1Valid = isNumeric(num1);
        boolean num2Valid = isNumeric(num2);
        if(!num1Valid) {
            results.put("num1Error", "Invalid first number");
        }
        if(!num2Valid) {
            results.put("num2Error", "Invalid second number");
        }
        if(num1Valid && num2Valid) {
            BigDecimal n1 = new BigDecimal(num1);
            BigDecimal n2 = new BigDecimal(num2);
            String sum  = n1.add(n2).toString();
            results.put("sum", sum);
        }
        results.put("firstNumber", num1);
        results.put("secondNumber", num2);
        return results;
     }

     private boolean isNumeric(String num) {
        try {
            Double.parseDouble(num);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
     }

}
