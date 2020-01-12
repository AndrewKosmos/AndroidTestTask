package com.kosmos.testtask.data.repositories;

import com.kosmos.testtask.data.database.dao.EmployeeDao;
import com.kosmos.testtask.data.database.mappers.EmployeeMapper;
import com.kosmos.testtask.domain.models.Employee;
import com.kosmos.testtask.domain.repositories.EmployeeRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.functions.Action;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    private EmployeeDao employeeDao;

    @Inject
    public EmployeeRepositoryImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public Single<List<Employee>> getAll() {
        return employeeDao.getAll().map(models -> EmployeeMapper.mapToDomainList(models));
    }

    @Override
    public Single<Employee> getEmployee(String id) {
        return employeeDao.getEmployee(id)
                .map(employeeDbModel -> EmployeeMapper.mapToDomainModel(employeeDbModel));
    }

    @Override
    public Single<List<Employee>> getEmployeeBySpecialty(int specialtyId) {
        return employeeDao.getEmployeeBySpecialty(specialtyId)
                .map(models -> EmployeeMapper.mapToDomainList(models));
    }

    @Override
    public Single<Integer> deleteById(String id) {
        return employeeDao.deleteById(id);
    }

    @Override
    public Completable insert(Employee employee) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                employeeDao.insert(EmployeeMapper.map(employee));
            }
        });
    }

    @Override
    public Completable insertAll(List<Employee> employees) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                employeeDao.insertAll(EmployeeMapper.mapList(employees));
            }
        });
    }

    @Override
    public Completable delete(Employee employee) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                employeeDao.delete(EmployeeMapper.map(employee));
            }
        });
    }
}
