package com.ztw.ztw.repository;

import com.ztw.ztw.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

    @Query("select i from Ingredient i where name = :name")
    List<Ingredient> findAllByName(String name);
}
