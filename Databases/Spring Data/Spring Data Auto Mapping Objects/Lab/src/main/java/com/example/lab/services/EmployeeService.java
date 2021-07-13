package com.example.lab.services;

import com.example.lab.entities.Employee;

import java.util.List;

public interface EmployeeService {

    Employee findById(Integer id);

    void save(Employee e);

    List<Employee> findAllEmployees();
}
