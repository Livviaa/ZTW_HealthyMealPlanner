package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Meal {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer MealId;
    private Integer DailyMenuId;
    private Double SumKcal;
    private Double SumProtein;
    private Double SumFats;
    private Double SumCarbohydrates;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Recipe> Recipes;

    public Meal() {
    }

    public Meal(Integer mealId, Integer dailyMenuId, Double sumKcal, Double sumProtein, Double sumFats, Double sumCarbohydrates, List<Recipe> recipes) {
        MealId = mealId;
        DailyMenuId = dailyMenuId;
        SumKcal = sumKcal;
        SumProtein = sumProtein;
        SumFats = sumFats;
        SumCarbohydrates = sumCarbohydrates;
        Recipes = recipes;
    }

    @JsonIgnore
    public Integer getMealId() {
        return MealId;
    }

    @JsonIgnore
    public void setMealId(Integer mealId) {
        MealId = mealId;
    }

    public Integer getDailyMenuId() {
        return DailyMenuId;
    }

    public void setDailyMenuId(Integer dailyMenuId) {
        DailyMenuId = dailyMenuId;
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
    public List<Recipe> getRecipes() {
        return Recipes;
    }

    @JsonIgnore
    public void setRecipes(List<Recipe> recipes) {
        Recipes = recipes;
    }
}
