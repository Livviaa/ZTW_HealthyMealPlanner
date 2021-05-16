package general;

import models.RecipesAndIngredients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.IRecipesAndIngredientsService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class RecipesAndIngredientsControler {

    @Autowired
    IRecipesAndIngredientsService recipesAndIngredientsService;

    // CREATE
    @RequestMapping(value = "/recipe_and_ingredient", method = RequestMethod.POST)
    public ResponseEntity<Object> createRecipeIngredient(@RequestBody RecipesAndIngredients recipesAndIngredients) {
        Integer errorCode = recipesAndIngredientsService.createRecipesAndIngredient(recipesAndIngredients);
        String errorMessage;
        HttpStatus httpStatus;

        if (errorCode == 0) {
            httpStatus = HttpStatus.OK;
            errorMessage = "Recipe-Ingredient: #" + recipesAndIngredients + " created.";
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: " + recipesAndIngredients + ".";
        }

        return new ResponseEntity<>(new Message(errorMessage), httpStatus);
    }

    // READ
    @RequestMapping(value = "/recipe_and_ingredient", method = RequestMethod.GET)
    public ResponseEntity<Object> getRecipeIngredient() {
        return new ResponseEntity<>(recipesAndIngredientsService.getRecipesAndIngredient(), HttpStatus.OK);
    }

    // DELETE
    @RequestMapping(value = "/recipe_and_ingredient/{id1},{id2}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteRecipeIngredient(@PathVariable("id1") int id1, @PathVariable("id2") int id2) {
        Integer errorCode = recipesAndIngredientsService.deleteRecipesAndIngredient(id1, id2);
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
