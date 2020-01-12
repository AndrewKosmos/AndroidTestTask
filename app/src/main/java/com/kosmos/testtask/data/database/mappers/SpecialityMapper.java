package com.kosmos.testtask.data.database.mappers;

import com.kosmos.testtask.data.database.models.SpecialityDbModel;
import com.kosmos.testtask.domain.models.Speciality;

import java.util.ArrayList;
import java.util.List;

public abstract class SpecialityMapper {

    public static SpecialityDbModel mapToDbModel(Speciality speciality) {
        return new SpecialityDbModel(speciality.getId(), speciality.getName());
    }

    public static Speciality mapToDomainModel(SpecialityDbModel specialityDbModel) {
        return new Speciality(specialityDbModel.getId(), specialityDbModel.getName());
    }

    public static List<SpecialityDbModel> mapToDbList(List<Speciality> list) {
        List<SpecialityDbModel> resultList = new ArrayList<>();
        for (Speciality speciality : list) {
            resultList.add(mapToDbModel(speciality));
        }
        return resultList;
    }

    public static List<Speciality> mapToDomainList(List<SpecialityDbModel> models) {
        List<Speciality> resultList = new ArrayList<>();
        for (SpecialityDbModel model : models) {
            resultList.add(mapToDomainModel(model));
        }
        return resultList;
    }

}
