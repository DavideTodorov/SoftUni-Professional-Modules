package com.example.lab.entities;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column
    private BigDecimal salary;

    @Column
    private Date birthday;

    @Column
    private String address;

    @Column(name = "is_on_holiday")
    private boolean isOnHoliday;

    @ManyToOne
    private Employee manager;

    @Column
    @OneToMany(mappedBy = "manager",fetch = FetchType.EAGER)
    private List<Employee> listOfEmployees;

    public int getId() {
        return id;
    }

    public Employee() {
    }

    public Employee(String firstName, String lastName, BigDecimal salary, Date birthday, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.birthday = birthday;
        this.address = address;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isOnHoliday() {
        return isOnHoliday;
    }

    public void setOnHoliday(boolean onHoliday) {
        isOnHoliday = onHoliday;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public List<Employee> getListOfEmployees() {
        return listOfEmployees;
    }

    public void setListOfEmployees(List<Employee> listOfEmployees) {
        this.listOfEmployees = listOfEmployees;
    }
}
