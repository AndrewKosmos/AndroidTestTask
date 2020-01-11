package com.kosmos.testtask.data.database.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "employee_specialty",
        foreignKeys = { @ForeignKey(entity = EmployeeDbModel.class, parentColumns = "id", childColumns = "empId", onDelete = CASCADE),
                        @ForeignKey(entity = SpecialityDbModel.class, parentColumns = "id", childColumns = "specId", onDelete = CASCADE)
        },
        indices = {@Index("empId"), @Index("specId")})
public class EmployeeSpecialtyDbModel {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "empId")
    private String employeeId;
    @ColumnInfo(name = "specId")
    private int specialtyId;

    public EmployeeSpecialtyDbModel(String employeeId, int specialtyId) {
        this.employeeId = employeeId;
        this.specialtyId = specialtyId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public int getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(int specialtyId) {
        this.specialtyId = specialtyId;
    }
}
