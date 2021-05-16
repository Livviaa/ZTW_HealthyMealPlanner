package services;

import models.Recipe_Ingredient;

import java.util.Collection;

public interface IRecipes_IngredientsService {

    // CREATE
    public abstract Integer createRecipe_Ingredient(Recipe_Ingredient recipe_ingredient);

    // READ
    public abstract Collection<Recipe_Ingredient> getRecipe_Ingredient();

    // DELETE
    public abstract Integer deleteRecipe_Ingredient(Integer Przepis_id, Integer Produkt_id);
}
