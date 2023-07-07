package com.ev.domain;

import com.ev.service.IEmployee;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BusinessAnalyst extends Employee implements IEmployee {
    private int clientCall;
    private int totalCall;
    String businessAnalystRegex = "\\w+\\s+=\\s+(?<clientCall>\\d+),\\s+\\w+\\s+=\\s+(?<totalCall>\\d+)";
    Pattern businessAnalystPattern = Pattern.compile(businessAnalystRegex);
    public BusinessAnalyst(String employeeText){
        super(employeeText);
        Matcher businessAnalystMatcher = businessAnalystPattern.matcher(employeeMatcher.group("details"));
            if(businessAnalystMatcher.matches()){
                this.clientCall = Integer.parseInt(businessAnalystMatcher.group("clientCall"));
                this.totalCall = Integer.parseInt(businessAnalystMatcher.group("totalCall"));
            }
    }

    @Override
    public String toString() {
        return String.format("%s %s %s : %s",firstName,lastName,dob,getSalary());
    }

    @Override
    public int getSalary() {
        return totalCall * clientCall;
    }
}
