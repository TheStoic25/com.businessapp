package com.ev.domain;

import com.ev.service.IEmployee;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CEO extends Employee implements IEmployee {
    private int clientCall;
    String ceoRegex = "\\w+\\s+=\\s+(?<totalHours>\\d+)";
    Pattern ceoPattern = Pattern.compile(ceoRegex);

    public CEO(String employeeText){
        super(employeeText);
        Matcher ceoMatcher = ceoPattern.matcher(employeeMatcher.group("details"));
        if(ceoMatcher.matches()){
                if(ceoMatcher.matches()){
                    this.clientCall = Integer.parseInt(ceoMatcher.group("totalHours"));
                }
            }
    }

    @Override
    public int getSalary(){
        return 100000 * clientCall;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s : %s",firstName,lastName,dob,getSalary());
    }
}
