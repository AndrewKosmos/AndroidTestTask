package com.kosmos.testtask.data.database.models;

import java.util.List;

public class ProfileModel {

    private EmployeeDbModel employeeDbModel;
    private List<SpecialityDbModel> specialityDbModels;

    public ProfileModel(EmployeeDbModel employeeDbModel, List<SpecialityDbModel> specialityDbModels) {
        this.employeeDbModel = employeeDbModel;
        this.specialityDbModels = specialityDbModels;
    }

    public EmployeeDbModel getEmployeeDbModel() {
        return employeeDbModel;
    }

    public void setEmployeeDbModel(EmployeeDbModel employeeDbModel) {
        this.employeeDbModel = employeeDbModel;
    }

    public List<SpecialityDbModel> getSpecialityDbModels() {
        return specialityDbModels;
    }

    public void setSpecialityDbModels(List<SpecialityDbModel> specialityDbModels) {
        this.specialityDbModels = specialityDbModels;
    }
}
