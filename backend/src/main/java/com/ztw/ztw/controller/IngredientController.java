package com.ztw.ztw.controller;

import com.ztw.ztw.model.Ingredient;
import com.ztw.ztw.model.Message;
import com.ztw.ztw.service.IngredientService;
import com.ztw.ztw.service.MegaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;
    private final MegaService megaService;

    // CREATE
    @PostMapping("/ingredients")
    public ResponseEntity<Object> addIngredient(@RequestBody Ingredient ingredient) {
        Ingredient createdIngredient = ingredientService.addIngredient(ingredient);
        String errorMessage;
        HttpStatus httpStatus;

        if (createdIngredient != null) {
            httpStatus = HttpStatus.OK;
            errorMessage = "Ingredient: " + createdIngredient.getIngredientId() + " created.";
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: " + ingredient.getIngredientId() + ".";
        }
        return new ResponseEntity<>(new Message(errorMessage), httpStatus);
    }

    // READ
    @GetMapping("/ingredients")
    public List<Ingredient> getIngredients() {
        return ingredientService.getIngredients();
    }

    @GetMapping("/ingredients/{id}")
    public ResponseEntity<Object> getSingleIngredient(@PathVariable Integer id) {
        Ingredient ingredient = ingredientService.getSingleIngredient(id);
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
    @PutMapping("/ingredients")
    public ResponseEntity<Object> editIngredient(@RequestBody Ingredient ingredient) {
        Ingredient editedIngredient = ingredientService.editIngredient(ingredient);
        String errorMessage;
        HttpStatus httpStatus;

        if (editedIngredient != null) {
            httpStatus = HttpStatus.OK;
            errorMessage = "Updated: #" + editedIngredient.getIngredientId() + ".";
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: #" + ingredient.getIngredientId() + ".";
        }
        megaService.updateMacroElementsAll();
        return new ResponseEntity<>(new Message(errorMessage), httpStatus);
    }

    // DELETE
    @DeleteMapping("/ingredients/{id}")
    public ResponseEntity<Object> deleteIngredient(@PathVariable Integer id) {
        boolean result = ingredientService.deleteIngredient(id);
        String errorMessage;
        HttpStatus httpStatus;

        if (result) {
            httpStatus = HttpStatus.OK;
            errorMessage = "Ingredient: #" + id + " deleted.";
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: #" + id + ".";
        }
        megaService.updateMacroElementsAll();
        return new ResponseEntity<>(new Message(errorMessage), httpStatus);
    }
}
