package services;

import models.DailyMenu;

import java.util.Collection;

public interface IDailyMenuService {

    // CREATE
    public abstract Integer createDailyMenu(DailyMenu dailyMenu);

    // READ
    public abstract DailyMenu getDailyMenu(Integer id);
    public abstract Collection<DailyMenu> getDailyMenu();

    // UPDATE
    public abstract Integer updateDailyMenu(DailyMenu dailyMenu);

    // DELETE
    public abstract Integer deleteDailyMenu(Integer id);
}
