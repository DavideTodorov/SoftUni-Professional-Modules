package com.example.cardealer.services.impl;

import com.example.cardealer.entities.Customer;
import com.example.cardealer.entities.dtos.CustomerWithNameAndAgeDto;
import com.example.cardealer.entities.dtos.CustomerWithSpentMoneyDto;
import com.example.cardealer.entities.dtos.inputDtos.CustomerInputDto;
import com.example.cardealer.repositories.CustomerRepository;
import com.example.cardealer.services.CustomerService;
import com.example.cardealer.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper,
                               ValidationUtil validationUtil) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedData(List<CustomerInputDto> customers) {
        if (customerRepository.count() > 0) {
            return;
        }

        List<Customer> collect = customers
                .stream()
                .filter(validationUtil::isValid)
                .map(customerInputDto -> {
                    Customer customer = modelMapper.map(customerInputDto, Customer.class);
                    LocalDateTime parse = LocalDateTime.parse(customerInputDto.getBirthDate());
                    customer.setBirthDate(parse);
                    return customer;
                })
                .collect(Collectors.toList());

        customerRepository.saveAll(collect);
    }

    @Override
    public Customer getRandomCustomer() {
        return customerRepository
                .findById(
                        ThreadLocalRandom
                                .current()
                                .nextLong(1, customerRepository.count() + 1))
                .orElse(null);
    }

    @Override
    public List<CustomerWithNameAndAgeDto> findAllAndOrderByBirthdateAndIsYoungDriver() {
        return customerRepository
                .findAllAndOrderByBirthdateAndIsYoungDriver()
                .stream()
                .map(customer -> modelMapper.map(customer, CustomerWithNameAndAgeDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerWithSpentMoneyDto> findAllByCarsBoughtMoreThanOne() {
        List<String> allByCarsBoughtMoreThanOne = customerRepository.findAllByCarsBoughtMoreThanOne();

        List<CustomerWithSpentMoneyDto> collect = allByCarsBoughtMoreThanOne
                .stream()
                .map(c -> c.split(","))
                .map(c -> new CustomerWithSpentMoneyDto(c[0], Integer.parseInt(c[1]), Double.parseDouble(c[2])))
                .collect(Collectors.toList());

        return collect;
    }
}
