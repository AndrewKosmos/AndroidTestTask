package com.kosmos.testtask.domain.interactors;

import com.kosmos.testtask.domain.models.Employee;
import com.kosmos.testtask.domain.repositories.EmployeeRepository;
import com.kosmos.testtask.presentation.SchedulersProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class EmployeesFragmentInteractor {

    private EmployeeRepository employeeRepository;
    private SchedulersProvider schedulersProvider;

    @Inject
    public EmployeesFragmentInteractor(EmployeeRepository employeeRepository,
                                       SchedulersProvider schedulersProvider) {
        this.employeeRepository = employeeRepository;
        this.schedulersProvider = schedulersProvider;
    }

    public Single<List<Employee>> getEmployeesForSpeciality(int specId) {
        return employeeRepository.getEmployeeBySpecialty(specId).subscribeOn(schedulersProvider.io());
    }

}
