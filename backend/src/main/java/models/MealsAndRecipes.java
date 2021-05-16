package models;

public class MealsAndRecipes {

    private Integer MealId;
    private Integer RecipeId;

    public MealsAndRecipes() {
    }

    public MealsAndRecipes(Integer mealId, Integer recipeId) {
        MealId = mealId;
        RecipeId = recipeId;
    }

    public Integer getMealId() {
        return MealId;
    }

    public void setMealId(Integer mealId) {
        MealId = mealId;
    }

    public Integer getRecipeId() {
        return RecipeId;
    }

    public void setRecipeId(Integer recipeId) {
        RecipeId = recipeId;
    }
}
