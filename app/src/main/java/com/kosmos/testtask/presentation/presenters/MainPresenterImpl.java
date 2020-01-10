package com.kosmos.testtask.presentation.presenters;

import com.kosmos.testtask.domain.interactors.MainInteractor;
import com.kosmos.testtask.domain.models.WebResponse;
import com.kosmos.testtask.presentation.SchedulersProvider;

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
        mainView.hideProgress();
        mainView.showError("Json Loaded! " + response.toString());
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
