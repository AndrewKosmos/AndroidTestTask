package com.kosmos.testtask.domain.repositories;

import com.kosmos.testtask.domain.models.Profile;

import io.reactivex.Single;

public interface ProfileRepository {

    Single<Profile> getProfileInfo(String userId);

}
