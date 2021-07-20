package com.example.cardealer;

import com.example.cardealer.entities.dtos.CarFullInfoDto;
import com.example.cardealer.entities.dtos.CarFullinfoWithPartsDto;
import com.example.cardealer.entities.dtos.CustomerTotalSalesDto;
import com.example.cardealer.entities.dtos.SupplierIdNameAndPartsCountDto;
import com.example.cardealer.services.*;
import com.google.gson.Gson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@Service
public class CommandLineRunnerImpl implements CommandLineRunner {

    private static final Scanner scanner = new Scanner(System.in);

    private final Gson gson;
    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;
    private final SaleService saleService;

    public CommandLineRunnerImpl(Gson gson, SupplierService supplierService, PartService partService,
                                 CarService carService, CustomerService customerService,
                                 SaleService saleService) {
        this.gson = gson;
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
    }


    @Override
    public void run(String... args) throws Exception {
        seedData();

        System.out.println("Enter query from 1 to 6: ");
        int queryNumber = Integer.parseInt(scanner.nextLine());

        switch (queryNumber) {
            case 1:
                queryOne();
                break;
            case 2:
                queryTwo();
                break;
            case 3:
                queryThree();
                break;
            case 4:
                queryFour();
                break;
            case 5:
                queryFive();
                break;
            case 6:
                querySix(); //TODO
                break;
        }

    }

    private void querySix() {

    }

    private void queryFive() {
        List<CustomerTotalSalesDto> customerWithTotalSales = customerService.getCustomerWithTotalSales();

        System.out.println(gson.toJson(customerWithTotalSales));
    }

    private void queryFour() {
        List<CarFullinfoWithPartsDto> allCarsWithParts = carService.getAllCarsWithParts();

        System.out.println(gson.toJson(allCarsWithParts));
    }

    private void queryThree() {
        List<SupplierIdNameAndPartsCountDto> suppliersThatArentImporters =
                supplierService.getSuppliersThatArentImporters();

        System.out.println(gson.toJson(suppliersThatArentImporters));
    }

    private void queryTwo() {
        System.out.println("Enter make: ");
        List<CarFullInfoDto> carsByMakeDtos = carService.getCarsByMake(scanner.nextLine());

        String s = gson.toJson(carsByMakeDtos);
        System.out.println(s);

    }

    private void queryOne() {
        System.out.println(customerService
                .findAllAndSortByBirthDate());
    }

    private void seedData() throws IOException {
        supplierService.seedData();
        partService.seedData();
        carService.seedData();
        customerService.seedData();
        saleService.seedData();
    }
}
