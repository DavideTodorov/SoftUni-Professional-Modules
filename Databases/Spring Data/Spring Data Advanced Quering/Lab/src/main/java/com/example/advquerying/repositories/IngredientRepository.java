package com.example.advquerying.repositories;

import com.example.advquerying.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    List<Ingredient> findAllByNameStartingWith(String name);

    List<Ingredient> findAllByNameInOrderByPrice(List<String> name);

    @Modifying
    int deleteIngredientByName(String name);

    @Modifying
    @Query("update Ingredient i set i.price = i.price * 1.1")
    int updateIngredientsPriceBy10Percent();

    @Modifying
    @Query("update Ingredient i set i.price = i.price * 1.1 where i.name = :name")
    int updateIngredientsPriceBy10PercentWithName(String name);
}
