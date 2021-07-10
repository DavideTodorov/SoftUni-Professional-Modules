package com.example.advquerying.services;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.repositories.IngredientRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<Ingredient> findAllByNameStartingWith(String name) {
        return ingredientRepository.findAllByNameStartingWith(name);
    }

    @Override
    public List<Ingredient> findAllByNameInOrderByPrice(List<String> name) {
        return ingredientRepository.findAllByNameInOrderByPrice(name);
    }

    @Override
    @Transactional
    public int deleteIngredientByName(String name) {
        return ingredientRepository.deleteIngredientByName(name);
    }

    @Override
    @Transactional
    public int updateIngredientsPriceBy10Percent() {
        return ingredientRepository.updateIngredientsPriceBy10Percent();
    }

    @Override
    @Transactional
    public int updateIngredientsPriceBy10PercentWithName(String name) {
        return ingredientRepository.updateIngredientsPriceBy10PercentWithName(name);
    }
}
