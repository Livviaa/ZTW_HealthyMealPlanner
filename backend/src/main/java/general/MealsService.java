package general;

import models.Meal;
import models.Meal_Recipe;
import models.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import services.IMealsService;
import services.IMeals_RecipesService;
import services.IRecipeService;
import services.IUsersService;

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
    IMeals_RecipesService meals_recipesService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // CREATE
    @Override
    public Integer createMeal(Meal meal) {
        Integer errorCode;

        if (meal.getPosilek_id() == null) {
            errorCode = -1;
        } else if (meal.getJadlospis_id() == null) {
            errorCode = -2;
        } else if (meal.getSum_kcal() == null) {
            errorCode = -3;
        } else if (meal.getSum_bialko() == null) {
            errorCode = -4;
        } else if (meal.getSum_tluszcze() == null) {
            errorCode = -5;
        } else if (meal.getSum_weglowodany() == null) {
            errorCode = -6;
        } else {
            jdbcTemplate.update(
                    "INSERT INTO Posilek VALUES (?, ?, ?, ?, ?)",
                    meal.getJadlospis_id(),
                    meal.getSum_kcal(),
                    meal.getSum_bialko(),
                    meal.getSum_tluszcze(),
                    meal.getSum_weglowodany()
            );
            errorCode = 0;
        }
        return errorCode;
    }

    // READ
    @Override
    public Meal getMeal(Integer id) {
        String sql = "SELECT * FROM Posilek WHERE Posilek_id = " + id;
        List<Meal> meals = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Meal.class));

        if (meals.size() == 0) {
            return null;
        } else {
            Meal meal = meals.get(0);
            List<Recipe> recipes = (List<Recipe>) recipeService.getRecipe();
            List<Meal_Recipe> meal_recipes = (List<Meal_Recipe>) meals_recipesService.getMeal_Recipe();
            for (Meal_Recipe meal_recipe : meal_recipes) {
                for (Recipe recipe : recipes) {
                    if (meal_recipe.getPrzepis_id().equals(recipe.getPrzepis_id()) && meal_recipe.getPosilek_id().equals(meal.getPosilek_id())) {
                        if (meal.getPrzepisy() == null)
                            meal.setPrzepisy(new ArrayList<>());
                        meal.getPrzepisy().add(recipe);
                    }
                }
            }
            return meal;
        }
    }

    @Override
    public Collection<Meal> getMeal() {
        String sql = "SELECT * FROM Posilek";
        List<Meal> meals = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Meal.class));
        List<Recipe> recipes = (List<Recipe>) recipeService.getRecipe();
        List<Meal_Recipe> meal_recipes = (List<Meal_Recipe>) meals_recipesService.getMeal_Recipe();

        for (Meal meal : meals) {
            for (Meal_Recipe meal_recipe : meal_recipes) {
                for (Recipe recipe : recipes) {
                    if (meal_recipe.getPrzepis_id().equals(recipe.getPrzepis_id()) && meal_recipe.getPosilek_id().equals(meal.getPosilek_id())) {
                        if (meal.getPrzepisy() == null)
                            meal.setPrzepisy(new ArrayList<>());
                        meal.getPrzepisy().add(recipe);
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
        Meal mealToUpdate = getMeal(meal.getPosilek_id());

        if (mealToUpdate == null) {
            errorCode = -1;
        } else if (meal.getJadlospis_id() == null) {
            errorCode = -2;
        } else if (meal.getSum_kcal() == null) {
            errorCode = -3;
        } else if (meal.getSum_bialko() == null) {
            errorCode = -4;
        } else if (meal.getSum_tluszcze() == null) {
            errorCode = -5;
        } else if (meal.getSum_weglowodany() == null) {
            errorCode = -6;
        } else {
            String SQL = "UPDATE Posilek SET Jadlospis_id = ?, Sum_kcal = ?, Sum_bialko = ?, Sum_tluszcze = ?, " +
                    "Sum_weglowodany = ?, WHERE Posilek_id = ?";
            jdbcTemplate.update(SQL,
                    meal.getJadlospis_id(),
                    meal.getSum_kcal(),
                    meal.getSum_bialko(),
                    meal.getSum_tluszcze(),
                    meal.getSum_weglowodany(),
                    meal.getPosilek_id()
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
            String SQL = "DELETE FROM Posilek WHERE Posilek_id = ?";
            jdbcTemplate.update(SQL, id);
            return 0;
        }
        return 1;
    }
}
