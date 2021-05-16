package general;

import models.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.IMealsService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class MealsControler {

    @Autowired
    IMealsService mealsService;

    // CREATE
    @RequestMapping(value = "/meal", method = RequestMethod.POST)
    public ResponseEntity<Object> createMeal(@RequestBody Meal meal) {
        Integer errorCode = mealsService.createMeal(meal);
        String errorMessage;
        HttpStatus httpStatus;

        if (errorCode == 0) {
            httpStatus = HttpStatus.OK;
            errorMessage = "Meal: #" + meal.getMealId() + " created.";
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: " + meal.getMealId()+ ".";
        }

        return new ResponseEntity<>(new Message(errorMessage), httpStatus);
    }

    // READ
    @RequestMapping(value = "/meal", method = RequestMethod.GET)
    public ResponseEntity<Object> getMeals() {
        return new ResponseEntity<>(mealsService.getMeal(), HttpStatus.OK);
    }

    @RequestMapping(value = "/meal/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getMeal(@PathVariable("id") int id) {
        Meal meal = mealsService.getMeal(id);
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
    @RequestMapping(value = "/meal", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateMeal(@RequestBody Meal meal) {
        Integer errorCode = mealsService.updateMeal(meal);
        String errorMessage;
        HttpStatus httpStatus;

        if (errorCode == 0) {
            httpStatus = HttpStatus.OK;
            errorMessage = "Meal: #" + meal.getMealId() + " updated.";
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: " + meal.getMealId() + ".";
        }

        return new ResponseEntity<>(new Message(errorMessage), httpStatus);
    }

    // DELETE
    @RequestMapping(value = "/meal/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteMeal(@PathVariable("id") int id) {
        Integer errorCode = mealsService.deleteMeal(id);
        String errorMessage;
        HttpStatus httpStatus;

        if (errorCode == 0) {
            httpStatus = HttpStatus.OK;
            errorMessage = "Meal: #" + id + " deleted.";
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: #" + id + ".";
        }

        return new ResponseEntity<>(new Message(errorMessage), httpStatus);
    }
}
