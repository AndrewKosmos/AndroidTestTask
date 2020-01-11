package com.kosmos.testtask.data.database.mappers;

import com.kosmos.testtask.data.database.models.ProfileModel;
import com.kosmos.testtask.data.database.models.SpecialityDbModel;
import com.kosmos.testtask.domain.models.Profile;
import com.kosmos.testtask.domain.models.Speciality;

import java.util.ArrayList;
import java.util.List;

public abstract class ProfileMapper {

    public static Profile mapToDomainModel(ProfileModel dbModel) {
        List<Speciality> specialityList = new ArrayList<>();
        List<SpecialityDbModel> dbSpecialitiesList = dbModel.getSpecialityDbModels();
        for (SpecialityDbModel specialityDbModel : dbSpecialitiesList) {
            specialityList.add(SpecialityMapper.mapToDomainModel(specialityDbModel));
        }

        return new Profile(dbModel.getEmployeeDbModel().getId(),
                dbModel.getEmployeeDbModel().getName(),
                dbModel.getEmployeeDbModel().getLastName(),
                dbModel.getEmployeeDbModel().getBirthday(),
                dbModel.getEmployeeDbModel().getAvatarUrl(),
                specialityList);
    }

}
