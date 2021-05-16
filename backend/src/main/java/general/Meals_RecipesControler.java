package general;

import models.Meal_Recipe;
import models.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.IMeals_RecipesService;
import services.IRecipeService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class Meals_RecipesControler {

    @Autowired
    IMeals_RecipesService meals_recipesService;

    // CREATE
    @RequestMapping(value = "/meal_recipe", method = RequestMethod.POST)
    public ResponseEntity<Object> createMealRecipe(@RequestBody Meal_Recipe meal_recipe) {
        Integer errorCode = meals_recipesService.createMeal_Recipe(meal_recipe);
        String errorMessage;
        HttpStatus httpStatus;

        if (errorCode == 0) {
            httpStatus = HttpStatus.OK;
            errorMessage = "Meal-Recipe: #" + meal_recipe + " created.";
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: " + meal_recipe + ".";
        }

        return new ResponseEntity<>(new Message(errorMessage), httpStatus);
    }

    // READ
    @RequestMapping(value = "/meal_recipe", method = RequestMethod.GET)
    public ResponseEntity<Object> getMealsRecipes() {
        return new ResponseEntity<>(meals_recipesService.getMeal_Recipe(), HttpStatus.OK);
    }

    // DELETE
    @RequestMapping(value = "/meal_recipe/{id1},{id2}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteMealRecipe(@PathVariable("id1") int id1, @PathVariable("id2") int id2) {
        Integer errorCode = meals_recipesService.deleteMeal_Recipe(id1, id2);
        String errorMessage;
        HttpStatus httpStatus;

        if (errorCode == 0) {
            httpStatus = HttpStatus.OK;
            errorMessage = "Meal Recipe: #" + id1 + "-"+ id2 + " deleted.";
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: #" + id1 + "-"+ id2 +   ".";
        }

        return new ResponseEntity<>(new Message(errorMessage), httpStatus);
    }
}
