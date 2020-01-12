package com.kosmos.testtask.domain.interactors;

import android.util.Log;

import com.kosmos.testtask.domain.models.Employee;
import com.kosmos.testtask.domain.models.EmployeeSpecialtyRelation;
import com.kosmos.testtask.domain.models.Speciality;
import com.kosmos.testtask.domain.models.WebResponse;
import com.kosmos.testtask.domain.repositories.EmployeeRepository;
import com.kosmos.testtask.domain.repositories.EmployeeSpecialtyRepository;
import com.kosmos.testtask.domain.repositories.SpecialityRepository;
import com.kosmos.testtask.domain.repositories.WebResponseRepository;
import com.kosmos.testtask.presentation.SchedulersProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

public class MainInteractor {

    private WebResponseRepository webResponseRepository;
    private EmployeeRepository employeeRepository;
    private SpecialityRepository specialityRepository;
    private EmployeeSpecialtyRepository employeeSpecialtyRepository;
    private SchedulersProvider schedulersProvider;

    @Inject
    public MainInteractor(WebResponseRepository repository,
                          EmployeeRepository employeeRepository,
                          SpecialityRepository specialityRepository,
                          EmployeeSpecialtyRepository employeeSpecialtyRepository,
                          SchedulersProvider provider) {
        this.webResponseRepository = repository;
        this.employeeRepository = employeeRepository;
        this.specialityRepository = specialityRepository;
        this.employeeSpecialtyRepository = employeeSpecialtyRepository;
        this.schedulersProvider = provider;
    }

    public Single<WebResponse> getWebResponse() {
        return webResponseRepository.getWebResponse().subscribeOn(schedulersProvider.io());
    }

    public Single<List<Employee>> getAllEmployees() {
        return employeeRepository.getAll().subscribeOn(schedulersProvider.io());
    }

    public Completable insertEmployee(Employee employee) {
        return employeeRepository.insert(employee).subscribeOn(schedulersProvider.io());
    }

    public Completable insertEmployeeList(List<Employee> employees) {
        return employeeRepository.insertAll(employees).subscribeOn(schedulersProvider.io());
    }

    public Completable insertSpecialtyList(List<Speciality> specialities) {
        return specialityRepository.insertAll(specialities).subscribeOn(schedulersProvider.io());
    }

    public Completable insertEmployeeSpecialtyList(List<EmployeeSpecialtyRelation> list) {
        return employeeSpecialtyRepository.insertAll(list).subscribeOn(schedulersProvider.io());
    }

    public Completable cleanAndInsertEmployeeSpecList(List<EmployeeSpecialtyRelation> list) {
        return employeeSpecialtyRepository.cleanAndInsert(list).subscribeOn(schedulersProvider.io());
    }

    public Completable saveDataToDatabase(List<Employee> employees,
                                          List<Speciality> specialities,
                                          List<EmployeeSpecialtyRelation> relations) {
        return insertEmployeeList(employees)
                .andThen(insertSpecialtyList(specialities))
                .andThen(cleanAndInsertEmployeeSpecList(relations))
                .subscribeOn(schedulersProvider.io());
    }

}
