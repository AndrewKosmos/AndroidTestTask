package com.kosmos.testtask.data.repositories;

import com.kosmos.testtask.data.database.dao.EmployeeSpecialtyDao;
import com.kosmos.testtask.data.database.mappers.EmployeeSpecialtyMapper;
import com.kosmos.testtask.domain.models.EmployeeSpecialtyRelation;
import com.kosmos.testtask.domain.repositories.EmployeeSpecialtyRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.functions.Action;

public class EmployeeSpecialtyRepositoryImpl implements EmployeeSpecialtyRepository {

    private EmployeeSpecialtyDao dao;

    @Inject
    public EmployeeSpecialtyRepositoryImpl(EmployeeSpecialtyDao dao) {
        this.dao = dao;
    }

    @Override
    public Completable insert(EmployeeSpecialtyRelation relation) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                dao.insert(EmployeeSpecialtyMapper.map(relation));
            }
        });
    }

    @Override
    public Completable insertAll(List<EmployeeSpecialtyRelation> list) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                dao.insertAll(EmployeeSpecialtyMapper.mapList(list));
            }
        });
    }

    @Override
    public Completable deleteAll() {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                dao.deleteAll();
            }
        });
    }

    @Override
    public Completable cleanAndInsert(List<EmployeeSpecialtyRelation> list) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                dao.cleanAndInsert(EmployeeSpecialtyMapper.mapList(list));
            }
        });
    }
}
