package com.ztw.ztw.controller;

import com.ztw.ztw.model.DailyMenu;
import com.ztw.ztw.model.Meal;
import com.ztw.ztw.model.Message;
import com.ztw.ztw.model.User;
import com.ztw.ztw.service.MealService;
import com.ztw.ztw.service.MegaService;
import com.ztw.ztw.service.UserService;
import com.ztw.ztw.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MealController {

    private final MealService mealService;
    private final MegaService megaService;
    private final UserService userService;

    // SPECIFIC
    @PostMapping("/specific/meals/{mealId}/{recipeId}")
    public ResponseEntity<Object> addRecipeToMealOfActualUser(Principal principal, @PathVariable Integer mealId, @PathVariable Integer recipeId) {
        boolean status = mealService.addRecipeToMeal(mealId, recipeId);
        String errorMessage;
        HttpStatus httpStatus;

        if (status) {
            httpStatus = HttpStatus.OK;
            errorMessage = "Added: #" + recipeId + " to #" + mealId + " created.";
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: #" + recipeId + " and #" + mealId + ".";
        }
        megaService.updateMacroElementsAll();
        return new ResponseEntity<>(new Message(errorMessage), httpStatus);
    }

    @DeleteMapping("/specific/meals/{mealId}/{recipeId}")
    public ResponseEntity<Object> deleteRecipeFromMealOfActualUser(Principal principal, @PathVariable Integer mealId, @PathVariable Integer recipeId) {
        boolean status = mealService.deleteRecipeFromMeal(mealId, recipeId);
        String errorMessage;
        HttpStatus httpStatus;

        if (status) {
            httpStatus = HttpStatus.OK;
            errorMessage = "Deleted: #" + recipeId + " from #" + mealId + " created.";
            megaService.updateMacroElementsAll();
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: #" + recipeId + " and #" + mealId + ".";
        }
        return new ResponseEntity<>(new Message(errorMessage), httpStatus);
    }

    // GENERAL
    // CREATE
    @PostMapping("/meals")
    public ResponseEntity<Object> addMeal(@RequestBody Meal meal) {
        Meal createdMeal = mealService.addMeal(meal);
        String errorMessage;
        HttpStatus httpStatus;

        if (createdMeal != null) {
            httpStatus = HttpStatus.OK;
            errorMessage = "Meal: #" + meal.getMealId() + " created.";
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: " + meal.getMealId() + ".";
        }
        megaService.updateMacroElementsAll();
        return new ResponseEntity<>(new Message(errorMessage), httpStatus);
    }

    // READ
    @GetMapping("/meals")
    public List<Meal> getMeals() {
        return mealService.getMeals();
    }

    @GetMapping("/meals/{id}")
    public ResponseEntity<Object> getSingleMeal(@PathVariable Integer id) {
        Meal meal = mealService.getSingleMeal(id);
        String errorMessage;
        HttpStatus httpStatus;

        if (meal != null) {
            httpStatus = HttpStatus.OK;
            return new ResponseEntity<>(meal, httpStatus);
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: #" + id + ".";
            return new ResponseEntity<>(new Message(errorMessage), httpStatus);
        }
    }

    // UPDATE
    @PutMapping("/meals")
    public ResponseEntity<Object> editMeal(@RequestBody Meal meal) {
        Meal editedMeal = mealService.editMeal(meal);
        String errorMessage;
        HttpStatus httpStatus;

        if (editedMeal != null) {
            httpStatus = HttpStatus.OK;
            errorMessage = "Updated: #" + editedMeal.getMealId() + ".";
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: #" + meal.getMealId() + ".";
        }
        megaService.updateMacroElementsAll();
        return new ResponseEntity<>(new Message(errorMessage), httpStatus);
    }

    // DELETE
    @DeleteMapping("/meals/{id}")
    public ResponseEntity<Object> deleteMeal(@PathVariable Integer id) {
        boolean result = mealService.deleteMeal(id);
        String errorMessage;
        HttpStatus httpStatus;

        if (result) {
            httpStatus = HttpStatus.OK;
            errorMessage = "Meal: #" + id + " deleted.";
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: #" + id + ".";
        }
        megaService.updateMacroElementsAll();
        return new ResponseEntity<>(new Message(errorMessage), httpStatus);
    }

    // EXTRA
    @PostMapping("/meals/{mealId}/{recipeId}")
    public ResponseEntity<Object> addRecipeToMeal(@PathVariable Integer mealId, @PathVariable Integer recipeId) {
        boolean status = mealService.addRecipeToMeal(mealId, recipeId);
        String errorMessage;
        HttpStatus httpStatus;

        if (status) {
            httpStatus = HttpStatus.OK;
            errorMessage = "Added: #" + recipeId + " to #" + mealId + " created.";
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: #" + recipeId + " and #" + mealId + ".";
        }
        megaService.updateMacroElementsAll();
        return new ResponseEntity<>(new Message(errorMessage), httpStatus);
    }

    @DeleteMapping("/meals/{mealId}/{recipeId}")
    public ResponseEntity<Object> deleteRecipeFromMeal(@PathVariable Integer mealId, @PathVariable Integer recipeId) {
        boolean status = mealService.deleteRecipeFromMeal(mealId, recipeId);
        String errorMessage;
        HttpStatus httpStatus;

        if (status) {
            httpStatus = HttpStatus.OK;
            errorMessage = "Deleted: #" + recipeId + " from #" + mealId + " created.";
            megaService.updateMacroElementsAll();
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: #" + recipeId + " and #" + mealId + ".";
        }
        return new ResponseEntity<>(new Message(errorMessage), httpStatus);
    }
}
