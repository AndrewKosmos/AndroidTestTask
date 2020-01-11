package com.kosmos.testtask.presentation.presenters;

import android.util.Log;

import com.kosmos.testtask.data.database.mappers.EmployeeMapper;
import com.kosmos.testtask.data.database.models.EmployeeDbModel;
import com.kosmos.testtask.domain.interactors.MainInteractor;
import com.kosmos.testtask.domain.models.Person;
import com.kosmos.testtask.domain.models.WebResponse;
import com.kosmos.testtask.presentation.SchedulersProvider;

import java.util.List;

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
        //mainView.hideProgress();
        //mainView.showError("Json Loaded! " + response.toString());
        List<Person> personList = response.getPersonList();
        for (Person person : personList) {
            EmployeeDbModel employeeDbModel = EmployeeMapper.map(person);
            String res = "";
            res += "Id = " + employeeDbModel.getId() + " \r\n" +
                    "Name = " + employeeDbModel.getName() + "\r\n" +
                    "LName = " + employeeDbModel.getLastName() + "\r\n" +
                    "BDay = " + employeeDbModel.getBirthday() + "\r\n" +
                    "Url = " + employeeDbModel.getAvatarUrl() + "\r\n\r\n";
            Log.d("ANDRE", "Employee: " + res);
        }
    }

    private void onWebResponseError(Throwable throwable) {
        //todo: check throwable
        mainView.showError("Error while loading Json");
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
