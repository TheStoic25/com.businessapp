package com.ev;

import com.ev.domain.BusinessAnalyst;
import com.ev.domain.CEO;
import com.ev.domain.Employee;
import com.ev.domain.Programmer;
import com.ev.service.IEmployee;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class EVMain
{
    public static void main( String[] args )
    {
        String employeeData = """
                Leo, dicaprio, 1/1/1990, Programmer, {hourlyPay = 100, totalHours = 110}
                Brad, Harley, 1/1/1990, Programmer, {hourlyPay = 100, totalHours = 110}
                Steve, Crawley, 2/2/1991, Programmer, {hourlyPay = 100, totalHours = 110}
                Ayan, Benerjee, 4/4/1989, BusinessAnalyst, {clientCall = 15, totalCall = 20}
                Shreya, Jr, 2/2/1988, BusinessAnalyst, {clientCall = 11, totalCall = 20}
                Naren, Bist, 3/3/1979, BusinessAnalyst, {clientCall = 25, totalCall = 20}
                Tim, Buchalka, 1/1/1885, CEO, {totalHours = 190}
                """;

        String employeeRegex = """
                (?<firstName>\\w+),\\s+(?<lastName>\\w+),\\s+(?<dob>\\d/\\d/\\d{1,4}),\\s+(?<role>\\w+),\\s+\\{(?<details>.*?)\\}\\n
                """;
        Pattern employeePattern = Pattern.compile(employeeRegex, Pattern.DOTALL | Pattern.COMMENTS);
        Matcher employeeMatcher = employeePattern.matcher(employeeData);

        IEmployee iEmployee = null;
        int totalSalry = 0;
        while (employeeMatcher.find()){
            iEmployee = Employee.createEmployee(employeeMatcher.group());
            System.out.println(iEmployee);
            totalSalry += iEmployee.getSalary();
        }

        NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.CHINA);
        System.out.println(currency.format(totalSalry));
    }

}
