package general;

import models.DailyMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import services.IDailyMenuService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class DailyMenuService implements IDailyMenuService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Integer createDailyMenu(DailyMenu dailyMenu) {
        Integer errorCode;

        if (dailyMenu.getJadlospis_id() == null) {
            errorCode = -1;
        } else if (dailyMenu.getUzytkownik_id() == null) {
            errorCode = -2;
        } else if (dailyMenu.getData() == null) {
            errorCode = -3;
        } else if (dailyMenu.getSum_kcal() == null) {
            errorCode = -4;
        } else if (dailyMenu.getSum_bialko() == null) {
            errorCode = -5;
        } else if (dailyMenu.getSum_tluszcze() == null) {
            errorCode = -6;
        } else if (dailyMenu.getSum_weglowodany() == null) {
            errorCode = -7;
        } else {
            jdbcTemplate.update(
                    "INSERT INTO Jadlospis VALUES (?, ?, ?, ?, ?, ?)",
                    dailyMenu.getUzytkownik_id(),
                    dailyMenu.getData(),
                    dailyMenu.getSum_kcal(),
                    dailyMenu.getSum_bialko(),
                    dailyMenu.getSum_tluszcze(),
                    dailyMenu.getSum_weglowodany()
            );
            errorCode = 0;
        }

        return errorCode;
    }

    @Override
    public DailyMenu getDailyMenu(Integer id) {
        String sql = "SELECT * FROM Jadlospis WHERE Jadlospis_id = " + id;
        List<DailyMenu> menus = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(DailyMenu.class));

        if (menus.size() == 0) {
            return null;
        } else {
            return menus.get(0);
        }
    }

    @Override
    public Collection<DailyMenu> getDailyMenu() {
        String sql = "SELECT * FROM Jadlospis";
        List<DailyMenu> menus = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(DailyMenu.class));
        return menus;
    }

    @Override
    public Integer updateDailyMenu(DailyMenu dailyMenu) {
        Integer errorCode;
        DailyMenu menuToUpdate = getDailyMenu(dailyMenu.getUzytkownik_id());

        if (menuToUpdate == null) {
            errorCode = -1;
        } else if (dailyMenu.getUzytkownik_id() == null) {
            errorCode = -2;
        } else if (dailyMenu.getData() == null) {
            errorCode = -3;
        } else if (dailyMenu.getSum_kcal() == null) {
            errorCode = -4;
        } else if (dailyMenu.getSum_bialko() == null) {
            errorCode = -5;
        } else if (dailyMenu.getSum_tluszcze() == null) {
            errorCode = -6;
        } else if (dailyMenu.getSum_weglowodany() == null) {
            errorCode = -7;
        } else {
            String SQL = "UPDATE Jadlospis SET Uzytkownik_id = ?, Data = ?, Sum_kcal = ?, Sum_bialko = ?, " +
                    "Sum_tluszcze = ?, Sum_weglowodany = ? WHERE Jadlospis_id = ?";
            jdbcTemplate.update(SQL,
                    dailyMenu.getUzytkownik_id(),
                    dailyMenu.getData(),
                    dailyMenu.getSum_kcal(),
                    dailyMenu.getSum_bialko(),
                    dailyMenu.getSum_tluszcze(),
                    dailyMenu.getSum_weglowodany(),
                    dailyMenu.getJadlospis_id()
            );
            errorCode = 0;
        }
        return errorCode;
    }

    @Override
    public Integer deleteDailyMenu(Integer id) {
        DailyMenu dailyMenuToDelete = getDailyMenu(id);
        if (dailyMenuToDelete != null) {
            String SQL = "DELETE FROM Jadlospis WHERE Jadlospis_id = ?";
            jdbcTemplate.update(SQL, id);
            return 0;
        }
        return 1;
    }
}


