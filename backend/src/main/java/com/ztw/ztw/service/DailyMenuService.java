package com.ztw.ztw.service;

import com.ztw.ztw.model.DailyMenu;
import com.ztw.ztw.model.Meal;
import com.ztw.ztw.repository.DailyMenuRepository;
import com.ztw.ztw.repository.MealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DailyMenuService {
    private final DailyMenuRepository dailyMenuRepository;
    private final MealRepository mealRepository;

    // CREATE
    public DailyMenu addDailyMenu(DailyMenu dailyMenu) {
        return dailyMenuRepository.save(dailyMenu);
    }

    // READ
    public DailyMenu getSingleDailyMenu(Integer id) {
        Optional<DailyMenu> dailyMenu = dailyMenuRepository.findById(id);
        return dailyMenu.orElse(null);
    }

    public List<DailyMenu> getDailyMenus() {
        List<DailyMenu> allMenus = dailyMenuRepository.findAll();
        List<Integer> ids = allMenus.stream()
                .map(DailyMenu::getDailyMenuId)
                .collect(Collectors.toList());
        List<Meal> meals = mealRepository.findAllByDailyMenuIdIn(ids);
        allMenus.forEach(menu -> menu.setMeals(extractMeals(meals, menu.getDailyMenuId())));
        return allMenus;
    }

    private List<Meal> extractMeals(List<Meal> meals, Integer id) {
        return meals.stream()
                .filter(meal -> meal.getDailyMenuId().equals(id))
                .collect(Collectors.toList());
    }

    // UPDATE
    @Transactional
    public DailyMenu editDailyMenu(DailyMenu dailyMenu) {
        Optional<DailyMenu> dailyMenuEditedOptional = dailyMenuRepository.findById(dailyMenu.getDailyMenuId());

        if(dailyMenuEditedOptional.isPresent()){
            DailyMenu dailyMenuEdited = dailyMenuEditedOptional.get();
            dailyMenuEdited.setDate(dailyMenu.getDate());
            return dailyMenuEdited;
        }else{
            return null;
        }
    }

    // DELETE
    public boolean deleteDailyMenu(Integer id) {
        Optional<DailyMenu> deletedMenu = dailyMenuRepository.findById(id);
        if (deletedMenu.isPresent()) {
            dailyMenuRepository.delete(deletedMenu.get());
            return true;
        } else {
            return false;
        }
    }

}
