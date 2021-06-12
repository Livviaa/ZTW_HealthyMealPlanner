package com.ztw.ztw.controller;

import com.ztw.ztw.model.Message;
import com.ztw.ztw.model.Recipe;
import com.ztw.ztw.model.User;
import com.ztw.ztw.service.MegaService;
import com.ztw.ztw.service.RecipeService;
import com.ztw.ztw.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;
    private final MegaService megaService;
    private final UserService userService;

    // SPECIFIC
    @PostMapping("/specific/recipes/{recipeId}/{ingredientId}")
    public ResponseEntity<Object> addIngredientToRecipeActualUser(Principal principal, @PathVariable Integer recipeId, @PathVariable Integer ingredientId) {
        boolean status = recipeService.addRecipeToMeal(recipeId, ingredientId);
        String errorMessage;
        HttpStatus httpStatus;

        if (status) {
            httpStatus = HttpStatus.OK;
            errorMessage = "Added: #" + ingredientId + " to #" + recipeId + " created.";
            megaService.updateMacroElementsAll();
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: #" + ingredientId + " and #" + recipeId + ".";
        }
        megaService.updateMacroElementsAll();
        return new ResponseEntity<>(new Message(errorMessage), httpStatus);
    }

    // SPECIFIC
    @PostMapping("/specific/recipes")
    public ResponseEntity<Object> addRecipeActualUser(Principal principal, @RequestBody Recipe recipe) {
        Map<String, Object> authDetails = (Map<String, Object>) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();
        String name = (String) authDetails.get("first_name");
        String surname = (String) authDetails.get("last_name");
        recipe.setAuthor(name + " " + surname);
        Recipe createRecipe = recipeService.addRecipe(recipe);
        String errorMessage;
        HttpStatus httpStatus;

        if (createRecipe != null) {
            httpStatus = HttpStatus.OK;
            errorMessage = String.valueOf(createRecipe.getRecipeId());
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: " + recipe.getRecipeId() + ".";
        }
        megaService.updateMacroElementsAll();
        return new ResponseEntity<>(new Message(errorMessage), httpStatus);
    }

    // GENERAL
    // CREATE
    @PostMapping("/recipes")
    public ResponseEntity<Object> addRecipe(@RequestBody Recipe recipe) {
        Recipe createRecipe = recipeService.addRecipe(recipe);
        String errorMessage;
        HttpStatus httpStatus;

        if (createRecipe != null) {
            httpStatus = HttpStatus.OK;
            errorMessage = "Recipe: #" + createRecipe.getRecipeId() + " created.";
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: " + recipe.getRecipeId() + ".";
        }
        megaService.updateMacroElementsAll();
        return new ResponseEntity<>(new Message(errorMessage), httpStatus);
    }


    // READ
    @GetMapping("/recipes")
    public List<Recipe> getRecipes() {
        return recipeService.getRecipes();
    }

    @GetMapping("/recipes/{id}")
    public ResponseEntity<Object> getSingleRecipe(@PathVariable Integer id) {
        Recipe recipe = recipeService.getSingleRecipe(id);
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
    @PutMapping("/recipes")
    public ResponseEntity<Object> editRecipe(@RequestBody Recipe recipe) {
        Recipe editedRecipe = recipeService.editRecipe(recipe);
        String errorMessage;
        HttpStatus httpStatus;

        if (editedRecipe != null) {
            httpStatus = HttpStatus.OK;
            errorMessage = "Updated: #" + editedRecipe.getRecipeId() + ".";
            megaService.updateMacroElementsAll();
            return new ResponseEntity<>(errorMessage, httpStatus);
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: #" + recipe.getRecipeId() + ".";
            return new ResponseEntity<>(new Message(errorMessage), httpStatus);
        }
    }


    // DELETE
    @DeleteMapping("/recipes/{id}")
    public ResponseEntity<Object> deleteRecipe(@PathVariable Integer id) {
        boolean result = recipeService.deleteRecipe(id);
        String errorMessage;
        HttpStatus httpStatus;

        if (result) {
            httpStatus = HttpStatus.OK;
            errorMessage = "Recipe: #" + id + " deleted.";
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: #" + id + ".";
        }
        megaService.updateMacroElementsAll();
        return new ResponseEntity<>(new Message(errorMessage), httpStatus);
    }

    // EXTRA
    @PostMapping("/recipes/{recipeId}/{ingredientId}")
    public ResponseEntity<Object> addIngredientToRecipe(@PathVariable Integer recipeId, @PathVariable Integer ingredientId) {
        boolean status = recipeService.addRecipeToMeal(recipeId, ingredientId);
        String errorMessage;
        HttpStatus httpStatus;

        if (status) {
            httpStatus = HttpStatus.OK;
            errorMessage = "Added: #" + ingredientId + " to #" + recipeId + " created.";
            megaService.updateMacroElementsAll();
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: #" + ingredientId + " and #" + recipeId + ".";
        }
        return new ResponseEntity<>(new Message(errorMessage), httpStatus);
    }

    @DeleteMapping("/recipes/{recipesId}/{IngredientId}")
    public ResponseEntity<Object> deleteIngredientFromRecipe(@PathVariable Integer recipeId, @PathVariable Integer ingredientId) {
        boolean status = recipeService.deleteIngredientFromRecipe(recipeId, ingredientId);
        String errorMessage;
        HttpStatus httpStatus;

        if (status) {
            httpStatus = HttpStatus.OK;
            errorMessage = "Deleted: #" + ingredientId + " from #" + recipeId + " created.";
            megaService.updateMacroElementsAll();
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: #" + ingredientId + " and #" + recipeId + ".";
        }
        return new ResponseEntity<>(new Message(errorMessage), httpStatus);
    }
}
