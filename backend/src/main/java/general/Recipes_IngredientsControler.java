package general;

import models.Recipe_Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.IRecipes_IngredientsService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class Recipes_IngredientsControler {

    @Autowired
    IRecipes_IngredientsService recipes_ingredientsService;

    // CREATE
    @RequestMapping(value = "/recipe_ingredient", method = RequestMethod.POST)
    public ResponseEntity<Object> createRecipeIngredient(@RequestBody Recipe_Ingredient recipe_ingredient) {
        Integer errorCode = recipes_ingredientsService.createRecipe_Ingredient(recipe_ingredient);
        String errorMessage;
        HttpStatus httpStatus;

        if (errorCode == 0) {
            httpStatus = HttpStatus.OK;
            errorMessage = "Recipe-Ingredient: #" + recipe_ingredient + " created.";
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: " + recipe_ingredient + ".";
        }

        return new ResponseEntity<>(new Message(errorMessage), httpStatus);
    }

    // READ
    @RequestMapping(value = "/recipe_ingredient", method = RequestMethod.GET)
    public ResponseEntity<Object> getRecipeIngredient() {
        return new ResponseEntity<>(recipes_ingredientsService.getRecipe_Ingredient(), HttpStatus.OK);
    }

    // DELETE
    @RequestMapping(value = "/recipe_ingredient/{id1},{id2}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteRecipeIngredient(@PathVariable("id1") int id1, @PathVariable("id2") int id2) {
        Integer errorCode = recipes_ingredientsService.deleteRecipe_Ingredient(id1, id2);
        String errorMessage;
        HttpStatus httpStatus;

        if (errorCode == 0) {
            httpStatus = HttpStatus.OK;
            errorMessage = "Recipe-Ingredient: #" + id1 + "-"+ id2 + " deleted.";
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: #" + id1 + "-"+ id2 +   ".";
        }

        return new ResponseEntity<>(new Message(errorMessage), httpStatus);
    }
}
