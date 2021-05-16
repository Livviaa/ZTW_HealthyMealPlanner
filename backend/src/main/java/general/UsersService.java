package general;

import models.DailyMenu;
import models.Meal;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import services.IDailyMenuService;
import services.IMealsService;
import services.IUsersService;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//{
//        "uzytkownik_id": 6,
//        "zalecane_dzienne_weglowodany": 180,
//        "zalecane_dzienne_tluszcze": 160,
//        "imie": "Ola",
//        "zalecane_dzienne_kcal": 1500,
//        "zalecane_dzienne_bialko": 20,
//        "email": "ola.w@gmail.com",
//        "naziwsko": "w",
//        "plec": "K",
//        "aktywnosc": "duzo",
//        "wzrost": 160,
//        "waga": 50,
//        "haslo": "12345"
//        }

@Service
public class UsersService implements IUsersService {

    @Autowired
    IDailyMenuService dailyMenuService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // CREATE
    @Override
    public Integer createUser(User user) {
        Integer errorCode;

        if (user.getUzytkownik_id() == null) {
            errorCode = -1;
        } else if (user.getImie() == null) {
            errorCode = -2;
        } else if (user.getNazwisko() == null) {
            errorCode = -3;
        } else if (user.getPlec() == null) {
            errorCode = -4;
        } else if (user.getWaga() == null) {
            errorCode = -5;
        } else if (user.getWzrost() == null) {
            errorCode = -6;
        } else if (user.getAktywnosc() == null) {
            errorCode = -7;
        } else if (user.getEmail() == null) {
            errorCode = -8;
        } else if (user.getHaslo() == null) {
            errorCode = -9;
        } else if (user.getZalecane_dzienne_kcal() == null) {
            errorCode = -10;
        } else if (user.getZalecane_dzienne_bialko() == null) {
            errorCode = -11;
        } else if (user.getZalecane_dzienne_tluszcze() == null) {
            errorCode = -12;
        } else if (user.getZalecane_dzienne_weglowodany() == null) {
            errorCode = -13;
        } else {
            jdbcTemplate.update(
                    "INSERT INTO Uzytkownik VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                    user.getImie(),
                    user.getNazwisko(),
                    user.getPlec(),
                    user.getWaga(),
                    user.getWzrost(),
                    user.getAktywnosc(),
                    user.getEmail(),
                    user.getHaslo(),
                    user.getZalecane_dzienne_kcal(),
                    user.getZalecane_dzienne_bialko(),
                    user.getZalecane_dzienne_tluszcze(),
                    user.getZalecane_dzienne_weglowodany()
            );
            errorCode = 0;
        }

        return errorCode;
    }

    // READ
    @Override
    public User getUser(Integer id) {
        String sql = "SELECT * FROM Uzytkownik WHERE Uzytkownik_id = " + id;
        List<User> users = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(User.class));

        if (users.size() == 0){
            return null;
        }
        else {
            User user = users.get(0);
            List<DailyMenu> menus = (List<DailyMenu>) dailyMenuService.getDailyMenu();
            for (DailyMenu menu : menus) {
                if(user.getUzytkownik_id().equals(menu.getUzytkownik_id())){
                    if(user.getJadlospisy() == null)
                        user.setJadlospisy(new ArrayList<>());
                    user.getJadlospisy().add(menu);
                }
            }
            return user;
        }
    }

    @Override
    public Collection<User> getUser() {
        String sql = "SELECT * FROM Uzytkownik";
        List<User> users = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(User.class));
        List<DailyMenu> menus = (List<DailyMenu>) dailyMenuService.getDailyMenu();

        for (User user : users) {
            for (DailyMenu menu : menus) {
                if(user.getUzytkownik_id().equals(menu.getUzytkownik_id())){
                    if(user.getJadlospisy() == null)
                        user.setJadlospisy(new ArrayList<>());
                    user.getJadlospisy().add(menu);
                }
            }
        }
        return users;
    }

    // UPDATE
    @Override
    public Integer updateUser(User user) {
        Integer errorCode;
        User userToUpdate = getUser(user.getUzytkownik_id());

        if (userToUpdate == null) {
            errorCode = -1;
        } else if (user.getImie() == null) {
            errorCode = -2;
        } else if (user.getNazwisko() == null) {
            errorCode = -3;
        } else if (user.getPlec() == null) {
            errorCode = -4;
        } else if (user.getWaga() == null) {
            errorCode = -5;
        } else if (user.getWzrost() == null) {
            errorCode = -6;
        } else if (user.getAktywnosc() == null) {
            errorCode = -7;
        } else if (user.getEmail() == null) {
            errorCode = -8;
        } else if (user.getHaslo() == null) {
            errorCode = -9;
        } else if (user.getZalecane_dzienne_kcal() == null) {
            errorCode = -10;
        } else if (user.getZalecane_dzienne_bialko() == null) {
            errorCode = -11;
        } else if (user.getZalecane_dzienne_tluszcze() == null) {
            errorCode = -12;
        } else if (user.getZalecane_dzienne_weglowodany() == null) {
            errorCode = -13;
        } else {
            String SQL = "UPDATE Uzytkownik SET Imie = ?, Nazwisko = ?, Plec = ?, Waga = ?, Wzrost = ?, Aktywnosc = ?, " +
                    "Email = ?, Haslo = ?, Zalecane_dzienne_kcal = ?, Zalecane_dzienne_bialko = ?, " +
                    "Zalecane_dzienne_tluszcze = ?, Zalecane_dzienne_weglowodany = ? WHERE Uzytkownik_id = ?";
            jdbcTemplate.update(SQL,
                    user.getImie(),
                    user.getNazwisko(),
                    user.getPlec(),
                    user.getWaga(),
                    user.getWzrost(),
                    user.getAktywnosc(),
                    user.getEmail(),
                    user.getHaslo(),
                    user.getZalecane_dzienne_kcal(),
                    user.getZalecane_dzienne_bialko(),
                    user.getZalecane_dzienne_tluszcze(),
                    user.getZalecane_dzienne_weglowodany(),
                    user.getUzytkownik_id());
            errorCode = 0;
        }
        return errorCode;
    }

    // DELETE
    @Override
    public Integer deleteUser(Integer id) {
        User userToDelete = getUser(id);
        if (userToDelete != null) {
            String SQL = "DELETE FROM Uzytkownik WHERE Uzytkownik_id = ?";
            jdbcTemplate.update(SQL, id);
            return 0;
        }
        return 1;
    }
}
