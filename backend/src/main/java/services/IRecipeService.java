package services;

import models.Recipe;

import java.util.Collection;

public interface IRecipeService {

    // CREATE
    public abstract Integer createRecipe(Recipe recipe);

    // READ
    public abstract Recipe getRecipe(Integer id);
    public abstract Collection<Recipe> getRecipe();

    // UPDATE
    public abstract Integer updateRecipe(Recipe recipe);

    // DELETE
    public abstract Integer deleteRecipe(Integer id);
}
