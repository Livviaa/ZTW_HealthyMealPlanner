package com.ztw.ztw.controller;

import com.ztw.ztw.ZtwApplication;
import com.ztw.ztw.model.DailyMenu;
import com.ztw.ztw.model.Meal;
import com.ztw.ztw.model.Message;
import com.ztw.ztw.model.User;
import com.ztw.ztw.service.DailyMenuService;
import com.ztw.ztw.service.MealService;
import com.ztw.ztw.service.MegaService;
import com.ztw.ztw.service.UserService;
import com.ztw.ztw.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class DailyMenuController {

    private final DailyMenuService dailyMenuService;
    private final MegaService megaService;
    private final UserService userService;
    private final MealService mealService;

    // SPECIFIC
    @GetMapping("/specific/menus")
    public List<DailyMenu> getActualUserDailyMenus(Principal principal) {
        List<DailyMenu> allMenus = dailyMenuService.getDailyMenus();
        String email = Utils.getEmail(principal);
        User user = userService.getUserByEmail(email);

        return allMenus.stream().filter(m -> m.getUserId().equals(user.getUserId())).collect(Collectors.toList());
    }

    @GetMapping("/specific/menus/{date}")
    public DailyMenu getActualUserDailyMenuFromDate(Principal principal, @PathVariable String date) {
        List<DailyMenu> allMenus = dailyMenuService.getDailyMenus();
        String email = Utils.getEmail(principal);
        User user = userService.getUserByEmail(email);
        megaService.updateMacroElementsAll();
        return allMenus.stream()
                .filter(m ->
                        Utils.parseDate(m.getDate()).equals(date) && m.getUserId().equals(user.getUserId()))
                .findFirst()
                .orElse(null);
    }

    @PutMapping("specific/menus/{date}")
    public DailyMenu initDailyMenuWithMealsForActualUserForDate(Principal principal, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        String email = Utils.getEmail(principal);
        User user = userService.getUserByEmail(email);

        date = new Date(date.getTime() + TimeUnit.HOURS.toMillis(2));

        List<DailyMenu> foundMenus = new ArrayList<>();
        for (DailyMenu x : dailyMenuService.getDailyMenus()) {
            if (x.getDate().getDay() == date.getDay() && x.getDate().getMonth() == date.getMonth() && x.getDate().getYear() == date.getYear()) {
                foundMenus.add(x);
            }
        }

        System.out.println(foundMenus.size());

        megaService.updateMacroElementsAll();
        if (foundMenus.size() == 0) {
            // Menu
            DailyMenu newMenu = new DailyMenu();
            newMenu.setDate(date);
            newMenu.setUserId(user.getUserId());
            Integer newMenuId = dailyMenuService.addDailyMenu(newMenu).getDailyMenuId();

            // Meals
            Meal breakfast = new Meal();
            breakfast.setDailyMenuId(newMenuId);
            mealService.addMeal(breakfast);
            Meal dinner = new Meal();
            dinner.setDailyMenuId(newMenuId);
            mealService.addMeal(dinner);
            Meal sapper = new Meal();
            sapper.setDailyMenuId(newMenuId);
            mealService.addMeal(sapper);
            return newMenu;
        }
        return null;
    }

    // GENERAL
    // CREATED
    @PostMapping("/menus")
    public ResponseEntity<Object> addMenu(@RequestBody DailyMenu dailyMenu) {
        DailyMenu createdMenu = dailyMenuService.addDailyMenu(dailyMenu);
        String errorMessage;
        HttpStatus httpStatus;

        if (createdMenu != null) {
            httpStatus = HttpStatus.OK;
            errorMessage = "Menu: #" + createdMenu.getDailyMenuId() + " created.";
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: " + dailyMenu + ".";
        }
        return new ResponseEntity<>(new Message(errorMessage), httpStatus);
    }

    // READ
    @GetMapping("/menus")
    public List<DailyMenu> getDailyMenus() {
        return dailyMenuService.getDailyMenus();
    }

    @GetMapping("/menus/{id}")
    public ResponseEntity<Object> getSingleDailyMenu(@PathVariable Integer id) {
        DailyMenu dailyMenu = dailyMenuService.getSingleDailyMenu(id);
        String errorMessage;
        HttpStatus httpStatus;

        if (dailyMenu != null) {
            httpStatus = HttpStatus.OK;
            return new ResponseEntity<>(dailyMenu, httpStatus);
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: #" + id + ".";
            return new ResponseEntity<>(new Message(errorMessage), httpStatus);
        }
    }

    // UPDATE
    @PutMapping("/menus")
    public ResponseEntity<Object> editMenu(@RequestBody DailyMenu dailyMenu) {
        DailyMenu editedMenu = dailyMenuService.editDailyMenu(dailyMenu);
        String errorMessage;
        HttpStatus httpStatus;

        if (editedMenu != null) {
            httpStatus = HttpStatus.OK;
            errorMessage = "Updated: #" + editedMenu.getDailyMenuId() + ".";
            return new ResponseEntity<>(errorMessage, httpStatus);
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: #" + dailyMenu.getDailyMenuId() + ".";
            return new ResponseEntity<>(new Message(errorMessage), httpStatus);
        }
    }

    // DELETE
    @DeleteMapping("/menus/{id}")
    public ResponseEntity<Object> deleteMenu(@PathVariable Integer id) {
        boolean result = dailyMenuService.deleteDailyMenu(id);
        String errorMessage;
        HttpStatus httpStatus;

        if (result) {
            httpStatus = HttpStatus.OK;
            errorMessage = "Daily menu: #" + id + " deleted.";
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: #" + id + ".";
        }
        megaService.updateMacroElementsAll();
        return new ResponseEntity<>(new Message(errorMessage), httpStatus);
    }
}
