package com.kosmos.testtask.data.database.mappers;

import android.text.TextUtils;

import com.kosmos.testtask.data.database.models.EmployeeDbModel;
import com.kosmos.testtask.domain.models.Employee;
import com.kosmos.testtask.domain.models.Person;

public abstract class EmployeeMapper {

    public static EmployeeDbModel map(Person person) {
        String name = capitalizeString(person.getName().toLowerCase());
        String lastName = capitalizeString(person.getLastName().toLowerCase());
        String birthday = person.getBirthday();
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
        if (TextUtils.isEmpty(str)) return "";
        return str.substring(0,1).toUpperCase() + str.substring(1);
    }

    /*private static String normalizeDate(String date) {

    }*/

}
