package com.hauschildt.fun_stuff;

import com.hauschildt.data_access.CountryDAO_CSV;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CountriesServlet", value = "/countries")
public class CountriesServlet extends HttpServlet {
    private List<Country> countries;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(countries == null) {
            countries = CountryDAO_CSV.getAll(request, response);
        }
        String show = request.getParameter("show");
        if(show == null) {
            show = "all";
        }
        show = show.replaceAll("\\+", " ");
        if(show.equals("all")) {
            request.setAttribute("countries", countries);
        } else {
            // https://stackoverflow.com/questions/715650/how-to-clone-arraylist-and-also-clone-its-contents
            List<Country> countriesFiltered = new ArrayList<>(countries.size());
            for(Country country: countries) {
                try {
                    countriesFiltered.add((Country)country.clone());
                } catch(CloneNotSupportedException e) {

                }
            }
            String finalShow = show; // an effectively final variable that can be used with a lambda expression
            countriesFiltered.removeIf(country -> !country.getContinent().equals(finalShow));
            request.setAttribute("countries", countriesFiltered);
        }

        String sort = request.getParameter("sort");
        if(sort != null) {
            switch (sort) {
                case "alphaAZ":
                    countries.sort((c1, c2) -> c1.getName().compareTo(c2.getName()));
                    break;
                case "alphaZA":
                    countries.sort((c1, c2) -> c1.getName().compareTo(c2.getName()) * -1);
                    break;
                case "populationAsc":
                    countries.sort((c1, c2) -> c1.getPopulation() - c2.getPopulation());
                    break;
                case "populationDesc":
                    countries.sort((c1, c2) -> c2.getPopulation() - c1.getPopulation());
                    break;
            }
        }



        request.getRequestDispatcher("WEB-INF/funstuff/countries.jsp").forward(request, response);
    }
}
