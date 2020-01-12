package com.kosmos.testtask.domain.repositories;

import com.kosmos.testtask.domain.models.Employee;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface EmployeeRepository {

    Single<List<Employee>> getAll();
    Single<Employee> getEmployee(String id);
    Single<List<Employee>> getEmployeeBySpecialty(int specialtyId);
    Single<Integer> deleteById(String id);
    Completable insert(Employee employee);
    Completable insertAll(List<Employee> employees);
    Completable delete(Employee employee);

}
