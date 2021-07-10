package com.example.advquerying.services;

import com.example.advquerying.entities.Ingredient;

import java.util.Collection;
import java.util.List;

public interface IngredientService {

    List<Ingredient> findAllByNameStartingWith(String name);

    List<Ingredient> findAllByNameInOrderByPrice(List<String> name);

    int deleteIngredientByName(String name);

    int updateIngredientsPriceBy10Percent();

    int updateIngredientsPriceBy10PercentWithName(String name);
}
