package services;

import models.MealsAndRecipes;

import java.util.Collection;

public interface IMealsAndRecipesService {

    // CREATE
    public abstract Integer createMealsAndRecipes(MealsAndRecipes mealsAndRecipes);

    // READ
    public abstract Collection<MealsAndRecipes> getMealsAndRecipes();

    // DELETE
    public abstract Integer deleteMealsAndRecipes(Integer mealId, Integer recipeId);
}
