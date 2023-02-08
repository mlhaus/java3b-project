package com.hauschildt.ch3and4;

public class Person implements Comparable<Person> {

    private String firstName;
    private String lastName;
    private double heightInInches;
    private double weightInPounds;

    public static final String DEFAULT_FIRST_NAME = "John";
    public static final String DEFAULT_LAST_NAME = "Doe";
    public static final int DEFAULT_HEIGHT = 0;
    public static final double DEFAULT_WEIGHT = 0;

    public Person() {
        this(DEFAULT_FIRST_NAME, DEFAULT_LAST_NAME, DEFAULT_HEIGHT, DEFAULT_WEIGHT);
    }

    public Person(String firstName) {
        this(firstName, DEFAULT_LAST_NAME, DEFAULT_HEIGHT, DEFAULT_WEIGHT);
    }

    public Person(String firstName, String lastName) {
        this(firstName, lastName, DEFAULT_HEIGHT, DEFAULT_WEIGHT);
    }

    public Person(String firstName, String lastName, int heightInInches, double weightInPounds) {
        setFirstName(firstName);
        setLastName(lastName);
        setHeightInInches(heightInInches);
        setWeightInPounds(weightInPounds);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if(firstName.equals("")) {
            throw new IllegalArgumentException("First name is required");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if(lastName.equals("")) {
            throw new IllegalArgumentException("Last name required");
        }
        this.lastName = lastName;
    }

    public double getHeightInInches() {
        return heightInInches;
    }

    public void setHeightInInches(double heightInInches) {
        if(heightInInches < 0) {
            throw new IllegalArgumentException("Height must be 0 or greater");
        }
        this.heightInInches = heightInInches;
    }

    public double getWeightInPounds() {
        return weightInPounds;
    }

    public void setWeightInPounds(double weightInPounds) {
        if(weightInPounds < 0) {
            throw new IllegalArgumentException("Weight must be 0 or greater");
        }
        this.weightInPounds = weightInPounds;
    }

    public String getBMI() {
        // Body Mass Index (BMI) is a person’s weight in pounds
        // divided by the square of height in feet.
        double result = weightInPounds / Math.pow(heightInInches, 2) * 703;
        String resultStr = String.format("%.1f", result);
        return resultStr;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", heightInInches=" + heightInInches +
                ", weightInPounds=" + weightInPounds +
                '}';
    }

    @Override
    public int compareTo(Person o) {
        int result = this.lastName.compareToIgnoreCase(o.lastName);
        if(result == 0) {
            result = this.firstName.compareToIgnoreCase(o.firstName);
        }
        return result;
    }
}
