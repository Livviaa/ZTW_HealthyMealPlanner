package services;

import models.Meal;

import java.util.Collection;

public interface IMealsService {

    // CREATE
    public abstract Integer createMeal(Meal meal);

    // READ
    public abstract Meal getMeal(Integer id);
    public abstract Collection<Meal> getMeal();

    // UPDATE
    public abstract Integer updateMeal(Meal meal);

    // DELETE
    public abstract Integer deleteMeal(Integer id);
}
