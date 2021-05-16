package general;

import models.Ingredient;
import models.Recipe;
import models.RecipesAndIngredients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import services.IIngredientService;
import services.IRecipeService;
import services.IRecipesAndIngredientsService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class RecipesService implements IRecipeService {

    @Autowired
    IRecipeService recipeService;

    @Autowired
    IIngredientService ingredientService;

    @Autowired
    IRecipesAndIngredientsService recipes_ingredientsService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // CREATE
    @Override
    public Integer createRecipe(Recipe recipe) {
        Integer errorCode;

        if (recipe.getRecipeId() == null) {
            errorCode = -1;
        } else if (recipe.getName() == null) {
            errorCode = -2;
        } else if (recipe.getInstruction() == null) {
            errorCode = -3;
        } else if (recipe.getImage() == null) {
            errorCode = -4;
        } else if (recipe.getSumKcal() == null) {
            errorCode = -5;
        } else if (recipe.getSumProtein() == null) {
            errorCode = -6;
        } else if (recipe.getSumFats() == null) {
            errorCode = -7;
        } else if (recipe.getSumCarbohydrates() == null) {
            errorCode = -8;
        } else {
            jdbcTemplate.update(
                    "INSERT INTO Recipe VALUES (?, ?, ?, ?, ?, ?, ?)",
                    recipe.getName(),
                    recipe.getInstruction(),
                    recipe.getImage(),
                    recipe.getSumKcal(),
                    recipe.getSumProtein(),
                    recipe.getSumFats(),
                    recipe.getSumCarbohydrates()
            );
            errorCode = 0;
        }
        return errorCode;
    }

    // READ
    @Override
    public Recipe getRecipe(Integer id) {
        String sql = "SELECT * FROM Recipe WHERE RecipeId  = " + id;
        List<Recipe> recipes = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Recipe.class));

        if (recipes.size() == 0){
            return null;
        }
        else {
            return recipes.get(0);
        }
    }

    @Override
    public Collection<Recipe> getRecipe() {
        String sql = "SELECT * FROM Recipe";
        List<Recipe> recipes = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Recipe.class));
        List<Ingredient> ingredients = (List<Ingredient>) ingredientService.getIngredient();
        List<RecipesAndIngredients> allRecipesAndIngredients = (List<RecipesAndIngredients>) recipes_ingredientsService.getRecipesAndIngredient();

        for (Recipe recipe : recipes){
            for(Ingredient ingredient: ingredients){
                for(RecipesAndIngredients recipesAndIngredients : allRecipesAndIngredients){
                    if(recipesAndIngredients.getRecipeId().equals(recipe.getRecipeId()) && recipesAndIngredients.getIngredientId().equals(ingredient.getIngredientId())){
                        if (recipe.getIngredients() == null)
                            recipe.setIngredients(new ArrayList<>());
                        recipe.getIngredients().add(ingredient);
                    }
                }
            }

        }

        return recipes;
    }

    // UPDATE
    @Override
    public Integer updateRecipe(Recipe recipe) {
        Integer errorCode;
        Recipe recipesToUpdate = getRecipe(recipe.getRecipeId());

        if (recipesToUpdate == null) {
            errorCode = -1;
        } else if (recipe.getName() == null) {
            errorCode = -2;
        } else if (recipe.getInstruction() == null) {
            errorCode = -3;
        } else if (recipe.getImage() == null) {
            errorCode = -4;
        } else if (recipe.getSumKcal() == null) {
            errorCode = -5;
        } else if (recipe.getSumProtein() == null) {
            errorCode = -6;
        } else if (recipe.getSumFats() == null) {
            errorCode = -7;
        } else if (recipe.getSumCarbohydrates() == null) {
            errorCode = -8;
        }else {
            String SQL = "UPDATE Recipe SET Name = ?, Instruction = ?, Image = ?, SumKcal = ?, SumProtein = ?, " +
                    "SumFats = ?, SumCarbohydrates = ?, WHERE RecipeId = ?";
            jdbcTemplate.update(SQL,
                    recipe.getName(),
                    recipe.getInstruction(),
                    recipe.getImage(),
                    recipe.getSumKcal(),
                    recipe.getSumProtein(),
                    recipe.getSumFats(),
                    recipe.getSumCarbohydrates(),
                    recipe.getRecipeId()
            );
            errorCode = 0;
        }
        return errorCode;
    }

    // DELETE
    @Override
    public Integer deleteRecipe(Integer id) {
        Recipe recipesToDelete = getRecipe(id);
        if (recipesToDelete != null) {
            String SQL = "DELETE FROM Recipe WHERE RecipeId = ?";
            jdbcTemplate.update(SQL, id);
            return 0;
        }
        return 1;
    }
}
