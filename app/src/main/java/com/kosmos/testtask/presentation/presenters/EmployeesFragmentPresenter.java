package com.kosmos.testtask.presentation.presenters;

import com.kosmos.testtask.domain.models.Employee;
import com.kosmos.testtask.presentation.ui.BaseView;

import java.util.List;

public interface EmployeesFragmentPresenter extends BasePresenter{

    interface View extends BaseView {
        void showEmployees(List<Employee> employees);
    }

    void getEmployeesForSpecialty(int specialtyId);
}
