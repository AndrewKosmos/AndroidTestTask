package com.kosmos.testtask.domain.models;

import java.util.UUID;

public class Employee {

    private String id;
    private String name;
    private String lastName;
    private String birthday;
    private String avatarUrl;

    public Employee() {}

    public Employee(String id, String name, String lastName, String date, String url) {
        this.name = name;
        this.lastName = lastName;
        this.birthday = date;
        this.avatarUrl = url;
        if (id == null || id.length() <= 0) {
            this.id = generateId();
        }
        else {
            this.id = id;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    private String generateId() {
        String key = name + "/" + lastName + "/" + birthday;
        return UUID.nameUUIDFromBytes(key.getBytes()).toString().replace("-","");
    }
}
