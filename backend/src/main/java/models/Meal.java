package models;

public class Meal {
    private Integer mealId;
    private Double sumKcal;
    private Double sumProtein;
    private Double sumFats;
    private Double sumCarbohydrates;

    public Meal(Integer mealId, Double sumKcal, Double sumProtein, Double sumFats, Double sumCarbohydrates) {
        this.mealId = mealId;
        this.sumKcal = sumKcal;
        this.sumProtein = sumProtein;
        this.sumFats = sumFats;
        this.sumCarbohydrates = sumCarbohydrates;
    }

    public Integer getMealId() {
        return mealId;
    }

    public void setMealId(Integer mealId) {
        this.mealId = mealId;
    }

    public Double getSumKcal() {
        return sumKcal;
    }

    public void setSumKcal(Double sumKcal) {
        this.sumKcal = sumKcal;
    }

    public Double getSumProtein() {
        return sumProtein;
    }

    public void setSumProtein(Double sumProtein) {
        this.sumProtein = sumProtein;
    }

    public Double getSumFats() {
        return sumFats;
    }

    public void setSumFats(Double sumFats) {
        this.sumFats = sumFats;
    }

    public Double getSumCarbohydrates() {
        return sumCarbohydrates;
    }

    public void setSumCarbohydrates(Double sumCarbohydrates) {
        this.sumCarbohydrates = sumCarbohydrates;
    }
}
