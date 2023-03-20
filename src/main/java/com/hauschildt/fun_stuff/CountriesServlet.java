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
        // https://stackoverflow.com/questions/715650/how-to-clone-arraylist-and-also-clone-its-contents
        List<Country> countriesCopy = new ArrayList<>(countries.size());
        for(Country country: countries) {
            try {
                countriesCopy.add((Country)country.clone());
            } catch(CloneNotSupportedException e) {

            }
        }


        String show = request.getParameter("show");
        if(show == null) {
            show = "all";
        }
        if(!show.equalsIgnoreCase("all")) {
            String showTemp = show;
            String finalShow = showTemp.replaceAll("\\+", " "); // an effectively final variable that can be used with a lambda expression
            countriesCopy.removeIf(country -> !country.getContinent().equals(finalShow));
        } 

        String sort = request.getParameter("sort");
        if(sort == null) {
            sort = "alphaAZ";
        }
        switch (sort) {
            case "alphaAZ":
                countriesCopy.sort((c1, c2) -> c1.getName().compareTo(c2.getName()));
                break;
            case "alphaZA":
                countriesCopy.sort((c1, c2) -> c1.getName().compareTo(c2.getName()) * -1);
                break;
            case "populationAsc":
                countriesCopy.sort((c1, c2) -> c1.getPopulation() - c2.getPopulation());
                break;
            case "populationDesc":
                countriesCopy.sort((c1, c2) -> c2.getPopulation() - c1.getPopulation());
                break;
        }
        
        String search = request.getParameter("search");
        if(search != null) {
            String finalSearch = search;
            countriesCopy.removeIf(country -> !country.getName().toLowerCase().contains(finalSearch.toLowerCase()));
        } else {
            search = "";
        }
        
        request.setAttribute("search", search);
        request.setAttribute("show", show);
        request.setAttribute("sort", sort);
        request.setAttribute("countries", countriesCopy);
        request.getRequestDispatcher("WEB-INF/funstuff/countries.jsp").forward(request, response);
    }
}
