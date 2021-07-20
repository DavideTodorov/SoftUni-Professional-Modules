package com.example.cardealer.services;

import com.example.cardealer.entities.Customer;
import com.example.cardealer.entities.dtos.CustomerTotalSalesDto;

import java.io.IOException;
import java.util.List;

public interface CustomerService {

    void seedData() throws IOException;

    Customer getRandomCustomer();

    String findAllAndSortByBirthDate();

    List<CustomerTotalSalesDto> getCustomerWithTotalSales();
}
