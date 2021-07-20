package com.example.cardealer.services;

import com.example.cardealer.entities.Customer;
import com.example.cardealer.entities.dtos.CustomerFullInfoDto;
import com.example.cardealer.entities.dtos.CustomerInputDto;
import com.example.cardealer.entities.dtos.CustomerTotalSalesDto;
import com.example.cardealer.repositories.CustomerRepository;
import com.example.cardealer.utils.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private static final String CUSTOMER_FILE_PATH = "src/main/resources/customers.json";

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;

    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper,
                               ValidationUtil validationUtil, Gson gson) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
    }

    @Override
    public void seedData() throws IOException {
        if (customerRepository.count() > 0) {
            return;
        }

        String s = Files.readString(Path.of(CUSTOMER_FILE_PATH));

        List<CustomerInputDto> customerInputDtos = Arrays
                .stream(gson.fromJson(s, CustomerInputDto[].class))
                .collect(Collectors.toList());

        List<CustomerInputDto> customerInputDtoStream = customerInputDtos
                .stream()
                .filter(validationUtil::isValid).collect(Collectors.toList());

        List<Customer> customers = new ArrayList<>();

        for (CustomerInputDto customerInputDto : customerInputDtoStream) {
            Customer customer = modelMapper.map(customerInputDto, Customer.class);

            LocalDateTime parse = LocalDateTime.parse(customerInputDto.getBirthDate());

            customer.setBirthDate(parse);
            customers.add(customer);
        }

        customerRepository.saveAll(customers);
    }

    @Override
    public Customer getRandomCustomer() {
        return customerRepository.findById(
                ThreadLocalRandom.current().nextLong(1, customerRepository.count() + 1)
        )
                .orElse(null);
    }

    @Override
    public String findAllAndSortByBirthDate() {
        List<Customer> collect = new ArrayList<>(customerRepository.findAll());

        collect
                .stream()
                .sorted((c1, c2) -> {
                    int i = c1.getBirthDate().compareTo(c2.getBirthDate());

                    if (i == 0) {
                        if (c1.isYoungDriver()) {
                            i = -1;
                        } else {
                            i = 1;
                        }
                    }

                    return i;
                });


        return gson.toJson(collect
                .stream()
                .map(c -> modelMapper.map(c, CustomerFullInfoDto.class))
                .collect(Collectors.toList()));
    }

    @Override
    public List<CustomerTotalSalesDto> getCustomerWithTotalSales() {
        List<String> allByCarsBoughtMoreThanOne = customerRepository.findAllByCarsBoughtMoreThanOne();

        return allByCarsBoughtMoreThanOne
                .stream()
                .map(c -> c.split(","))
                .map(c -> {
                    return new CustomerTotalSalesDto(c[0], Integer.parseInt(c[1]), Double.parseDouble(c[2]));
                })
                .collect(Collectors.toList());
    }
}
