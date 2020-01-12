package com.kosmos.testtask.presentation.presenters;

import com.kosmos.testtask.domain.interactors.EmployeesFragmentInteractor;
import com.kosmos.testtask.domain.models.Employee;
import com.kosmos.testtask.presentation.SchedulersProvider;

import java.util.List;

public class EmployeesFragmentPresenterImpl implements EmployeesFragmentPresenter {

    private EmployeesFragmentInteractor interactor;
    private EmployeesFragmentPresenter.View employeesView;
    private SchedulersProvider schedulersProvider;

    public EmployeesFragmentPresenterImpl(EmployeesFragmentInteractor interactor,
                                          View view,
                                          SchedulersProvider provider) {
        this.interactor = interactor;
        this.employeesView = view;
        this.schedulersProvider = provider;
    }

    @Override
    public void getEmployeesForSpecialty(int specialtyId) {
        employeesView.showProgress();
        interactor.getEmployeesForSpeciality(specialtyId)
                .observeOn(schedulersProvider.ui())
                .subscribe(this::onEmployeesLoaded, this::onEmployeesLoadFailed);
    }

    private void onEmployeesLoaded(List<Employee> employees) {
        employeesView.hideProgress();
        employeesView.showEmployees(employees);
    }

    private void onEmployeesLoadFailed(Throwable throwable) {
        employeesView.hideProgress();
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError(String message) {

    }
}
