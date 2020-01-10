package com.kosmos.testtask.domain.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WebResponse {

    @SerializedName("response")
    private List<Person> personList;

    public List<Person> getPersonList() {
        return personList;
    }

}
