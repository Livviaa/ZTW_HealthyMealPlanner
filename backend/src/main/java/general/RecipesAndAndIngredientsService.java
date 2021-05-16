package general;

import models.RecipesAndIngredients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import services.IRecipesAndIngredientsService;

import java.util.Collection;
import java.util.List;

@Service
public class RecipesAndAndIngredientsService implements IRecipesAndIngredientsService {

    @Autowired
    IRecipesAndIngredientsService recipesAndIngredientsService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Integer createRecipesAndIngredient(RecipesAndIngredients recipesAndIngredients) {
        Integer errorCode;

        if (recipesAndIngredients.getIngredientId() == null) {
            errorCode = -1;
        } else if (recipesAndIngredients.getRecipeId() == null) {
            errorCode = -2;
        } else {
            jdbcTemplate.update(
                    "INSERT INTO RecipesAndIngredients VALUES (?, ?)",
                    recipesAndIngredients.getRecipeId(),
                    recipesAndIngredients.getIngredientId()
            );
            errorCode = 0;
        }
        return errorCode;
    }

    @Override
    public Collection<RecipesAndIngredients> getRecipesAndIngredient() {
        String sql = "SELECT * FROM RecipesAndIngredients";
        List<RecipesAndIngredients> recipesAndIngredients = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(RecipesAndIngredients.class));
        return recipesAndIngredients;
    }

    @Override
    public Integer deleteRecipesAndIngredient(Integer Przepis_id, Integer Produkt_id) {
        String sql = "SELECT * FROM RecipesAndIngredients WHERE RecipeId = " + Przepis_id + " AND IngredientId = " + Produkt_id;
        List<RecipesAndIngredients> recipesAndIngredients = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(RecipesAndIngredients.class));

        if (recipesAndIngredients.size() > 0) {
            String SQL = "DELETE FROM RecipesAndIngredients WHERE RecipeId = ? AND IngredientId = ?";
            jdbcTemplate.update(SQL, Przepis_id, Produkt_id);
            return 0;
        }
        return 1;
    }
}
