package com.hauschildt.data_access;

import com.hauschildt.fun_stuff.Country;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CountryDAO_CSV {

    public static List<Country> getAll(HttpServletRequest request, HttpServletResponse response) {
        List<Country> countries = new ArrayList<>();
        try(Scanner scanner = new Scanner(new File(request.getServletContext().getRealPath("/WEB-INF/funstuff/countries.csv")))) {
            int lineCount = 0;
            while(scanner.hasNext()) {
                String[] dataStr = scanner.nextLine().split(",");
                lineCount++;
                if(lineCount != 1) {
                    String name = dataStr[0];
                    String continent = dataStr[2];
                    int population = Integer.parseInt(dataStr[5]);
                    String latitude = dataStr[7];
                    String longitude = dataStr[8];
                    Country country = new Country(name, continent, population, latitude, longitude);
                    countries.add(country);
                }
            }
        } catch(FileNotFoundException e) {
            System.out.println("File not found");
        }
        Collections.sort(countries);
        return countries;
    }

}
