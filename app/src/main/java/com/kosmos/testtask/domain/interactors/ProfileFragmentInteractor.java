package com.kosmos.testtask.domain.interactors;

import com.kosmos.testtask.domain.models.Profile;
import com.kosmos.testtask.domain.repositories.ProfileRepository;
import com.kosmos.testtask.presentation.SchedulersProvider;

import io.reactivex.Single;

public class ProfileFragmentInteractor {

    private ProfileRepository profileRepository;
    private SchedulersProvider schedulersProvider;

    public ProfileFragmentInteractor(ProfileRepository profileRepository,
                                     SchedulersProvider schedulersProvider) {
        this.profileRepository = profileRepository;
        this.schedulersProvider = schedulersProvider;
    }

    public Single<Profile> getProfileInfo(String userId) {
        return profileRepository.getProfileInfo(userId).subscribeOn(schedulersProvider.io());
    }

}
