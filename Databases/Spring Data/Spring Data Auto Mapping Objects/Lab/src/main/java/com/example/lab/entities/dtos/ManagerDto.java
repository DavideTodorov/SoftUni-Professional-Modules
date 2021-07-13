package com.example.lab.entities.dtos;

import com.example.lab.entities.Employee;

import java.util.List;

public class ManagerDto {
    private String firstName;
    private String lastName;
    private List<Employee> listOfEmployees;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Employee> getEmployeeList() {
        return listOfEmployees;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.listOfEmployees = employeeList;
    }
}
