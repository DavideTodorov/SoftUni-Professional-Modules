package com.example.shopdatabase.services;

import com.example.shopdatabase.entities.Category;
import com.example.shopdatabase.entities.dtos.CategoryAvgInfoDto;
import com.example.shopdatabase.entities.dtos.CategoryInputDto;

import java.util.List;

public interface CategoryService {
    void seedData(List<CategoryInputDto> categories);

    List<Category> getRandomCategories();

    List<CategoryAvgInfoDto> getAvgInfoAboutCategories();
}
