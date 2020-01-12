package com.kosmos.testtask.data.database.mappers;

import com.kosmos.testtask.data.database.models.EmployeeDbModel;
import com.kosmos.testtask.domain.models.Employee;
import com.kosmos.testtask.domain.models.Person;

import java.util.ArrayList;
import java.util.List;

import static com.kosmos.testtask.domain.utils.StringUtils.capitalizeString;
import static com.kosmos.testtask.domain.utils.StringUtils.normalizeDate;

public abstract class EmployeeMapper {

    public static EmployeeDbModel map(Person person) {
        String name = capitalizeString(person.getName().toLowerCase());
        String lastName = capitalizeString(person.getLastName().toLowerCase());
        String birthday = normalizeDate(person.getBirthday());
        String url = person.getAvatarUrl();
        return new EmployeeDbModel(null, name, lastName, birthday, url);
    }

    public static EmployeeDbModel map(Employee employee) {
        return new EmployeeDbModel(employee.getId(), employee.getName(), employee.getLastName(),
                employee.getBirthday(), employee.getAvatarUrl());
    }

    public static Employee mapToDomainModel(EmployeeDbModel dbModel) {
        return new Employee(dbModel.getId(), dbModel.getName(), dbModel.getLastName(),
                dbModel.getBirthday(), dbModel.getAvatarUrl());
    }

    public static List<Employee> mapToDomainList(List<EmployeeDbModel> models) {
        List<Employee> resultList = new ArrayList<>();
        for (EmployeeDbModel model : models) {
            resultList.add(mapToDomainModel(model));
        }
        return resultList;
    }

    public static List<EmployeeDbModel> mapList(List<Employee> employees) {
        List<EmployeeDbModel> resultList = new ArrayList<>();
        for (Employee employee : employees) {
            resultList.add(map(employee));
        }
        return resultList;
    }

}
