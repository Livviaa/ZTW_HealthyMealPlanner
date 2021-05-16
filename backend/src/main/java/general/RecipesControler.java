package general;

import models.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.IRecipeService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class RecipesControler {

    @Autowired
    IRecipeService recipeService;

    // CREATE
    @RequestMapping(value = "/recipe", method = RequestMethod.POST)
    public ResponseEntity<Object> createRecipe(@RequestBody Recipe recipe) {
        Integer errorCode = recipeService.createRecipe(recipe);
        String errorMessage;
        HttpStatus httpStatus;

        if (errorCode == 0) {
            httpStatus = HttpStatus.OK;
            errorMessage = "Recipe: #" + recipe.getRecipeId() + " created.";
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: " + recipe.getRecipeId()+ ".";
        }

        return new ResponseEntity<>(new Message(errorMessage), httpStatus);
    }

    // READ
    @RequestMapping(value = "/recipe", method = RequestMethod.GET)
    public ResponseEntity<Object> getRecipes() {
        return new ResponseEntity<>(recipeService.getRecipe(), HttpStatus.OK);
    }

    @RequestMapping(value = "/recipe/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getRecipe(@PathVariable("id") int id) {
        Recipe recipe = recipeService.getRecipe(id);
        String errorMessage;
        HttpStatus httpStatus;

        if (recipe != null) {
            httpStatus = HttpStatus.OK;
            return new ResponseEntity<>(recipe, httpStatus);
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: #" + id + ".";
            return new ResponseEntity<>(new Message(errorMessage), httpStatus);
        }
    }

    // UPDATE
    @RequestMapping(value = "/recipe", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateRecipe(@RequestBody Recipe recipe) {
        Integer errorCode = recipeService.updateRecipe(recipe);
        String errorMessage;
        HttpStatus httpStatus;

        if (errorCode == 0) {
            httpStatus = HttpStatus.OK;
            errorMessage = "Recipe: #" + recipe.getRecipeId() + " updated.";
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: " + recipe.getRecipeId() + ".";
        }

        return new ResponseEntity<>(new Message(errorMessage), httpStatus);
    }

    // DELETE
    @RequestMapping(value = "/recipe/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteRecipe(@PathVariable("id") int id) {
        Integer errorCode = recipeService.deleteRecipe(id);
        String errorMessage;
        HttpStatus httpStatus;

        if (errorCode == 0) {
            httpStatus = HttpStatus.OK;
            errorMessage = "Recipe: #" + id + " deleted.";
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: #" + id + ".";
        }

        return new ResponseEntity<>(new Message(errorMessage), httpStatus);
    }
}
