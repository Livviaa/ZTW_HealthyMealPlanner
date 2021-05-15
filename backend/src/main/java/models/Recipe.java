package models;

public class Recipe {

    private Integer recipeId;
    private String mealName;
    private String text;
    private String photo;

    public Recipe(Integer recipeId, String mealName, String text, String photo) {
        this.recipeId = recipeId;
        this.mealName = mealName;
        this.text = text;
        this.photo = photo;
    }

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
