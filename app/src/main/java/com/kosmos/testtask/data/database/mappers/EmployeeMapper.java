package com.kosmos.testtask.data.database.mappers;

import com.kosmos.testtask.data.database.models.EmployeeDbModel;
import com.kosmos.testtask.domain.models.Employee;
import com.kosmos.testtask.domain.models.Person;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public abstract class EmployeeMapper {

    public static EmployeeDbModel map(Person person) {
        String name = capitalizeString(person.getName().toLowerCase());
        String lastName = capitalizeString(person.getLastName().toLowerCase());
        String birthday = normalizeDate(person.getBirthday());
        String url = person.getAvatarUrl();
        return new EmployeeDbModel(name, lastName, birthday, url);
    }

    public static EmployeeDbModel map(Employee employee) {
        return new EmployeeDbModel(employee.getName(), employee.getLastName(),
                employee.getBirthday(), employee.getAvatarUrl());
    }

    public static Employee mapToDomainModel(EmployeeDbModel dbModel) {
        return new Employee(dbModel.getId(), dbModel.getName(), dbModel.getLastName(),
                dbModel.getBirthday(), dbModel.getAvatarUrl());
    }

    private static String capitalizeString(String str) {
        if (str == null || str.length() <= 0) return "";
        return str.substring(0,1).toUpperCase() + str.substring(1);
    }

    private static String normalizeDate(String date) {
        if (date == null || date.length() <= 0) return "";
        if (!Pattern.matches("\\d\\d\\d\\d-\\d\\d-\\d\\d", date)) {
            if (Pattern.matches("\\d\\d-\\d\\d-\\d\\d\\d\\d", date)) {
                try {
                    Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(date);
                    return new SimpleDateFormat("yyyy-MM-dd").format(date1);
                } catch (ParseException e) {
                    e.printStackTrace();
                    return "";
                }
            }
            else {
                return "";
            }
        }
        return date;
    }

}
