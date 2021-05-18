package general;

import models.Meal;
import models.MealsAndRecipes;
import models.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import services.IMealsService;
import services.IMealsAndRecipesService;
import services.IRecipeService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class MealsService implements IMealsService {

    @Autowired
    IMealsService mealsService;

    @Autowired
    IRecipeService recipeService;

    @Autowired
    IMealsAndRecipesService mealsAndRecipesService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // CREATE
    @Override
    public Integer createMeal(Meal meal) {
        Integer errorCode;

         if (meal.getDailyMenuId() == null) {
            errorCode = -2;
        } else {
            jdbcTemplate.update(
                    "INSERT INTO Meal VALUES (?, ?, ?, ?, ?)",
                    meal.getDailyMenuId(),
                    meal.getSumKcal(),
                    meal.getSumProtein(),
                    meal.getSumFats(),
                    meal.getSumCarbohydrates()
            );
            errorCode = 0;
        }
        return errorCode;
    }

    // READ
    @Override
    public Meal getMeal(Integer id) {
        String sql = "SELECT * FROM Meal WHERE MealId = " + id;
        List<Meal> meals = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Meal.class));

        if (meals.size() == 0) {
            return null;
        } else {
            Meal meal = meals.get(0);
            List<Recipe> recipes = (List<Recipe>) recipeService.getRecipe();
            List<MealsAndRecipes> allMealsAndRecipes = (List<MealsAndRecipes>) mealsAndRecipesService.getMealsAndRecipes();
            for (MealsAndRecipes mealsAndRecipes : allMealsAndRecipes) {
                for (Recipe recipe : recipes) {
                    if (mealsAndRecipes.getRecipeId().equals(recipe.getRecipeId()) && mealsAndRecipes.getMealId().equals(meal.getMealId())) {
                        if (meal.getRecipes() == null)
                            meal.setRecipes(new ArrayList<>());
                        meal.getRecipes().add(recipe);
                    }
                }
            }
            return meal;
        }
    }

    @Override
    public Collection<Meal> getMeal() {
        String sql = "SELECT * FROM Meal";
        List<Meal> meals = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Meal.class));
        List<Recipe> recipes = (List<Recipe>) recipeService.getRecipe();
        List<MealsAndRecipes> allMealsAndRecipes = (List<MealsAndRecipes>) mealsAndRecipesService.getMealsAndRecipes();

        for (Meal meal : meals) {
            for (MealsAndRecipes mealsAndRecipes : allMealsAndRecipes) {
                for (Recipe recipe : recipes) {
                    if (mealsAndRecipes.getRecipeId().equals(recipe.getRecipeId()) && mealsAndRecipes.getMealId().equals(meal.getMealId())) {
                        if (meal.getRecipes() == null)
                            meal.setRecipes(new ArrayList<>());
                        meal.getRecipes().add(recipe);
                    }
                }
            }
        }
        return meals;
    }

    // UPDATE
    @Override
    public Integer updateMeal(Meal meal) {
        Integer errorCode;
        Meal mealToUpdate = getMeal(meal.getMealId());

        if (mealToUpdate == null) {
            errorCode = -1;
        } else if (meal.getDailyMenuId() == null) {
            errorCode = -2;
        }  else {
            String SQL = "UPDATE Meal SET DailyMenuId = ?, SumKcal = ?, SumProtein = ?, SumFats = ?, " +
                    "SumCarbohydrates = ?, WHERE MealId = ?";
            jdbcTemplate.update(SQL,
                    meal.getDailyMenuId(),
                    meal.getSumKcal(),
                    meal.getSumProtein(),
                    meal.getSumFats(),
                    meal.getSumCarbohydrates(),
                    meal.getMealId()
            );
            errorCode = 0;
        }
        return errorCode;
    }

    // DELETE
    @Override
    public Integer deleteMeal(Integer id) {
        Meal mealToDelete = getMeal(id);
        if (mealToDelete != null) {
            String SQL = "DELETE FROM Meal WHERE MealId = ?";
            jdbcTemplate.update(SQL, id);
            return 0;
        }
        return 1;
    }
}
