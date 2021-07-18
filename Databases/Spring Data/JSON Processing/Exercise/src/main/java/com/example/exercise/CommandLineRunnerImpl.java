package com.example.exercise;

import com.example.exercise.entities.dtos.CategoriesInfoDto;
import com.example.exercise.entities.dtos.ProductNameAndPriceDto;
import com.example.exercise.entities.dtos.UserNamesAndProductsDto;
import com.example.exercise.services.CategoryService;
import com.example.exercise.services.ProductService;
import com.example.exercise.services.UserService;
import com.google.gson.Gson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

@Service
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final Scanner scanner = new Scanner(System.in);

    private final CategoryService categoryService;
    private final UserService userService;
    private final ProductService productService;

    private final Gson gson;

    public CommandLineRunnerImpl(CategoryService categoryService, UserService userService, ProductService productService, Gson gson) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.productService = productService;
        this.gson = gson;
    }

    @Override
    public void run(String... args) throws Exception {
        seedDatabase();


        System.out.println("Enter query number from 1 to 4");
        int queryNum = Integer.parseInt(scanner.nextLine());


        switch (queryNum) {
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
                //TODO queryFour();
                break;
        }
    }

    private void queryFour() {
    }

    private void queryThree() {
        List<CategoriesInfoDto> avgInfoForCategories = categoryService.findAvgInfoForCategories();

        String s = gson.toJson(avgInfoForCategories);
        System.out.println(s);
    }

    private void queryTwo() {
        userService.findAllWhoHaveSales()
                .stream()
                .map(gson::toJson)
                .forEach(System.out::println);
    }

    private void queryOne() {
        System.out.println("Enter lower bound:");
        BigDecimal lower = new BigDecimal(scanner.nextLine());

        System.out.println("Enter upper bound:");
        BigDecimal upper = new BigDecimal(scanner.nextLine());

        List<ProductNameAndPriceDto> allWithPriceInRange =
                productService.findAllWithPriceInRange(lower, upper);

        for (ProductNameAndPriceDto productNameAndPriceDto : allWithPriceInRange) {
            System.out.println(gson.toJson(productNameAndPriceDto));
        }
    }

    private void seedDatabase() throws IOException {
        categoryService.seedData();
        userService.seedData();
        productService.seedData();
    }
}
