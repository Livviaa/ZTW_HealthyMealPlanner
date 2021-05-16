package services;

import models.Ingredient;

import java.util.Collection;

public interface IIngredientService {

    // CREATE
    public abstract Integer createIngredient(Ingredient ingredient);

    // READ
    public abstract Ingredient getIngredient(Integer id);
    public abstract Collection<Ingredient> getIngredient();

    // UPDATE
    public abstract Integer updateIngredient(Ingredient ingredient);

    // DELETE
    public abstract Integer deleteIngredient(Integer id);
}
