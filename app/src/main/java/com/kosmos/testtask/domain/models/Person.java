package com.kosmos.testtask.domain.models;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Person {

    @SerializedName("f_name")
    private String name;
    @SerializedName("l_name")
    private String lastName;
    @SerializedName("birthday")
    private String birthday;
    @SerializedName("avatr_url")
    private String avatarUrl;
    @SerializedName("specialty")
    private List<Speciality> specialities;

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public List<Speciality> getSpecialities() {
        return specialities;
    }

    @NonNull
    @Override
    public String toString() {
        String res = "";
        res += name + " / " + lastName + " / " + birthday + " / " + avatarUrl + " / ";
        for (Speciality s : specialities) {
            res += "[ speciality: " + s.toString() + " ]";
        }
        return res;
    }
}
