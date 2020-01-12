package com.kosmos.testtask.data.repositories;

import com.kosmos.testtask.data.database.dao.SpecialityDao;
import com.kosmos.testtask.data.database.mappers.SpecialityMapper;
import com.kosmos.testtask.domain.models.Speciality;
import com.kosmos.testtask.domain.repositories.SpecialityRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.functions.Action;

public class SpecialityRepositoryImpl implements SpecialityRepository {

    private SpecialityDao specialityDao;

    @Inject
    public SpecialityRepositoryImpl(SpecialityDao specialityDao) {
        this.specialityDao = specialityDao;
    }

    @Override
    public Single<List<Speciality>> getAll() {
        return specialityDao.getAll().map(specialityDbModels -> SpecialityMapper.mapToDomainList(specialityDbModels));
    }

    @Override
    public Single<Speciality> getSpecialty(int id) {
        return specialityDao.getSpecialty(id)
                .map(specialityDbModel -> SpecialityMapper.mapToDomainModel(specialityDbModel));
    }

    @Override
    public Single<Integer> deleteById(int id) {
        return specialityDao.deleteById(id);
    }

    @Override
    public Completable delete(Speciality speciality) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                specialityDao.delete(SpecialityMapper.mapToDbModel(speciality));
            }
        });
    }

    @Override
    public Completable insert(Speciality speciality) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                specialityDao.insert(SpecialityMapper.mapToDbModel(speciality));
            }
        });
    }

    @Override
    public Completable insertAll(List<Speciality> specialities) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                specialityDao.insertAll(SpecialityMapper.mapToDbList(specialities));
            }
        });
    }
}
