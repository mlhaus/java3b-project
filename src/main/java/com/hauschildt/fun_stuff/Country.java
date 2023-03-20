package com.hauschildt.fun_stuff;

public class Country implements Comparable<Country>, Cloneable {
    private String name;
    private String continent;
    private int population;
    private String latitude;
    private String longitude;

    public Country() {
        this("Unknown", "Unknown", 0, "0", "0");
    }

    public Country(String name, String continent, int population, String latitude, String longitude) {
        this.name = name;
        this.continent = continent;
        this.population = population;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public int compareTo(Country o) {
        return this.name.compareTo(o.name);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
