package general;

import models.DailyMenu;
import models.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import services.IDailyMenuService;
import services.IMealsService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class DailyMenuService implements IDailyMenuService {

    @Autowired
    IMealsService mealsService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Integer createDailyMenu(DailyMenu dailyMenu) {
        Integer errorCode;

        if (dailyMenu.getDailyMenuId() == null) {
            errorCode = -1;
        } else if (dailyMenu.getUserId() == null) {
            errorCode = -2;
        } else if (dailyMenu.getDate() == null) {
            errorCode = -3;
        } else if (dailyMenu.getSumKcal() == null) {
            errorCode = -4;
        } else if (dailyMenu.getSumProtein() == null) {
            errorCode = -5;
        } else if (dailyMenu.getSumFats() == null) {
            errorCode = -6;
        } else if (dailyMenu.getSumCarbohydrates() == null) {
            errorCode = -7;
        } else {
            jdbcTemplate.update(
                    "INSERT INTO DailyMenu VALUES (?, ?, ?, ?, ?, ?)",
                    dailyMenu.getUserId(),
                    dailyMenu.getDate(),
                    dailyMenu.getSumKcal(),
                    dailyMenu.getSumProtein(),
                    dailyMenu.getSumFats(),
                    dailyMenu.getSumCarbohydrates()
            );
            errorCode = 0;
        }

        return errorCode;
    }

    @Override
    public DailyMenu getDailyMenu(Integer id) {
        String sql = "SELECT * FROM DailyMenu WHERE DailyMenuId = " + id;
        List<DailyMenu> menus = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(DailyMenu.class));

        if (menus.size() == 0) {
            return null;
        } else {
            DailyMenu menu = menus.get(0);
            List<Meal> meals = (List<Meal>) mealsService.getMeal();
            for (Meal meal : meals){
                if(menu.getDailyMenuId().equals(meal.getDailyMenuId())){
                    if(menu.getMeals() == null)
                        menu.setMeals(new ArrayList<>());
                    menu.getMeals().add(meal);
                }
            }
            return menu;
        }
    }

    @Override
    public Collection<DailyMenu> getDailyMenu() {
        String sql = "SELECT * FROM DailyMenu";
        List<DailyMenu> menus = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(DailyMenu.class));
        List<Meal> meals = (List<Meal>) mealsService.getMeal();

        for (DailyMenu menu : menus) {
            for (Meal meal : meals) {
                if(menu.getDailyMenuId().equals(meal.getDailyMenuId())){
                    if(menu.getMeals() == null)
                        menu.setMeals(new ArrayList<>());
                    menu.getMeals().add(meal);
                }
            }
        }

        return menus;
    }

    @Override
    public Integer updateDailyMenu(DailyMenu dailyMenu) {
        Integer errorCode;
        DailyMenu menuToUpdate = getDailyMenu(dailyMenu.getUserId());

        if (menuToUpdate == null) {
            errorCode = -1;
        } else if (dailyMenu.getUserId() == null) {
            errorCode = -2;
        } else if (dailyMenu.getDate() == null) {
            errorCode = -3;
        } else if (dailyMenu.getSumKcal() == null) {
            errorCode = -4;
        } else if (dailyMenu.getSumProtein() == null) {
            errorCode = -5;
        } else if (dailyMenu.getSumFats() == null) {
            errorCode = -6;
        } else if (dailyMenu.getSumCarbohydrates() == null) {
            errorCode = -7;
        } else {
            String SQL = "UPDATE DailyMenu SET UserId = ?, [Date] = ?, SumKcal = ?, SumProtein = ?, " +
                    "SumFats = ?, SumCarbohydrates = ? WHERE DailyMenuId = ?";
            jdbcTemplate.update(SQL,
                    dailyMenu.getUserId(),
                    dailyMenu.getDate(),
                    dailyMenu.getSumKcal(),
                    dailyMenu.getSumProtein(),
                    dailyMenu.getSumFats(),
                    dailyMenu.getSumCarbohydrates(),
                    dailyMenu.getDailyMenuId()
            );
            errorCode = 0;
        }
        return errorCode;
    }

    @Override
    public Integer deleteDailyMenu(Integer id) {
        DailyMenu dailyMenuToDelete = getDailyMenu(id);
        if (dailyMenuToDelete != null) {
            String SQL = "DELETE FROM DailyMenu WHERE DailyMenuId = ?";
            jdbcTemplate.update(SQL, id);
            return 0;
        }
        return 1;
    }
}


