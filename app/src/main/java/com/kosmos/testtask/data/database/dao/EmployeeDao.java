package com.kosmos.testtask.data.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.kosmos.testtask.data.database.models.EmployeeDbModel;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface EmployeeDao {

    @Query("SELECT * FROM employees")
    Single<List<EmployeeDbModel>> getAll();

    @Query("SELECT * FROM employees WHERE id = :id")
    Single<EmployeeDbModel> getEmployee(String id);

    @Query("SELECT employees.id, employees.f_name, employees.l_name, employees.birthday, employees.avatar_url FROM employees" +
            " LEFT JOIN employee_specialty ON employees.id = employee_specialty.empId WHERE employee_specialty.specId = :specId")
    Single<List<EmployeeDbModel>> getEmployeeBySpecialty(int specId);

    @Query("DELETE FROM employees WHERE id = :id")
    Single<Integer> deleteById(String id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(EmployeeDbModel employeeDbModel);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<EmployeeDbModel> models);

    @Delete
    void delete(EmployeeDbModel employeeDbModel);
}
