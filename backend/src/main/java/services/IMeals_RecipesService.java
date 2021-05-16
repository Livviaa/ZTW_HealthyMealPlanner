package services;

import models.Meal_Recipe;

import java.util.Collection;

public interface IMeals_RecipesService {

    // CREATE
    public abstract Integer createMeal_Recipe(Meal_Recipe meal_recipe);

    // READ
    public abstract Collection<Meal_Recipe> getMeal_Recipe();

    // DELETE
    public abstract Integer deleteMeal_Recipe(Integer Posilek_id, Integer Przepis_id);
}
