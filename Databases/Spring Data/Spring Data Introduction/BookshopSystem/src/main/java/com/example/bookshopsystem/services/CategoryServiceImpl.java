package com.example.bookshopsystem.services;

import com.example.bookshopsystem.entities.Category;
import com.example.bookshopsystem.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final String CATEGORY_FILE_PATH = "src/main/resources/dbData/categories.txt";

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedCategories() {
        if (categoryRepository.count() > 0){
            return;
        }

        try {
            Files.readAllLines(Path.of
                    (CATEGORY_FILE_PATH))
                    .forEach(authorEntry -> {
                        String[] tokens = authorEntry.split("\\s+\\n+");
                        String categoryName = tokens[0];

                        if (!categoryName.isEmpty()) {
                            Category category = new Category(categoryName);
                            categoryRepository.save(category);
                        }
                    });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
