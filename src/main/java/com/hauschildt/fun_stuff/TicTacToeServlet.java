package com.hauschildt.fun_stuff;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "TicTacToeServlet", value = "/tictactoe")
public class TicTacToeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/funstuff/tictactoe.jsp").forward(request, response);
    }
}
