package com.example.exercise.services;

import com.example.exercise.entities.Product;
import com.example.exercise.entities.dtos.ProductInputDto;
import com.example.exercise.entities.dtos.ProductNameAndPriceDto;
import com.example.exercise.repositories.ProductRepository;
import com.example.exercise.utils.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private static final String PRODUCTS_FILE_PATH = "src/main/resources/products.json";

    private final ProductRepository productRepository;

    private final UserService userService;
    private final CategoryService categoryService;

    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public ProductServiceImpl(ProductRepository productRepository, UserService userService,
                              CategoryService categoryService, Gson gson,
                              ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.categoryService = categoryService;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedData() throws IOException {
        if (productRepository.count() > 0) {
            return;
        }

        ProductInputDto[] productInputDtos = gson.fromJson
                (Files.readString(Path.of(PRODUCTS_FILE_PATH)), ProductInputDto[].class);

        Arrays.stream(productInputDtos)
                .filter(validationUtil::isValid)
                .map(productInputDto -> {
                    Product product = modelMapper.map(productInputDto, Product.class);

                    if (product.getPrice().compareTo(new BigDecimal(750)) > 0) {
                        product.setBuyer(userService.findRandom());
                    }

                    product.setSeller(userService.findRandom());
                    product.setCategories(categoryService.findRandomCategories());
                    return product;
                })
                .forEach(productRepository::save);

    }

    @Override
    public List<ProductNameAndPriceDto> findAllWithPriceInRange(BigDecimal lower, BigDecimal upper) {
        List<Product> allByPriceBetween = productRepository.findAllByPriceBetween(lower, upper);

        return allByPriceBetween.stream()
                .map(p -> {
                    ProductNameAndPriceDto map = modelMapper.map(p, ProductNameAndPriceDto.class);
                    map.setSeller(String.format("%s %s",
                            p.getSeller().getFirstName(), p.getSeller().getLastName()));

                    return map;
                })
                .collect(Collectors.toList());
    }
}
