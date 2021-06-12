package com.ztw.ztw.service;

import com.ztw.ztw.model.DailyMenu;
import com.ztw.ztw.model.Ingredient;
import com.ztw.ztw.model.Meal;
import com.ztw.ztw.model.Recipe;
import com.ztw.ztw.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MegaService {
    private final UserRepository userRepository;
    private final DailyMenuRepository dailyMenuRepository;
    private final MealRepository mealRepository;
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final JdbcTemplate jdbcTemplate;
    private final RecipeService recipeService;


    public void updateMacroElementsAll() {
        updateMacroElementsRecipes();
        updateMacroElementsMeals();
        updateMacroElementsDailyMenus();
    }

    private void updateMacroElementsDailyMenus() {
        ArrayList<DailyMenu> dailyMenus = (ArrayList<DailyMenu>) dailyMenuRepository.findAll();

        for (DailyMenu dailyMenu : dailyMenus) {
            double bufSumKcal = 0;
            double bufSumProtein = 0;
            double bufSumFats = 0;
            double bufSumCarbohydrates = 0;

            for (Meal meal : dailyMenu.getMeals()) {
                bufSumKcal += meal.getSumKcal();
                bufSumProtein += meal.getSumProtein();
                bufSumFats += meal.getSumFats();
                bufSumCarbohydrates += meal.getSumCarbohydrates();
            }

            dailyMenu.setSumKcal(bufSumKcal);
            dailyMenu.setSumProtein(bufSumProtein);
            dailyMenu.setSumFats(bufSumFats);
            dailyMenu.setSumCarbohydrates(bufSumCarbohydrates);
        }
    }

    @Transactional
    private void updateMacroElementsMeals() {
        ArrayList<Meal> meals = (ArrayList<Meal>) mealRepository.findAll();

        for (Meal meal : meals) {
            double bufSumKcal = 0;
            double bufSumProtein = 0;
            double bufSumFats = 0;
            double bufSumCarbohydrates = 0;

            for (Recipe recipe : meal.getRecipes()) {
                bufSumKcal += recipe.getSumKcal();
                bufSumProtein += recipe.getSumProtein();
                bufSumFats += recipe.getSumFats();
                bufSumCarbohydrates += recipe.getSumCarbohydrates();
            }
            meal.setSumKcal(bufSumKcal);
            meal.setSumProtein(bufSumProtein);
            meal.setSumFats(bufSumFats);
            meal.setSumCarbohydrates(bufSumCarbohydrates);
        }
    }

    @Transactional
    private void updateMacroElementsRecipes() {
        ArrayList<Recipe> recipes = (ArrayList<Recipe>) recipeRepository.findAll();

        for (Recipe recipe : recipes) {
            double bufSumKcal = 0;
            double bufSumProtein = 0;
            double bufSumFats = 0;
            double bufSumCarbohydrates = 0;

            if (recipe.getIngredients() != null)
                for (Ingredient ingredient : recipe.getIngredients()) {
                    double grams = 0;
                    Integer units = ingredient.getUnits();
                    String measureUnit = ingredient.getMeasureUnit();

                    switch (measureUnit) {
                        case "mg":
                            grams = 0.001 * units;
                            break;
                        case "g":
                        case "ml":
                            grams = units;
                            break;
                        case "dag":
                            grams = 10 * units;
                            break;
                        case "kg":
                        case "l":
                            grams = 1000 * units;
                            break;
                        case "szklanka (200g)":
                            grams = 200 * units;
                            break;
                        case "łyżeczka (5g)":
                            grams = 5 * units;
                            break;
                        case "łyżka (15g)":
                            grams = 15 * units;
                            break;
                        case "garść (50g)":
                            grams = 50 * units;
                            break;
                    }
                    bufSumKcal += grams / 100 * ingredient.getKcalPer100g();
                    bufSumProtein += grams / 100 * ingredient.getProteinPer100g();
                    bufSumFats += grams / 100 * ingredient.getFatsPer100g();
                    bufSumCarbohydrates += grams / 100 * ingredient.getCarbohydratesPer100g();
                }

            recipe.setSumKcal(bufSumKcal);
            recipe.setSumProtein(bufSumProtein);
            recipe.setSumFats(bufSumFats);
            recipe.setSumCarbohydrates(bufSumCarbohydrates);
            recipeService.editRecipe(recipe);
        }
    }
}
