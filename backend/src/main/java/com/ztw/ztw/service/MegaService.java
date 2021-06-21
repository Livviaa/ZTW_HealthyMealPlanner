package com.ztw.ztw.service;

import com.ztw.ztw.model.*;
import com.ztw.ztw.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
    private final MealService mealService;
    private final UserService userService;


    public void updateMacroElementsAll() {
        updateMacroElementsRecipes();
        updateMacroElementsMeals();
        updateMacroElementsDailyMenus();
        updateUsers();
    }

    @Transactional
    private void updateUsers() {
        ArrayList<User> users = (ArrayList<User>) userRepository.findAll();

        for (User user : users) {
            // węglowodany (55%) i białka(10%) : 4 kcal == 1 g
            // Tłuszcze (15%): 9 kcal == 1 g

            double BMR;
            Date today = new Date();
            long difference_In_Time = today.getTime() - user.getBirthDate().getTime();
            long difference_In_Years = (difference_In_Time / (1000L * 60 * 60 * 24 * 365));

            if(user.getSex().equals("M")){
                BMR = 66 + (13.7 * user.getWeight()) + (5 * user.getHeight()) - (6.8 * difference_In_Years);
            }else{
                BMR = 655 + (9.6 * user.getWeight()) + (1.8 * user.getHeight()) - (4.7 * difference_In_Years);
            }

            switch (user.getActivity()) {
                case "brak":
                    BMR *= 1.2;
                    break;
                case "malo":
                    BMR *= 1.375;
                    break;
                case "srednio":
                    BMR *= 1.725;
                    break;
                case "duzo":
                    BMR *= 1.9;
                    break;
            }
            user.setRecommendedDailyKcal(BMR);
            double carbo = 0.55 * BMR / 4;
            double prot = 0.10 * BMR / 4;
            double fats = 0.15 * BMR / 9;

            user.setRecommendedDailyProtein(prot);
            user.setRecommendedDailyFats(fats);
            user.setRecommendedDailyCarbohydrates(carbo);
            userService.editUser(user);
        }
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
            mealService.editMeal(meal);
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
