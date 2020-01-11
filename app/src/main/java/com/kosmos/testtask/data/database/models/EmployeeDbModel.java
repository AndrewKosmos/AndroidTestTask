package com.kosmos.testtask.data.database.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@Entity(tableName = "employees")
public class EmployeeDbModel {

    @PrimaryKey
    @NotNull
    private String id;
    @ColumnInfo(name = "f_name")
    private String name;
    @ColumnInfo(name = "l_name")
    private String lastName;
    private String birthday;
    @ColumnInfo(name = "avatar_url")
    private String avatarUrl;

    public EmployeeDbModel(String name, String lastName, String birthday, String url) {
        this.name = name;
        this.lastName = lastName;
        this.birthday = birthday;
        this.avatarUrl = url;
        this.id = generateId();
    }

    public String getId() {
        return id;
    }

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

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    private String generateId() {
        String key = name + "/" + lastName + "/" + birthday;
        return UUID.nameUUIDFromBytes(key.getBytes()).toString().replace("-","");
    }

}
