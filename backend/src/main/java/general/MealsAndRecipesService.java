package general;

import models.MealsAndRecipes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import services.IMealsAndRecipesService;
import services.IRecipeService;

import java.util.Collection;
import java.util.List;

@Service
public class MealsAndRecipesService implements IMealsAndRecipesService {

    @Autowired
    IRecipeService recipeService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Integer createMealsAndRecipes(MealsAndRecipes mealsAndRecipes) {
        Integer errorCode;

        if (mealsAndRecipes.getMealId() == null) {
            errorCode = -1;
        } else if (mealsAndRecipes.getRecipeId() == null) {
            errorCode = -2;
        } else {
            jdbcTemplate.update(
                    "INSERT INTO MealsAndRecipes VALUES (?, ?)",
                    mealsAndRecipes.getMealId(),
                    mealsAndRecipes.getRecipeId()
            );
            errorCode = 0;
        }
        return errorCode;
    }

    @Override
    public Collection<MealsAndRecipes> getMealsAndRecipes() {
        String sql = "SELECT * FROM MealsAndRecipes";
        List<MealsAndRecipes> mealsAndRecipes = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(MealsAndRecipes.class));
        return mealsAndRecipes;
    }

    @Override
    public Integer deleteMealsAndRecipes(Integer mealId, Integer recipeId) {
        String sql = "SELECT * FROM MealsAndRecipes WHERE MealId = " + mealId + " AND RecipeId = " + recipeId;
        List<MealsAndRecipes> mealsAndRecipes = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(MealsAndRecipes.class));

        if (mealsAndRecipes.size() > 0) {
            String SQL = "DELETE FROM MealsAndRecipes WHERE MealId = ? AND RecipeId = ?";
            jdbcTemplate.update(SQL, mealId, recipeId);
            return 0;
        }
        return 1;
    }
}
