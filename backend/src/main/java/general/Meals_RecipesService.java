package general;

import models.Meal_Recipe;
import models.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import services.IMeals_RecipesService;
import services.IRecipeService;

import java.util.Collection;
import java.util.List;

@Service
public class Meals_RecipesService implements IMeals_RecipesService {

    @Autowired
    IRecipeService recipeService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Integer createMeal_Recipe(Meal_Recipe meal_recipe) {
        Integer errorCode;

        if (meal_recipe.getPosilek_id() == null) {
            errorCode = -1;
        } else if (meal_recipe.getPrzepis_id() == null) {
            errorCode = -2;
        } else {
            jdbcTemplate.update(
                    "INSERT INTO Posilek_Przepis VALUES (?, ?)",
                    meal_recipe.getPosilek_id(),
                    meal_recipe.getPrzepis_id()
            );
            errorCode = 0;
        }
        return errorCode;
    }

    @Override
    public Collection<Meal_Recipe> getMeal_Recipe() {
        String sql = "SELECT * FROM Posilek_Przepis";
        List<Meal_Recipe> meal_recipes = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Meal_Recipe.class));
        return meal_recipes;
    }

    @Override
    public Integer deleteMeal_Recipe(Integer Posilek_id, Integer Przepis_id) {
        String sql = "SELECT * FROM Posilek_Przepis WHERE Posilek_id = " + Posilek_id + " AND Przepis_id = " + Przepis_id;
        List<Meal_Recipe> meal_recipes = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Meal_Recipe.class));

        if (meal_recipes.size() > 0) {
            String SQL = "DELETE FROM Posilek_Przepis WHERE Posilek_id = ? AND Przepis_id = ?";
            jdbcTemplate.update(SQL, Posilek_id, Przepis_id);
            return 0;
        }
        return 1;
    }
}
