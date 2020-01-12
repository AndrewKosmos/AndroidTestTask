package com.kosmos.testtask.data.database.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.kosmos.testtask.data.database.models.EmployeeDbModel;
import com.kosmos.testtask.data.database.models.ProfileModel;
import com.kosmos.testtask.data.database.models.SpecialityDbModel;

import java.util.List;

import io.reactivex.Single;

@Dao
public abstract class ProfileDao {

    @Query("SELECT specialties.id, specialties.name from specialties LEFT JOIN employee_specialty " +
            "ON specialties.id = employee_specialty.specId WHERE employee_specialty.empId = :userid")
    public abstract Single<List<SpecialityDbModel>> getSpecialtiesForEmployee(String userid);

    @Query("SELECT * FROM employees WHERE id = :id")
    public abstract Single<EmployeeDbModel> getEmployee(String id);

    public Single<ProfileModel> getProfile(String userId) {
        return getProfileSingle(userId);
    }

    private Single<ProfileModel> getProfileSingle(String userId) {
        Single<EmployeeDbModel> employeeDbModelSingle = getEmployee(userId);
        Single<List<SpecialityDbModel>> specialtiesSingle = getSpecialtiesForEmployee(userId);
        Single<ProfileModel> resultSingle = Single.zip(employeeDbModelSingle, specialtiesSingle, ProfileModel::new);
        return resultSingle;
    }

//    @Transaction
//    public Single<ProfileModel> getProfileInfo(String userId) {
//        Single<EmployeeDbModel> employeeDbModelSingle = getEmployee(userId);
//        Single<List<SpecialityDbModel>> specialtiesSingle = getSpecialtiesForEmployee(userId);
//        Single<ProfileModel> resultSingle = Single.zip(employeeDbModelSingle, specialtiesSingle, ProfileModel::new);
//        return resultSingle;
//    }

//    @Transaction
//    public void getProfileInfo(String userId) {
//        getProfileSingle(userId);
//    }

}
