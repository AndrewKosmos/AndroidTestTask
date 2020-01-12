package com.kosmos.testtask.domain.repositories;

import com.kosmos.testtask.domain.models.Speciality;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface SpecialityRepository {

    Single<List<Speciality>> getAll();
    Single<Speciality> getSpecialty(int id);
    Single<Integer> deleteById(int id);
    Completable delete(Speciality speciality);
    Completable insert(Speciality speciality);
    Completable insertAll(List<Speciality> specialities);

}
