package com.kosmos.testtask.domain.repositories;

import com.kosmos.testtask.domain.models.EmployeeSpecialtyRelation;

import java.util.List;

import io.reactivex.Completable;

public interface EmployeeSpecialtyRepository {

    Completable insert(EmployeeSpecialtyRelation relation);
    Completable insertAll(List<EmployeeSpecialtyRelation> list);
    Completable deleteAll();
    Completable cleanAndInsert(List<EmployeeSpecialtyRelation> list);

}
