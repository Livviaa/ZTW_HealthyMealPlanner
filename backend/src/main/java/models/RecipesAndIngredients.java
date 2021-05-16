package models;

public class RecipesAndIngredients {
    private Integer RecipeId;
    private Integer IngredientId;

    public RecipesAndIngredients() {
    }

    public RecipesAndIngredients(Integer recipeId, Integer ingredientId) {
        RecipeId = recipeId;
        IngredientId = ingredientId;
    }

    public Integer getRecipeId() {
        return RecipeId;
    }

    public void setRecipeId(Integer recipeId) {
        RecipeId = recipeId;
    }

    public Integer getIngredientId() {
        return IngredientId;
    }

    public void setIngredientId(Integer ingredientId) {
        IngredientId = ingredientId;
    }
}
