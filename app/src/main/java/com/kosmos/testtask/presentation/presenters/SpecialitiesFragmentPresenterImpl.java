package com.kosmos.testtask.presentation.presenters;

import com.kosmos.testtask.domain.interactors.SpecialitiesFragmentInteractor;
import com.kosmos.testtask.domain.models.Speciality;
import com.kosmos.testtask.presentation.SchedulersProvider;

import java.util.List;

public class SpecialitiesFragmentPresenterImpl implements SpecialitiesFragmentPresenter {

    private SpecialitiesFragmentInteractor interactor;
    private SpecialitiesFragmentPresenter.View specialitiesView;
    private SchedulersProvider schedulersProvider;

    public SpecialitiesFragmentPresenterImpl(SpecialitiesFragmentInteractor interactor,
                                             View view,
                                             SchedulersProvider provider) {
        this.interactor = interactor;
        this.specialitiesView = view;
        this.schedulersProvider = provider;
    }

    @Override
    public void getAllSpecialities() {
        specialitiesView.showProgress();
        interactor.getAllSpecialities()
                .observeOn(schedulersProvider.ui())
                .subscribe(this::onSpecialitiesLoaded, this::onSpecialitiesLoadFail);
    }

    private void onSpecialitiesLoaded(List<Speciality> specialities) {
        specialitiesView.hideProgress();
        specialitiesView.showSpecialities(specialities);
    }

    private void onSpecialitiesLoadFail(Throwable throwable) {
        specialitiesView.hideProgress();
        specialitiesView.showError("Specialities load failed: " + throwable.getMessage());
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
