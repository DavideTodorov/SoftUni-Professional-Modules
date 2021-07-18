package com.example.exercise.services;

import com.example.exercise.entities.Category;
import com.example.exercise.entities.dtos.CategoriesInfoDto;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface CategoryService {

    void seedData() throws IOException;

    List<Category> findRandomCategories();

    List<CategoriesInfoDto> findAvgInfoForCategories();
}
