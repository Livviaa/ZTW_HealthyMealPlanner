package com.ztw.ztw.service;

import com.ztw.ztw.model.Ingredient;
import com.ztw.ztw.model.Meal;
import com.ztw.ztw.model.Recipe;
import com.ztw.ztw.repository.IngredientRepository;
import com.ztw.ztw.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final JdbcTemplate jdbcTemplate;

    // CREATE
    public Recipe addRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    // READ
    public List<Recipe> getRecipes() {
        return recipeRepository.findAll();
    }

    public Recipe getSingleRecipe(Integer id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        return recipe.orElse(null);
    }

    // UPDATE
    @Transactional
    public Recipe editRecipe(Recipe recipe) {
        Optional<Recipe> recipeEditedOptional = recipeRepository.findById(recipe.getRecipeId());
        if(recipeEditedOptional.isPresent()){
            Recipe recipeEdited = recipeEditedOptional.get();
            recipeEdited.setName(recipe.getName());
            recipeEdited.setInstruction(recipe.getInstruction());
            recipeEdited.setImage(recipe.getImage());
            recipeEdited.setSumKcal(recipe.getSumKcal());
            recipeEdited.setSumProtein(recipe.getSumProtein());
            recipeEdited.setSumFats(recipe.getSumFats());
            recipeEdited.setSumCarbohydrates(recipe.getSumCarbohydrates());
            return recipeEdited;
        }else{
            return null;
        }
    }

    // DELETE
    public boolean deleteRecipe(Integer id) {
        Optional<Recipe> deletedRecipe = recipeRepository.findById(id);
        if (deletedRecipe.isPresent()) {
            recipeRepository.delete(deletedRecipe.get());
            return true;
        } else {
            return false;
        }
    }

    // EXTRA
    public boolean addRecipeToMeal(Integer recipeId, Integer ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        Optional<Ingredient> ingredientOptional = ingredientRepository.findById(ingredientId);

        if (recipeOptional.isPresent() && ingredientOptional.isPresent()) {
            jdbcTemplate.update("INSERT INTO ingredient_recipes VALUES (?, ?)", recipeId, ingredientId);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteIngredientFromRecipe(Integer recipeId, Integer ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        Optional<Ingredient> ingredientOptional = ingredientRepository.findById(ingredientId);

        if (recipeOptional.isPresent() && ingredientOptional.isPresent()) {
            jdbcTemplate.update("DELETE FROM ingredient_recipes WHERE ingredient_id = ? AND recipe_id = ?", ingredientId, recipeId);
            return true;
        } else {
            return false;
        }
    }
}
