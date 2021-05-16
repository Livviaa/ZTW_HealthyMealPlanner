package models;

import java.util.List;

public class Recipe {

    private Integer RecipeId;
    private String Name;
    private String Instruction;
    private String Image;
    private Double SumKcal;
    private Double SumProtein;
    private Double SumFats;
    private Double SumCarbohydrates;
    private List<Ingredient> Ingredients;

    public Recipe() {
    }

    public Recipe(Integer recipeId, String name, String instruction, String image, Double sumKcal, Double sumProtein, Double sumFats, Double sumCarbohydrates, List<Ingredient> ingredients) {
        RecipeId = recipeId;
        Name = name;
        Instruction = instruction;
        Image = image;
        SumKcal = sumKcal;
        SumProtein = sumProtein;
        SumFats = sumFats;
        SumCarbohydrates = sumCarbohydrates;
        Ingredients = ingredients;
    }

    public Integer getRecipeId() {
        return RecipeId;
    }

    public void setRecipeId(Integer recipeId) {
        RecipeId = recipeId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getInstruction() {
        return Instruction;
    }

    public void setInstruction(String instruction) {
        Instruction = instruction;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
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

    public List<Ingredient> getIngredients() {
        return Ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        Ingredients = ingredients;
    }
}
