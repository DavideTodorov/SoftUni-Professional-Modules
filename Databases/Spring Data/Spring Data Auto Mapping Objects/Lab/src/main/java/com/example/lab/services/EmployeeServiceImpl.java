package com.example.lab.services;

import com.example.lab.entities.Employee;
import com.example.lab.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee findById(Integer id) {
        return employeeRepository.getById(id);
    }

    @Override
    public void save(Employee e) {
        employeeRepository.save(e);
    }

    @Override
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }
}
