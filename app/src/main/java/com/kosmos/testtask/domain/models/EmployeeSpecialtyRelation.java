package com.kosmos.testtask.domain.models;

public class EmployeeSpecialtyRelation {

    private String employeeId;
    private int specialtyId;

    public EmployeeSpecialtyRelation(String employeeId, int specialtyId) {
        this.employeeId = employeeId;
        this.specialtyId = specialtyId;
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
