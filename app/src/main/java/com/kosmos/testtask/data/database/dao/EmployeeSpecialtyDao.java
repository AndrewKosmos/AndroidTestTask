package com.kosmos.testtask.data.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.kosmos.testtask.data.database.models.EmployeeSpecialtyDbModel;

import java.util.List;

@Dao
public abstract class EmployeeSpecialtyDao {

    @Insert
    public abstract void insert(EmployeeSpecialtyDbModel model);

    @Insert
    public abstract void insertAll(List<EmployeeSpecialtyDbModel> list);

    @Query("DELETE FROM employee_specialty")
    public abstract void deleteAll();

    @Transaction
    public void cleanAndInsert(List<EmployeeSpecialtyDbModel> models) {
        deleteAll();
        insertAll(models);
    }
}
