package com.kosmos.testtask.data.database.mappers;

import com.kosmos.testtask.data.database.models.EmployeeSpecialtyDbModel;
import com.kosmos.testtask.domain.models.EmployeeSpecialtyRelation;

import java.util.ArrayList;
import java.util.List;

public abstract class EmployeeSpecialtyMapper {

    public static EmployeeSpecialtyDbModel map(EmployeeSpecialtyRelation relation) {
        return new EmployeeSpecialtyDbModel(relation.getEmployeeId(), relation.getSpecialtyId());
    }

    public static List<EmployeeSpecialtyDbModel> mapList(List<EmployeeSpecialtyRelation> list) {
        List<EmployeeSpecialtyDbModel> resultList = new ArrayList<>();
        for (EmployeeSpecialtyRelation rel : list) {
            resultList.add(map(rel));
        }
        return resultList;
    }

}
