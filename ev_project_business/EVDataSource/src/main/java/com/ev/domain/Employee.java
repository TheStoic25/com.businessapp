package com.ev.domain;

import com.ev.service.IEmployee;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Employee implements IEmployee {

    String firstName;
    String lastName;
    LocalDate dob;

    static String employeeRegex = """
                (?<firstName>\\w+),\\s+(?<lastName>\\w+),\\s+(?<dob>\\d/\\d/\\d{1,4}),\\s+(?<role>\\w+),\\s+\\{(?<details>.*?)\\}\\n
                """;
    static Pattern employeePattern = Pattern.compile(employeeRegex, Pattern.DOTALL | Pattern.COMMENTS);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
    Matcher employeeMatcher = null;
    public Employee(String employeeText){
        employeeMatcher = employeePattern.matcher(employeeText);
        if(employeeMatcher.matches()) {
            this.firstName = employeeMatcher.group("firstName");
            this.lastName = employeeMatcher.group("lastName");
            this.dob = LocalDate.from(formatter.parse(employeeMatcher.group("dob")));
        }
    }

    public static Employee createEmployee(String employeeText){
        Matcher employeeTxtMatcher = employeePattern.matcher(employeeText);
        if(employeeTxtMatcher.matches()){
            return switch (employeeTxtMatcher.group("role")){
                case "Programmer"-> new Programmer(employeeText);
                case "BusinessAnalyst" -> new BusinessAnalyst(employeeText);
                case "CEO" -> new CEO(employeeText);
                default -> null;
            };
        }else{
            return null;
        }
    }

    public int getSalary() {
        return 0;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
}
