package com.kosmos.testtask.domain.interactors;

import com.kosmos.testtask.domain.models.Speciality;
import com.kosmos.testtask.domain.repositories.SpecialityRepository;
import com.kosmos.testtask.presentation.SchedulersProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class SpecialitiesFragmentInteractor {

    private SpecialityRepository specialityRepository;
    private SchedulersProvider schedulersProvider;

    @Inject
    public SpecialitiesFragmentInteractor(SpecialityRepository specialityRepository,
                                          SchedulersProvider schedulersProvider) {
        this.specialityRepository = specialityRepository;
        this.schedulersProvider = schedulersProvider;
    }

    public Single<List<Speciality>> getAllSpecialities() {
        return specialityRepository.getAll().subscribeOn(schedulersProvider.io());
    }
}
