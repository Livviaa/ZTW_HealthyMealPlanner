package general;

import models.Ingredient;
import models.Recipe;
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

        if (ingredient.getProdukt_id() == null) {
            errorCode = -1;
        } else if (ingredient.getNazwa_produktu() == null) {
            errorCode = -2;
        } else if (ingredient.getJednostka_miary() == null) {
            errorCode = -3;
        } else if (ingredient.getLiczba_jednostek() == null) {
            errorCode = -4;
        } else if (ingredient.getKcal_na_100_jednostek() == null) {
            errorCode = -5;
        } else if (ingredient.getBialko_na_100_jednostek() == null) {
            errorCode = -6;
        } else if (ingredient.getTluszcze_na_100_jednostek() == null) {
            errorCode = -7;
        } else if (ingredient.getWeglowodany_na_100_jednostek() == null) {
            errorCode = -8;
        } else {
            jdbcTemplate.update(
                    "INSERT INTO Produkt VALUES (?, ?, ?, ?, ?, ?, ?)",
                    ingredient.getNazwa_produktu(),
                    ingredient.getJednostka_miary(),
                    ingredient.getLiczba_jednostek(),
                    ingredient.getKcal_na_100_jednostek(),
                    ingredient.getBialko_na_100_jednostek(),
                    ingredient.getTluszcze_na_100_jednostek(),
                    ingredient.getWeglowodany_na_100_jednostek()
            );
            errorCode = 0;
        }
        return errorCode;
    }

    // READ
    @Override
    public Ingredient getIngredient(Integer id) {
        String sql = "SELECT * FROM Produkt WHERE Produkt_id  = " + id;
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
        String sql = "SELECT * FROM Produkt";
        List<Ingredient> ingredients = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Ingredient.class));
        return ingredients;
    }

    // UPDATE
    @Override
    public Integer updateIngredient(Ingredient ingredient) {
        Integer errorCode;
        Ingredient ingredientToUpdate = getIngredient(ingredient.getProdukt_id());

        if (ingredientToUpdate == null) {
            errorCode = -1;
        } else if (ingredient.getNazwa_produktu() == null) {
            errorCode = -2;
        } else if (ingredient.getJednostka_miary() == null) {
            errorCode = -3;
        } else if (ingredient.getLiczba_jednostek() == null) {
            errorCode = -4;
        } else if (ingredient.getKcal_na_100_jednostek() == null) {
            errorCode = -5;
        } else if (ingredient.getBialko_na_100_jednostek() == null) {
            errorCode = -6;
        } else if (ingredient.getTluszcze_na_100_jednostek() == null) {
            errorCode = -7;
        } else if (ingredient.getWeglowodany_na_100_jednostek() == null) {
            errorCode = -8;
        }else {
            String SQL = "UPDATE Produkt SET Nazwa_produktu = ?, Jednostka_miary = ?, Liczba_jednostek = ?, Kcal_na_100_jednostek = ?, Bialko_na_100_jednostek = ?, " +
                    "Tluszcze_na_100_jednostek = ?, Weglowodany_na_100_jednostek = ?, WHERE Produkt_id = ?";
            jdbcTemplate.update(SQL,
                    ingredient.getNazwa_produktu(),
                    ingredient.getJednostka_miary(),
                    ingredient.getLiczba_jednostek(),
                    ingredient.getKcal_na_100_jednostek(),
                    ingredient.getBialko_na_100_jednostek(),
                    ingredient.getTluszcze_na_100_jednostek(),
                    ingredient.getWeglowodany_na_100_jednostek(),
                    ingredient.getProdukt_id()
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
            String SQL = "DELETE FROM Produkt WHERE Produkt_id = ?";
            jdbcTemplate.update(SQL, id);
            return 0;
        }
        return 1;
    }
}
