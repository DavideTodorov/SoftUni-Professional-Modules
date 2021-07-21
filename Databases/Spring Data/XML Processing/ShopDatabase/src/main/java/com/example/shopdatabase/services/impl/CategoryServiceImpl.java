package com.example.shopdatabase.services.impl;

import com.example.shopdatabase.entities.Category;
import com.example.shopdatabase.entities.dtos.CategoryAvgInfoDto;
import com.example.shopdatabase.entities.dtos.CategoryInputDto;
import com.example.shopdatabase.repositories.CategoryRepository;
import com.example.shopdatabase.services.CategoryService;
import com.example.shopdatabase.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper,
                               ValidationUtil validationUtil) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedData(List<CategoryInputDto> categories) {
        if (categoryRepository.count() > 0) {
            return;
        }

        List<Category> collect = categories
                .stream()
                .map(categoryInputDto -> modelMapper.map(categoryInputDto, Category.class))
                .filter(validationUtil::isValid)
                .collect(Collectors.toList());

        categoryRepository.saveAll(collect);
    }

    @Override
    public List<Category> getRandomCategories() {
        List<Category> categories = new ArrayList<>();
        long count = categoryRepository.count();

        for (int i = 0; i < 2; i++) {
            categories.add(categoryRepository.findById(
                    ThreadLocalRandom
                            .current().nextLong(1, count + 1))
                    .orElse(null)
            );
        }

        return categories;
    }

    @Override
    public List<CategoryAvgInfoDto> getAvgInfoAboutCategories() {

        List<Category> allCategories = categoryRepository.findAll();

        List<CategoryAvgInfoDto> categoriesInfoDtos = new ArrayList<>();

        for (Category category : allCategories) {
            CategoryAvgInfoDto categoriesInfoDto = new CategoryAvgInfoDto();

            categoriesInfoDto.setName(category.getName());

            categoriesInfoDto.setAveragePrice(category
                    .getProducts()
                    .stream()
                    .mapToDouble(p -> p.getPrice().doubleValue())
                    .average().getAsDouble());

            categoriesInfoDto.setTotalRevenue(
                    category
                            .getProducts()
                            .stream()
                            .mapToDouble(p -> p.getPrice().doubleValue())
                            .sum());

            categoriesInfoDto.setCount(category.getProducts().size());

            categoriesInfoDtos.add(categoriesInfoDto);
        }

        categoriesInfoDtos.sort((c1, c2) ->
                Integer.compare(c2.getCount(), c1.getCount()));

        return categoriesInfoDtos;
    }
}
