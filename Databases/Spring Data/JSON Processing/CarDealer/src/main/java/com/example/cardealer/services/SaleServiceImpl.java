package com.example.cardealer.services;

import com.example.cardealer.entities.Car;
import com.example.cardealer.entities.Customer;
import com.example.cardealer.entities.Sale;
import com.example.cardealer.repositories.SaleRepository;
import com.example.cardealer.utils.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class SaleServiceImpl implements SaleService {

    private final CustomerService customerService;
    private final CarService carService;
    private final SaleRepository saleRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;

    public SaleServiceImpl(CustomerService customerService, CarService carService,
                           SaleRepository saleRepository, ModelMapper modelMapper,
                           ValidationUtil validationUtil, Gson gson) {
        this.customerService = customerService;
        this.carService = carService;
        this.saleRepository = saleRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
    }

    @Override
    public void seedData() {
        if (saleRepository.count() > 0){
            return;
        }



        List<Integer> discounts = List.of(0, 5, 10, 15, 20, 30, 40, 50);

        for (int i = 0; i < 50; i++) {
            Car randomCar = carService.getRandomCar();
            Customer randomCustomer = customerService.getRandomCustomer();
            Integer discount = discounts.get(ThreadLocalRandom.current().nextInt(0, discounts.size()));
            Sale sale = new Sale(randomCar, randomCustomer, (1.0 * discount / 100));
            saleRepository.save(sale);
        }
    }
}
