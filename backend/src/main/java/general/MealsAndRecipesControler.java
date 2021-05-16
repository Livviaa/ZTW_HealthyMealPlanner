package general;

import models.MealsAndRecipes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.IMealsAndRecipesService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class MealsAndRecipesControler {

    @Autowired
    IMealsAndRecipesService mealsAndRecipesService;

    // CREATE
    @RequestMapping(value = "/meals_and_recipes", method = RequestMethod.POST)
    public ResponseEntity<Object> createMealRecipe(@RequestBody MealsAndRecipes mealsAndRecipes) {
        Integer errorCode = mealsAndRecipesService.createMealsAndRecipes(mealsAndRecipes);
        String errorMessage;
        HttpStatus httpStatus;

        if (errorCode == 0) {
            httpStatus = HttpStatus.OK;
            errorMessage = "Meal-Recipe: #" + mealsAndRecipes + " created.";
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: " + mealsAndRecipes + ".";
        }

        return new ResponseEntity<>(new Message(errorMessage), httpStatus);
    }

    // READ
    @RequestMapping(value = "/meals_and_recipes", method = RequestMethod.GET)
    public ResponseEntity<Object> getMealsRecipes() {
        return new ResponseEntity<>(mealsAndRecipesService.getMealsAndRecipes(), HttpStatus.OK);
    }

    // DELETE
    @RequestMapping(value = "/meals_and_recipes/{id1},{id2}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteMealRecipe(@PathVariable("id1") int id1, @PathVariable("id2") int id2) {
        Integer errorCode = mealsAndRecipesService.deleteMealsAndRecipes(id1, id2);
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
