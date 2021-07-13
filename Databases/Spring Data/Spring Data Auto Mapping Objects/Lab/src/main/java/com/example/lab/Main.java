package com.example.lab;

import com.example.lab.entities.Employee;
import com.example.lab.entities.dtos.EmployeeDto;
import com.example.lab.entities.dtos.ManagerDto;
import com.example.lab.services.EmployeeService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Main implements CommandLineRunner {

    private final EmployeeService employeeService;

    public Main(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        ModelMapper managerMapper = new ModelMapper();

        managerMapper.addMappings(new PropertyMap<Employee, ManagerDto>() {
            @Override
            protected void configure() {
                map().setFirstName(source.getFirstName());
                map().setLastName(source.getLastName());
                map().setEmployeeList(source.getListOfEmployees());
            }
        });

        List<Employee> employees = employeeService.findAllEmployees();

        List<ManagerDto> managerDtoList = employees.stream()
                .map(e -> managerMapper.map(e, ManagerDto.class))
                .collect(Collectors.toList());

        ModelMapper employeeMapper = new ModelMapper();
        employeeMapper.addMappings(new PropertyMap<Employee, EmployeeDto>() {
            @Override
            protected void configure() {
                map().setFirstName(source.getFirstName());
                map().setLastName(source.getLastName());
                map().setSalary(source.getSalary());
            }
        });

        for (ManagerDto managerDto : managerDtoList) {
            System.out.println(managerDto.getFirstName() + " " + managerDto.getLastName() + ": ");
            for (Employee e : managerDto.getEmployeeList()) {
                System.out.println("    " + employeeMapper.map(e, EmployeeDto.class).toString());
            }
        }
    }
}
