package services;

import models.RecipesAndIngredients;

import java.util.Collection;

public interface IRecipesAndIngredientsService {

    // CREATE
    public abstract Integer createRecipesAndIngredient(RecipesAndIngredients recipesAndIngredients);

    // READ
    public abstract Collection<RecipesAndIngredients> getRecipesAndIngredient();

    // DELETE
    public abstract Integer deleteRecipesAndIngredient(Integer RecipeId, Integer IngredientId);
}
