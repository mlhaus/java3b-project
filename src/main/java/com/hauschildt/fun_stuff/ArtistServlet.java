package com.hauschildt.fun_stuff;

import se.michaelthelin.spotify.model_objects.specification.Artist;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ArtistServlet", value = "/artist")
public class ArtistServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String q = request.getParameter("q");
        if(q == null) {
            q = "Abba";
        }
        request.setAttribute("artist", q);
        Artist[] artists = MySpotify.searchArtists(q);
        request.setAttribute("artists", artists);
        request.getRequestDispatcher("WEB-INF/funstuff/artist.jsp").forward(request, response);
    }
}
