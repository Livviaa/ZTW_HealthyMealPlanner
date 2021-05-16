package general;

import models.Recipe_Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import services.IRecipes_IngredientsService;

import java.util.Collection;
import java.util.List;

@Service
public class Recipes_IngredientsService implements IRecipes_IngredientsService {

    @Autowired
    IRecipes_IngredientsService recipes_ingredientsService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Integer createRecipe_Ingredient(Recipe_Ingredient recipe_ingredient) {
        Integer errorCode;

        if (recipe_ingredient.getProdukt_id() == null) {
            errorCode = -1;
        } else if (recipe_ingredient.getPrzepis_id() == null) {
            errorCode = -2;
        } else {
            jdbcTemplate.update(
                    "INSERT INTO Przepis_Produkt VALUES (?, ?)",
                    recipe_ingredient.getPrzepis_id(),
                    recipe_ingredient.getProdukt_id()
            );
            errorCode = 0;
        }
        return errorCode;
    }

    @Override
    public Collection<Recipe_Ingredient> getRecipe_Ingredient() {
        String sql = "SELECT * FROM Przepis_Produkt";
        List<Recipe_Ingredient> recipe_ingredients = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Recipe_Ingredient.class));
        return recipe_ingredients;
    }

    @Override
    public Integer deleteRecipe_Ingredient(Integer Przepis_id, Integer Produkt_id) {
        String sql = "SELECT * FROM Przepis_Produkt WHERE Przepis_id = " + Przepis_id + " AND Produkt_id = " + Produkt_id;
        List<Recipe_Ingredient> recipe_ingredients = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Recipe_Ingredient.class));

        if (recipe_ingredients.size() > 0) {
            String SQL = "DELETE FROM Przepis_Produkt WHERE Przepis_id = ? AND Produkt_id = ?";
            jdbcTemplate.update(SQL, Przepis_id, Produkt_id);
            return 0;
        }
        return 1;
    }
}
