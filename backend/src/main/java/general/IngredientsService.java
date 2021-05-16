package general;

import models.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import services.IIngredientService;

import java.util.Collection;
import java.util.List;

@Service
public class IngredientsService implements IIngredientService {

    @Autowired
    IIngredientService ingredientService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // CREATE
    @Override
    public Integer createIngredient(Ingredient ingredient) {
        Integer errorCode;

        if (ingredient.getIngredientId() == null) {
            errorCode = -1;
        } else if (ingredient.getName() == null) {
            errorCode = -2;
        } else if (ingredient.getMeasureUnit() == null) {
            errorCode = -3;
        } else if (ingredient.getUnits() == null) {
            errorCode = -4;
        } else if (ingredient.getKcalPer100() == null) {
            errorCode = -5;
        } else if (ingredient.getProteinPer100() == null) {
            errorCode = -6;
        } else if (ingredient.getFatsPer100() == null) {
            errorCode = -7;
        } else if (ingredient.getCarbohydratesPer100() == null) {
            errorCode = -8;
        } else {
            jdbcTemplate.update(
                    "INSERT INTO Ingredient VALUES (?, ?, ?, ?, ?, ?, ?)",
                    ingredient.getName(),
                    ingredient.getMeasureUnit(),
                    ingredient.getUnits(),
                    ingredient.getKcalPer100(),
                    ingredient.getProteinPer100(),
                    ingredient.getFatsPer100(),
                    ingredient.getCarbohydratesPer100()
            );
            errorCode = 0;
        }
        return errorCode;
    }

    // READ
    @Override
    public Ingredient getIngredient(Integer id) {
        String sql = "SELECT * FROM Ingredient WHERE IngredientId  = " + id;
        List<Ingredient> ingredients = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Ingredient.class));

        if (ingredients.size() == 0){
            return null;
        }
        else {
            return ingredients.get(0);
        }
    }

    @Override
    public Collection<Ingredient> getIngredient() {
        String sql = "SELECT * FROM Ingredient";
        List<Ingredient> ingredients = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Ingredient.class));
        return ingredients;
    }

    // UPDATE
    @Override
    public Integer updateIngredient(Ingredient ingredient) {
        Integer errorCode;
        Ingredient ingredientToUpdate = getIngredient(ingredient.getIngredientId());

        if (ingredientToUpdate == null) {
            errorCode = -1;
        } else if (ingredient.getName() == null) {
            errorCode = -2;
        } else if (ingredient.getMeasureUnit() == null) {
            errorCode = -3;
        } else if (ingredient.getUnits() == null) {
            errorCode = -4;
        } else if (ingredient.getKcalPer100() == null) {
            errorCode = -5;
        } else if (ingredient.getProteinPer100() == null) {
            errorCode = -6;
        } else if (ingredient.getFatsPer100() == null) {
            errorCode = -7;
        } else if (ingredient.getCarbohydratesPer100() == null) {
            errorCode = -8;
        }else {
            String SQL = "UPDATE Ingredient SET Name = ?, MeasureUnit = ?, Units = ?, KcalPer100 = ?, ProteinPer100 = ?, " +
                    "FatsPer100 = ?, CarbohydratesPer100 = ?, WHERE IngredientId = ?";
            jdbcTemplate.update(SQL,
                    ingredient.getName(),
                    ingredient.getMeasureUnit(),
                    ingredient.getUnits(),
                    ingredient.getKcalPer100(),
                    ingredient.getProteinPer100(),
                    ingredient.getFatsPer100(),
                    ingredient.getCarbohydratesPer100(),
                    ingredient.getIngredientId()
            );
            errorCode = 0;
        }
        return errorCode;
    }

    // DELETE
    @Override
    public Integer deleteIngredient(Integer id) {
        Ingredient ingredientToDelete = getIngredient(id);
        if (ingredientToDelete != null) {
            String SQL = "DELETE FROM Ingredient WHERE IngredientId = ?";
            jdbcTemplate.update(SQL, id);
            return 0;
        }
        return 1;
    }
}
