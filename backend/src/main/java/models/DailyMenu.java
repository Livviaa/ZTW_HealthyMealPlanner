package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class DailyMenu {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer DailyMenuId;
    private Integer UserId;
    private Date Date;
    private Double SumKcal;
    private Double SumProtein;
    private Double SumFats;
    private Double SumCarbohydrates;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Meal> Meals;

    public DailyMenu() {
    }

    public DailyMenu(Integer dailyMenuId, Integer userId, Date date, Double sumKcal, Double sumProtein, Double sumFats, Double sumCarbohydrates, List<Meal> meals) {
        DailyMenuId = dailyMenuId;
        UserId = userId;
        Date = date;
        SumKcal = sumKcal;
        SumProtein = sumProtein;
        SumFats = sumFats;
        SumCarbohydrates = sumCarbohydrates;
        Meals = meals;
    }

    @JsonIgnore
    public Integer getDailyMenuId() {
        return DailyMenuId;
    }

    @JsonIgnore
    public void setDailyMenuId(Integer dailyMenuId) {
        DailyMenuId = dailyMenuId;
    }

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date date) {
        Date = date;
    }

    public Double getSumKcal() {
        return SumKcal;
    }

    public void setSumKcal(Double sumKcal) {
        SumKcal = sumKcal;
    }

    public Double getSumProtein() {
        return SumProtein;
    }

    public void setSumProtein(Double sumProtein) {
        SumProtein = sumProtein;
    }

    public Double getSumFats() {
        return SumFats;
    }

    public void setSumFats(Double sumFats) {
        SumFats = sumFats;
    }

    public Double getSumCarbohydrates() {
        return SumCarbohydrates;
    }

    public void setSumCarbohydrates(Double sumCarbohydrates) {
        SumCarbohydrates = sumCarbohydrates;
    }

    @JsonIgnore
    public List<Meal> getMeals() {
        return Meals;
    }

    @JsonIgnore
    public void setMeals(List<Meal> meals) {
        Meals = meals;
    }
}
