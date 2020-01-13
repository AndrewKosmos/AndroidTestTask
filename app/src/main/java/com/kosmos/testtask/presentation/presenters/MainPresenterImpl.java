package com.kosmos.testtask.presentation.presenters;

import android.util.Log;

import com.kosmos.testtask.domain.interactors.MainInteractor;
import com.kosmos.testtask.domain.models.Employee;
import com.kosmos.testtask.domain.models.EmployeeSpecialtyRelation;
import com.kosmos.testtask.domain.models.Person;
import com.kosmos.testtask.domain.models.Speciality;
import com.kosmos.testtask.domain.models.WebResponse;
import com.kosmos.testtask.domain.utils.StringUtils;
import com.kosmos.testtask.presentation.SchedulersProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainPresenterImpl implements MainPresenter {

    private MainInteractor mainInteractor;
    private MainPresenter.View mainView;
    private SchedulersProvider schedulersProvider;

    public MainPresenterImpl(MainInteractor interactor, View view, SchedulersProvider provider) {
        mainInteractor = interactor;
        mainView = view;
        schedulersProvider = provider;
    }

    @Override
    public void getWebResponse() {
        mainView.showProgress();
        mainInteractor.getWebResponse().observeOn(schedulersProvider.ui())
                .subscribe(this::onWebResponseLoaded, this::onWebResponseError);
    }

    private void onWebResponseLoaded(WebResponse response) {
        List<Person> personList = response.getPersonList();
        List<Employee> employees = new ArrayList<>();
        List<Speciality> specialities;
        Map<Integer, Speciality> specialityMap = new HashMap<>();
        List<EmployeeSpecialtyRelation> relations = new ArrayList<>();

        for(Person person : personList) {
            Employee employee = new Employee(null,
                    StringUtils.capitalizeString(person.getName().toLowerCase()),
                    StringUtils.capitalizeString(person.getLastName().toLowerCase()),
                    StringUtils.normalizeDate(person.getBirthday()),
                    person.getAvatarUrl());
            employees.add(employee);
            List<Speciality> list = person.getSpecialities();
            for(Speciality speciality : list) {
                if(!specialityMap.containsKey(speciality.getId()))
                    specialityMap.put(speciality.getId(), speciality);
                relations.add(new EmployeeSpecialtyRelation(employee.getId(), speciality.getId()));
            }

        }

        specialities = new ArrayList<Speciality>(specialityMap.values());
        mainInteractor.saveDataToDatabase(employees,specialities,relations)
                .observeOn(schedulersProvider.ui())
                .subscribe(this::onDataSaved, this::onDataSaveError);
    }

    private void onWebResponseError(Throwable throwable) {
        mainView.hideProgress();
        mainView.showError("Error while loading Json: " + throwable.getMessage());
    }

    private void onDataSaved() {
        Log.d("MainPresenter", "onDataSaved: DATA SAVED!!!");
        mainView.hideProgress();
        mainView.showSpecialitiesFragment();
    }

    private void onDataSaveError(Throwable throwable) {
        mainView.hideProgress();
        Log.d("MainPresenter", "onDataSaveError: ERROR!");
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
