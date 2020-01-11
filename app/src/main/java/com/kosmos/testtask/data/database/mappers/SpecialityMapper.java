package com.kosmos.testtask.data.database.mappers;

import com.kosmos.testtask.data.database.models.SpecialityDbModel;
import com.kosmos.testtask.domain.models.Speciality;

public abstract class SpecialityMapper {

    public static SpecialityDbModel mapToDbModel(Speciality speciality) {
        return new SpecialityDbModel(speciality.getId(), speciality.getName());
    }

    public static Speciality mapToDomainModel(SpecialityDbModel specialityDbModel) {
        return new Speciality(specialityDbModel.getId(), specialityDbModel.getName());
    }

}
