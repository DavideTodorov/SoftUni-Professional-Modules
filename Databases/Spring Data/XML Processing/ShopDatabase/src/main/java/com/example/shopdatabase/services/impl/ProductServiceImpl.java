package com.example.shopdatabase.services.impl;

import com.example.shopdatabase.entities.Product;
import com.example.shopdatabase.entities.dtos.ProductInputDto;
import com.example.shopdatabase.entities.dtos.ProductWithSellerDto;
import com.example.shopdatabase.repositories.ProductRepository;
import com.example.shopdatabase.services.CategoryService;
import com.example.shopdatabase.services.ProductService;
import com.example.shopdatabase.services.UserService;
import com.example.shopdatabase.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    private final UserService userService;
    private final CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper,
                              ValidationUtil validationUtil, UserService userService,
                              CategoryService categoryService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedData(List<ProductInputDto> products) {
        if (productRepository.count() > 0) {
            return;
        }

        List<Product> collect = products
                .stream()
                .map(productInputDto -> {
                    Product product = modelMapper.map(productInputDto, Product.class);

                    product.setSeller(userService.getRandomUser());
                    if (product.getPrice().compareTo(BigDecimal.valueOf(650L)) > 0) {
                        product.setBuyer(userService.getRandomUser());
                    }

                    product.setCategories(categoryService.getRandomCategories());
                    return product;
                })
                .collect(Collectors.toList());

        productRepository.saveAll(collect);
    }

    @Override
    public List<ProductWithSellerDto> findAllByPriceBetweenAndBuyerIsNull(BigDecimal lower, BigDecimal upper) {
        return productRepository.findAllByPriceBetweenAndBuyerIsNull(lower, upper)
                .stream()
                .map(product -> {
                    ProductWithSellerDto map = modelMapper.map(product, ProductWithSellerDto.class);
                    map.setSeller(product.getSeller().getFirstName() + " " + product.getSeller().getLastName());
                    return map;
                })
                .collect(Collectors.toList());
    }
}
