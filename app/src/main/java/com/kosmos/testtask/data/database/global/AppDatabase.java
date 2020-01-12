package com.kosmos.testtask.data.database.global;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.kosmos.testtask.data.database.dao.EmployeeDao;
import com.kosmos.testtask.data.database.dao.EmployeeSpecialtyDao;
import com.kosmos.testtask.data.database.dao.ProfileDao;
import com.kosmos.testtask.data.database.dao.SpecialityDao;
import com.kosmos.testtask.data.database.models.EmployeeDbModel;
import com.kosmos.testtask.data.database.models.EmployeeSpecialtyDbModel;
import com.kosmos.testtask.data.database.models.SpecialityDbModel;

@Database(entities = {EmployeeDbModel.class, SpecialityDbModel.class, EmployeeSpecialtyDbModel.class},
            version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract EmployeeDao employeeDao();
    public abstract SpecialityDao specialityDao();
    public abstract ProfileDao profileDao();
    public abstract EmployeeSpecialtyDao employeeSpecialtyDao();
}
