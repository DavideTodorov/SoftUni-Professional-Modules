package com.example.shopdatabase;

import com.example.shopdatabase.entities.dtos.*;
import com.example.shopdatabase.services.CategoryService;
import com.example.shopdatabase.services.ProductService;
import com.example.shopdatabase.services.UserService;
import com.example.shopdatabase.utils.XmlParser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

@Service
public class CommandLineRunnerImpl implements CommandLineRunner {
    private static final String USERS_FILE_INPUT = "src/main/resources/users.xml";
    private static final String CATEGORIES_FILE_INPUT = "src/main/resources/categories.xml";
    private static final String PRODUCTS_FILE_INPUT = "src/main/resources/products.xml";

    private static final String OUTPUT_FOLDER_PATH = "src/main/resources/output/";
    private static final String PRODUCTS_IN_RANGE_FILE_NAME = "products-in-range.xml";
    private static final String USERS_SOLD_PRODUCTS_FILE_NAME = "users-sold-products.xml";
    private static final String CATEGORIES_BY_PRODUCTS_FILE_NAME = "categories-by-products.xml";


    private static final Scanner scanner = new Scanner(System.in);

    private final UserService userService;
    private final CategoryService categoryService;
    private final ProductService productService;
    private final XmlParser xmlParser;

    public CommandLineRunnerImpl(UserService userService, CategoryService categoryService,
                                 ProductService productService, XmlParser xmlParser) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
        this.xmlParser = xmlParser;
    }

    @Override
    public void run(String... args) throws Exception {
        seedDatabase();

        System.out.println("Choose query number from 1 to 4");
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
                //ToDo
                break;
        }
    }

    private void queryThree() throws JAXBException, IOException {
        List<CategoryAvgInfoDto> avgInfoAboutCategories =
                categoryService.getAvgInfoAboutCategories();
        CategoryAvgInfoRootDto categoryAvgInfoRootDto = new CategoryAvgInfoRootDto();
        categoryAvgInfoRootDto.setCategories(avgInfoAboutCategories);

        xmlParser.writeToFail(OUTPUT_FOLDER_PATH + CATEGORIES_BY_PRODUCTS_FILE_NAME, categoryAvgInfoRootDto);
    }

    private void queryTwo() throws JAXBException, IOException {
        List<UserWithSoldProductsDto> allByCountOfSalesMoreThanOne =
                userService.findAllByCountOfSalesMoreThanOne();

        UserWithSoldProductsRootDto userWithSoldProductsRootDto = new UserWithSoldProductsRootDto();
        userWithSoldProductsRootDto.setUserWithSoldProducts(allByCountOfSalesMoreThanOne);

        xmlParser.writeToFail
                (OUTPUT_FOLDER_PATH + USERS_SOLD_PRODUCTS_FILE_NAME, userWithSoldProductsRootDto);
    }

    private void queryOne() throws JAXBException, IOException {
        List<ProductWithSellerDto> allByPriceBetweenAndBuyerIsNull = productService
                .findAllByPriceBetweenAndBuyerIsNull(BigDecimal.valueOf(500L), BigDecimal.valueOf(1000L));

        ProductWithSellerRootDto productWithSellerRootDto = new ProductWithSellerRootDto();
        productWithSellerRootDto.setProducts(allByPriceBetweenAndBuyerIsNull);

        xmlParser.writeToFail(OUTPUT_FOLDER_PATH + PRODUCTS_IN_RANGE_FILE_NAME, productWithSellerRootDto);
    }

    private void seedDatabase() throws JAXBException, FileNotFoundException {
        UserInputRootDto userInputRootDto = xmlParser.fromFile(USERS_FILE_INPUT, UserInputRootDto.class);
        userService.seedData(userInputRootDto.getUsers());

        CategoryRootDto categoryRootDto = xmlParser.fromFile(CATEGORIES_FILE_INPUT, CategoryRootDto.class);
        categoryService.seedData(categoryRootDto.getCategories());

        ProductRootDto productRootDto = xmlParser.fromFile(PRODUCTS_FILE_INPUT, ProductRootDto.class);
        productService.seedData(productRootDto.getProducts());
    }
}
