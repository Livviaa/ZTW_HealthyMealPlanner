package com.ztw.ztw.service;

import com.ztw.ztw.model.Meal;
import com.ztw.ztw.model.Recipe;
import com.ztw.ztw.repository.MealRepository;
import com.ztw.ztw.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MealService {

    private final MealRepository mealRepository;
    private final RecipeRepository recipeRepository;
    private final JdbcTemplate jdbcTemplate;

    // CREATE
    public Meal addMeal(Meal meal) {
        return mealRepository.save(meal);
    }

    // READ
    public List<Meal> getMeals() {
        return mealRepository.findAll();
    }

    public Meal getSingleMeal(Integer id) {
        Optional<Meal> meal = mealRepository.findById(id);
        return meal.orElse(null);
    }

    // UPDATE
    @Transactional
    public Meal editMeal(Meal meal) {
        Optional<Meal> mealEditedOptional = mealRepository.findById(meal.getMealId());
        if (mealEditedOptional.isPresent()) {
            Meal mealEdited = mealEditedOptional.get();
            return mealEdited;
        } else {
            return null;
        }
    }

    // DELETE
    public boolean deleteMeal(Integer id) {
        Optional<Meal> deletedMeal = mealRepository.findById(id);
        if (deletedMeal.isPresent()) {
            mealRepository.delete(deletedMeal.get());
            return true;
        } else {
            return false;
        }
    }

    // EXTRA
    public boolean addRecipeToMeal(Integer mealId, Integer recipeId) {
        Optional<Meal> mealOptional = mealRepository.findById(mealId);
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (mealOptional.isPresent() && recipeOptional.isPresent()) {
            jdbcTemplate.update("INSERT INTO recipe_meals VALUES (?, ?)", mealId, recipeId);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteRecipeFromMeal(Integer mealId, Integer recipeId) {
        Optional<Meal> mealOptional = mealRepository.findById(mealId);
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (mealOptional.isPresent() && recipeOptional.isPresent()) {
            jdbcTemplate.update("DELETE FROM recipe_meals WHERE meal_id = ? AND recipe_id = ?", mealId, recipeId);
            return true;
        } else {
            return false;
        }
    }
}
