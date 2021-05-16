package general;

import models.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.IIngredientService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class IngredientsControler {

    @Autowired
    IIngredientService ingredientService;

    // CREATE
    @RequestMapping(value = "/ingredient", method = RequestMethod.POST)
    public ResponseEntity<Object> createIngredient(@RequestBody Ingredient ingredient) {
        Integer errorCode = ingredientService.createIngredient(ingredient);
        String errorMessage;
        HttpStatus httpStatus;

        if (errorCode == 0) {
            httpStatus = HttpStatus.OK;
            errorMessage = "Ingredient: #" + ingredient.getName() + " created.";
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: " + ingredient + ".";
        }

        return new ResponseEntity<>(new Message(errorMessage), httpStatus);
    }

    // READ
    @RequestMapping(value = "/ingredient", method = RequestMethod.GET)
    public ResponseEntity<Object> getIngredients() {
        return new ResponseEntity<>(ingredientService.getIngredient(), HttpStatus.OK);
    }

    @RequestMapping(value = "/ingredient/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getIngredient(@PathVariable("id") int id) {
        Ingredient ingredient = ingredientService.getIngredient(id);
        String errorMessage;
        HttpStatus httpStatus;

        if (ingredient != null) {
            httpStatus = HttpStatus.OK;
            return new ResponseEntity<>(ingredient, httpStatus);
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: #" + id + ".";
            return new ResponseEntity<>(new Message(errorMessage), httpStatus);
        }
    }

    // UPDATE
    @RequestMapping(value = "/ingredient", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateIngredient(@RequestBody Ingredient ingredient) {
        Integer errorCode = ingredientService.updateIngredient(ingredient);
        String errorMessage;
        HttpStatus httpStatus;

        if (errorCode == 0) {
            httpStatus = HttpStatus.OK;
            errorMessage = "Ingredient: #" + ingredient.getName() + " updated.";
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: " + ingredient.getName() + ".";
        }

        return new ResponseEntity<>(new Message(errorMessage), httpStatus);
    }

    // DELETE
    @RequestMapping(value = "/ingredient/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteIngredient(@PathVariable("id") int id) {
        Integer errorCode = ingredientService.deleteIngredient(id);
        String errorMessage;
        HttpStatus httpStatus;

        if (errorCode == 0) {
            httpStatus = HttpStatus.OK;
            errorMessage = "Ingredient: #" + id + " deleted.";
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: #" + id + ".";
        }

        return new ResponseEntity<>(new Message(errorMessage), httpStatus);
    }
}
