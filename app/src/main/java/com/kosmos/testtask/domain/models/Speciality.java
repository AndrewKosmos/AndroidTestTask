package com.kosmos.testtask.domain.models;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Speciality {

    @SerializedName("specialty_id")
    private Integer id;
    private String name;

    public Speciality() {}

    public Speciality(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @NonNull
    @Override
    public String toString() {
        return id + " / " + name;
    }
}
