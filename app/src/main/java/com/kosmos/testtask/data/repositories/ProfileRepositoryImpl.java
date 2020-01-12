package com.kosmos.testtask.data.repositories;

import com.kosmos.testtask.data.database.dao.ProfileDao;
import com.kosmos.testtask.data.database.mappers.ProfileMapper;
import com.kosmos.testtask.domain.models.Profile;
import com.kosmos.testtask.domain.repositories.ProfileRepository;

import javax.inject.Inject;

import io.reactivex.Single;

public class ProfileRepositoryImpl implements ProfileRepository {

    private ProfileDao profileDao;

    @Inject
    public ProfileRepositoryImpl(ProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    @Override
    public Single<Profile> getProfileInfo(String userId) {
        return profileDao.getProfile(userId).map(profileModel -> ProfileMapper.mapToDomainModel(profileModel));
    }
}
