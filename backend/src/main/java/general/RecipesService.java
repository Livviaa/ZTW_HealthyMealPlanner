package general;

import models.Ingredient;
import models.Recipe;
import models.Recipe_Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import services.IIngredientService;
import services.IRecipeService;
import services.IRecipes_IngredientsService;

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
    IRecipes_IngredientsService recipes_ingredientsService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // CREATE
    @Override
    public Integer createRecipe(Recipe recipe) {
        Integer errorCode;

        if (recipe.getPrzepis_id() == null) {
            errorCode = -1;
        } else if (recipe.getNazwa_potrawy() == null) {
            errorCode = -2;
        } else if (recipe.getTresc() == null) {
            errorCode = -3;
        } else if (recipe.getZdjecie() == null) {
            errorCode = -4;
        } else if (recipe.getSum_kcal() == null) {
            errorCode = -5;
        } else if (recipe.getSum_bialko() == null) {
            errorCode = -6;
        } else if (recipe.getSum_tluszcze() == null) {
            errorCode = -7;
        } else if (recipe.getSum_weglowodany() == null) {
            errorCode = -8;
        } else {
            jdbcTemplate.update(
                    "INSERT INTO Przepis VALUES (?, ?, ?, ?, ?, ?, ?)",
                    recipe.getNazwa_potrawy(),
                    recipe.getTresc(),
                    recipe.getZdjecie(),
                    recipe.getSum_kcal(),
                    recipe.getSum_bialko(),
                    recipe.getSum_tluszcze(),
                    recipe.getSum_weglowodany()
            );
            errorCode = 0;
        }
        return errorCode;
    }

    // READ
    @Override
    public Recipe getRecipe(Integer id) {
        String sql = "SELECT * FROM Przepis WHERE Przepis_id  = " + id;
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
        String sql = "SELECT * FROM Przepis";
        List<Recipe> recipes = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Recipe.class));
        List<Ingredient> ingredients = (List<Ingredient>) ingredientService.getIngredient();
        List<Recipe_Ingredient> recipe_ingredients = (List<Recipe_Ingredient>) recipes_ingredientsService.getRecipe_Ingredient();

        for (Recipe recipe : recipes){
            for(Ingredient ingredient: ingredients){
                for(Recipe_Ingredient recipe_ingredient: recipe_ingredients){
                    if(recipe_ingredient.getPrzepis_id().equals(recipe.getPrzepis_id()) && recipe_ingredient.getProdukt_id().equals(ingredient.getProdukt_id())){
                        if (recipe.getSkladniki() == null)
                            recipe.setSkladniki(new ArrayList<>());
                        recipe.getSkladniki().add(ingredient);
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
        Recipe recipesToUpdate = getRecipe(recipe.getPrzepis_id());

        if (recipesToUpdate == null) {
            errorCode = -1;
        } else if (recipe.getNazwa_potrawy() == null) {
            errorCode = -2;
        } else if (recipe.getTresc() == null) {
            errorCode = -3;
        } else if (recipe.getZdjecie() == null) {
            errorCode = -4;
        } else if (recipe.getSum_kcal() == null) {
            errorCode = -5;
        } else if (recipe.getSum_bialko() == null) {
            errorCode = -6;
        } else if (recipe.getSum_tluszcze() == null) {
            errorCode = -7;
        } else if (recipe.getSum_weglowodany() == null) {
            errorCode = -8;
        }else {
            String SQL = "UPDATE Przepis SET Nazwa_potrawy = ?, Tresc = ?, Zdjecie = ?, Sum_kcal = ?, Sum_bialko = ?, " +
                    "Sum_tluszcze = ?, Sum_weglowodany = ?, WHERE Przepis_id = ?";
            jdbcTemplate.update(SQL,
                    recipe.getNazwa_potrawy(),
                    recipe.getTresc(),
                    recipe.getZdjecie(),
                    recipe.getSum_kcal(),
                    recipe.getSum_bialko(),
                    recipe.getSum_tluszcze(),
                    recipe.getSum_weglowodany(),
                    recipe.getPrzepis_id()
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
            String SQL = "DELETE FROM Przepis WHERE Przepis_id = ?";
            jdbcTemplate.update(SQL, id);
            return 0;
        }
        return 1;
    }
}
