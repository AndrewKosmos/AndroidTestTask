package com.kosmos.testtask.data.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.kosmos.testtask.data.database.models.SpecialityDbModel;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface SpecialityDao {

    @Query("SELECT * FROM specialties")
    Single<List<SpecialityDbModel>> getAll();

    @Query("SELECT * FROM specialties WHERE id = :id")
    Single<SpecialityDbModel> getSpecialty(int id);

//    @Query("SELECT specialties.id, specialties.name from specialties LEFT JOIN employee_specialty " +
//            "ON specialties.id = employee_specialty.specId WHERE employee_specialty.empId = :userid")
//    Single<List<SpecialityDbModel>> getSpecialtiesForEmployee(String userid);

    @Query("DELETE FROM specialties WHERE id = :id")
    Single<Integer> deleteById(int id);

    @Delete
    void delete(SpecialityDbModel specialityDbModel);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(SpecialityDbModel specialityDbModel);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<SpecialityDbModel> models);
}
