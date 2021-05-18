package general;

import models.DailyMenu;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import services.IDailyMenuService;
import services.IUsersService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

        if (user.getName() == null) {
            errorCode = -2;
        } else if (user.getSurname() == null) {
            errorCode = -3;
        } else if (user.getSex() == null) {
            errorCode = -4;
        } else if (user.getWeight() == null) {
            errorCode = -5;
        } else if (user.getHeight() == null) {
            errorCode = -6;
        } else if (user.getActivity() == null) {
            errorCode = -7;
        } else if (user.getEmail() == null) {
            errorCode = -8;
        } else if (user.getPassword() == null) {
            errorCode = -9;
        } else {
            jdbcTemplate.update(
                    "INSERT INTO [User] VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                    user.getName(),
                    user.getSurname(),
                    user.getSex(),
                    user.getWeight(),
                    user.getHeight(),
                    user.getActivity(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getRecommendedDailyKcal(),
                    user.getRecommendedDailyProtein(),
                    user.getRecommendedDailyFats(),
                    user.getRecommendedDailyCarbohydrates()
            );
            errorCode = 0;
        }

        return errorCode;
    }

    // READ
    @Override
    public User getUser(Integer id) {
        String sql = "SELECT * FROM [User] WHERE UserId = " + id;
        List<User> users = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(User.class));

        if (users.size() == 0) {
            return null;
        } else {
            User user = users.get(0);
            List<DailyMenu> menus = (List<DailyMenu>) dailyMenuService.getDailyMenu();
            for (DailyMenu menu : menus) {
                if (user.getUserId().equals(menu.getUserId())) {
                    if (user.getDailyMeals() == null)
                        user.setDailyMeals(new ArrayList<>());
                    user.getDailyMeals().add(menu);
                }
            }
            return user;
        }
    }

    @Override
    public Collection<User> getUser() {
        String sql = "SELECT * FROM [User]";
        List<User> users = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(User.class));
        List<DailyMenu> menus = (List<DailyMenu>) dailyMenuService.getDailyMenu();

        for (User user : users) {
            for (DailyMenu menu : menus) {
                if (user.getUserId().equals(menu.getUserId())) {
                    if (user.getDailyMeals() == null)
                        user.setDailyMeals(new ArrayList<>());
                    user.getDailyMeals().add(menu);
                }
            }
        }
        return users;
    }

    // UPDATE
    @Override
    public Integer updateUser(User user) {
        Integer errorCode;
        User userToUpdate = getUser(user.getUserId());

        if (userToUpdate == null) {
            errorCode = -1;
        } else if (user.getName() == null) {
            errorCode = -2;
        } else if (user.getSurname() == null) {
            errorCode = -3;
        } else if (user.getSex() == null) {
            errorCode = -4;
        } else if (user.getWeight() == null) {
            errorCode = -5;
        } else if (user.getHeight() == null) {
            errorCode = -6;
        } else if (user.getActivity() == null) {
            errorCode = -7;
        } else if (user.getEmail() == null) {
            errorCode = -8;
        } else if (user.getPassword() == null) {
            errorCode = -9;
        } else {
            String SQL = "UPDATE User SET [Name] = ?, Surname = ?, Sex = ?, [Weight] = ?, Height = ?, Activity = ?, " +
                    "Email = ?, [Password] = ?, RecommendedDailyKcal = ?, RecommendedDailyProtein = ?, " +
                    "RecommendedDailyFats = ?, RecommendedDailyCarbohydrates = ? WHERE UserId = ?";
            jdbcTemplate.update(SQL,
                    user.getName(),
                    user.getSurname(),
                    user.getSex(),
                    user.getWeight(),
                    user.getHeight(),
                    user.getActivity(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getRecommendedDailyKcal(),
                    user.getRecommendedDailyProtein(),
                    user.getRecommendedDailyFats(),
                    user.getRecommendedDailyCarbohydrates(),
                    user.getUserId());
            errorCode = 0;
        }
        return errorCode;
    }

    // DELETE
    @Override
    public Integer deleteUser(Integer id) {
        User userToDelete = getUser(id);
        if (userToDelete != null) {
            String SQL = "DELETE FROM [User] WHERE UserId = ?";
            jdbcTemplate.update(SQL, id);
            return 0;
        }
        return 1;
    }
}
