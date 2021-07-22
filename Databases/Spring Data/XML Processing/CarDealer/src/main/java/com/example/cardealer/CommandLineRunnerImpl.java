package com.example.cardealer;

import com.example.cardealer.entities.dtos.*;
import com.example.cardealer.entities.dtos.inputDtos.*;
import com.example.cardealer.services.*;
import com.example.cardealer.utils.XmlParser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@Service
public class CommandLineRunnerImpl implements CommandLineRunner {
    private static final Scanner scanner = new Scanner(System.in);

    //Input paths
    private static final String FILES_INPUT_DIRECTORY_PATH = "src/main/resources/";
    private static final String SUPPLIERS_INPUT_FILE_NAME = "suppliers.xml";
    private static final String PARTS_INPUT_FILE_NAME = "parts.xml";
    private static final String CARS_INPUT_FILE_NAME = "cars.xml";
    private static final String CUSTOMERS_INPUT_FILE_NAME = "customers.xml";

    //Output paths
    private static final String FILES_OUTPUT_DIRECTORY_PATH = "src/main/resources/outputs/";
    private static final String ORDERED_CUSTOMERS_FILE_NAME = "ordered-customers.xml";
    private static final String TOYOTA_CARS_FILE_NAME = "toyota-cars.xml";
    private static final String LOCAL_SUPPLIERS_FILE_NAME = "local-suppliers.xml";
    private static final String CARS_AND_PARTS_FILE_NAME = "cars-and-parts.xml";
    private static final String CUSTOMERS_TOTAL_SALES_FILE_NAME = "customers-total-sales.xml";

    private final XmlParser xmlParser;
    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;
    private final SaleService saleService;

    public CommandLineRunnerImpl(XmlParser xmlParser, SupplierService supplierService,
                                 PartService partService, CarService carService,
                                 CustomerService customerService, SaleService saleService) {
        this.xmlParser = xmlParser;
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();
        System.out.println("Choose query number from 1 to 6");
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
                //ToDo
                break;
        }

    }

    private void queryFive() throws JAXBException, IOException {
        List<CustomerWithSpentMoneyDto> allByCarsBoughtMoreThanOne = customerService.findAllByCarsBoughtMoreThanOne();
        CustomerWithSpentMoneyRootDto customerWithSpentMoneyRootDto = new CustomerWithSpentMoneyRootDto();
        customerWithSpentMoneyRootDto.setCustomers(allByCarsBoughtMoreThanOne);

        xmlParser.writeToFail(
                FILES_OUTPUT_DIRECTORY_PATH + CUSTOMERS_TOTAL_SALES_FILE_NAME,
                customerWithSpentMoneyRootDto);
    }

    private void queryFour() throws JAXBException, IOException {
        List<CustomerWithSpentMoneyDto> allByCarsBoughtMoreThanOne = customerService.findAllByCarsBoughtMoreThanOne();
        CustomerWithSpentMoneyRootDto customerWithSpentMoneyRootDto = new CustomerWithSpentMoneyRootDto();
        customerWithSpentMoneyRootDto.setCustomers(allByCarsBoughtMoreThanOne);

        xmlParser.writeToFail(
                FILES_OUTPUT_DIRECTORY_PATH + CARS_AND_PARTS_FILE_NAME,
                customerWithSpentMoneyRootDto);
    }

    private void queryThree() throws JAXBException, IOException {
        List<SupplierNameAndPartsCountDto> allByImporterIsFalse =
                supplierService.findAllByImporterIsFalse();
        SupplierNameAndPartsCountRootDto supplierNameAndPartsCountRootDto =
                new SupplierNameAndPartsCountRootDto();

        supplierNameAndPartsCountRootDto.setSuppliers(allByImporterIsFalse);

        xmlParser.writeToFail(
                FILES_OUTPUT_DIRECTORY_PATH + LOCAL_SUPPLIERS_FILE_NAME,
                supplierNameAndPartsCountRootDto);
    }

    private void queryTwo() throws JAXBException, IOException {
        List<CarMakeModelAndDistanceDto> allByMakeOrderByModelAscTravelledDistanceDesc =
                carService.findAllByMakeOrderByModelAscTravelledDistanceDesc("Toyota");

        CarMakeModelAndDistanceRootDto carMakeModelAndDistanceRootDto =
                new CarMakeModelAndDistanceRootDto();

        carMakeModelAndDistanceRootDto.setCars(allByMakeOrderByModelAscTravelledDistanceDesc);

        xmlParser.writeToFail(
                FILES_OUTPUT_DIRECTORY_PATH + TOYOTA_CARS_FILE_NAME,
                carMakeModelAndDistanceRootDto);

    }

    private void queryOne() throws JAXBException, IOException {
        List<CustomerWithNameAndAgeDto> allAndOrderByBirthdateAndIsYoungDriver =
                customerService.findAllAndOrderByBirthdateAndIsYoungDriver();

        CustomerWithNameAndAgeRootDto customerWithNameAndAgeRootDto = new CustomerWithNameAndAgeRootDto();
        customerWithNameAndAgeRootDto.setCustomers(allAndOrderByBirthdateAndIsYoungDriver);

        xmlParser.writeToFail(
                FILES_OUTPUT_DIRECTORY_PATH + ORDERED_CUSTOMERS_FILE_NAME,
                customerWithNameAndAgeRootDto);
    }

    private void seedData() throws JAXBException, FileNotFoundException {
        //Seed suppliers
        SupplierInputRootDto supplierInputRootDto = xmlParser.fromFile(
                FILES_INPUT_DIRECTORY_PATH + SUPPLIERS_INPUT_FILE_NAME,
                SupplierInputRootDto.class);
        supplierService.seedData(supplierInputRootDto.getSuppliers());

        //Seed parts
        PartInputRootDto partInputRootDto = xmlParser.fromFile(
                FILES_INPUT_DIRECTORY_PATH + PARTS_INPUT_FILE_NAME,
                PartInputRootDto.class);
        partService.seedData(partInputRootDto.getParts());

        //Seed cars
        CarInputRootDto carInputRootDto = xmlParser.fromFile(
                FILES_INPUT_DIRECTORY_PATH + CARS_INPUT_FILE_NAME,
                CarInputRootDto.class);

        carService.seedData(carInputRootDto.getCars());

        //Seed customers
        CustomerInputRootDto customerInputRootDto = xmlParser.fromFile(
                FILES_INPUT_DIRECTORY_PATH + CUSTOMERS_INPUT_FILE_NAME,
                CustomerInputRootDto.class);
        customerService.seedData(customerInputRootDto.getCustomers());

        //Seed sales
        saleService.seedData();
    }
}
