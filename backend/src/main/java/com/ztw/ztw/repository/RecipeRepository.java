package com.ztw.ztw.repository;

import com.ztw.ztw.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    @Query("select r from Recipe r where name = :name")
    List<Recipe> findAllByName(String name);
}
