package com.ev.domain;

import com.ev.service.IEmployee;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Programmer extends Employee{
    private int hourlyPay;
    private int totalHours;

    String programmerRegex = "\\w+\\s+=\\s+(?<hourlyPay>\\d+),\\s+\\w+\\s+=\\s+(?<totalHours>\\d+)";
    Pattern programmerPattern = Pattern.compile(programmerRegex);//compile

    public Programmer(String employeeText){
        super(employeeText);
            Matcher programmerMatcher = programmerPattern.matcher(employeeMatcher.group("details"));
            if(programmerMatcher.matches()){
                this.hourlyPay = Integer.parseInt(programmerMatcher.group("hourlyPay"));
                this.totalHours = Integer.parseInt(programmerMatcher.group("totalHours"));
        }
    }

    @Override
    public int getSalary(){
        return hourlyPay * totalHours;
    }


    @Override
    public String toString() {
        return String.format("%s %s %s : %s",firstName,lastName,dob,getSalary());
    }

}
