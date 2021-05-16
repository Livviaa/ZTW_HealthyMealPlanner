package models;

import java.util.List;

public class User {
    private Integer UserId;
    private String Name;
    private String Surname;
    private String Sex;
    private Double Weight;
    private Integer Height;
    private String Activity;
    private String Email;
    private String Password;
    private Double RecommendedDailyKcal;
    private Double RecommendedDailyProtein;
    private Double RecommendedDailyFats;
    private Double RecommendedDailyCarbohydrates;
    private List<DailyMenu> DailyMeals;

    public User() {
    }

    public User(Integer userId, String name, String surname, String sex, Double weight, Integer height, String activity,
                String email, String password, Double recommendedDailyKcal, Double recommendedDailyProtein,
                Double recommendedDailyFats, Double recommendedDailyCarbohydrates, List<DailyMenu> dailyMeals) {
        UserId = userId;
        Name = name;
        Surname = surname;
        Sex = sex;
        Weight = weight;
        Height = height;
        Activity = activity;
        Email = email;
        Password = password;
        RecommendedDailyKcal = recommendedDailyKcal;
        RecommendedDailyProtein = recommendedDailyProtein;
        RecommendedDailyFats = recommendedDailyFats;
        RecommendedDailyCarbohydrates = recommendedDailyCarbohydrates;
        DailyMeals = dailyMeals;
    }

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public Double getWeight() {
        return Weight;
    }

    public void setWeight(Double weight) {
        Weight = weight;
    }

    public Integer getHeight() {
        return Height;
    }

    public void setHeight(Integer height) {
        Height = height;
    }

    public String getActivity() {
        return Activity;
    }

    public void setActivity(String activity) {
        Activity = activity;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Double getRecommendedDailyKcal() {
        return RecommendedDailyKcal;
    }

    public void setRecommendedDailyKcal(Double recommendedDailyKcal) {
        RecommendedDailyKcal = recommendedDailyKcal;
    }

    public Double getRecommendedDailyProtein() {
        return RecommendedDailyProtein;
    }

    public void setRecommendedDailyProtein(Double recommendedDailyProtein) {
        RecommendedDailyProtein = recommendedDailyProtein;
    }

    public Double getRecommendedDailyFats() {
        return RecommendedDailyFats;
    }

    public void setRecommendedDailyFats(Double recommendedDailyFats) {
        RecommendedDailyFats = recommendedDailyFats;
    }

    public Double getRecommendedDailyCarbohydrates() {
        return RecommendedDailyCarbohydrates;
    }

    public void setRecommendedDailyCarbohydrates(Double recommendedDailyCarbohydrates) {
        RecommendedDailyCarbohydrates = recommendedDailyCarbohydrates;
    }

    public List<DailyMenu> getDailyMeals() {
        return DailyMeals;
    }

    public void setDailyMeals(List<DailyMenu> dailyMeals) {
        DailyMeals = dailyMeals;
    }
}
