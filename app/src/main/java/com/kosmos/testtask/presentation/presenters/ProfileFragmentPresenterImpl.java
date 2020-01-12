package com.kosmos.testtask.presentation.presenters;

import com.kosmos.testtask.domain.interactors.ProfileFragmentInteractor;
import com.kosmos.testtask.domain.models.Profile;
import com.kosmos.testtask.presentation.SchedulersProvider;

public class ProfileFragmentPresenterImpl implements ProfileFragmentPresenter {

    private ProfileFragmentInteractor interactor;
    private ProfileFragmentPresenter.View profileView;
    private SchedulersProvider schedulersProvider;

    public ProfileFragmentPresenterImpl(ProfileFragmentInteractor interactor,
                                        View view,
                                        SchedulersProvider schedulersProvider) {
        this.interactor = interactor;
        this.profileView = view;
        this.schedulersProvider = schedulersProvider;
    }

    @Override
    public void getProfileInfo(String userId) {
        profileView.showProgress();
        interactor.getProfileInfo(userId)
                .observeOn(schedulersProvider.ui())
                .subscribe(this::onProfileLoaded, this::onProfileLoadFailed);
    }

    private void onProfileLoaded(Profile profile) {
        profileView.hideProgress();
        profileView.showProfileInfo(profile);
    }

    private void onProfileLoadFailed(Throwable throwable) {
        profileView.hideProgress();
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
