package com.kosmos.testtask.data.database.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "specialties")
public class SpecialityDbModel {

    @PrimaryKey
    private int id;
    private String name;

    public SpecialityDbModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
