package models;

public class Ingredient {

    private Integer ingredientId;
    private Integer intgredientName;
    private String unitName;
    private Integer unitsNumber;
    private Double kcalPer100Units;
    private Double proteinPer100Units;
    private Double fatsPer100Units;
    private Double carbohydratesPer100Units;

    public Ingredient(Integer ingredientId, Integer intgredientName, String unitName, Integer unitsNumber,
                      Double kcalPer100Units, Double proteinPer100Units, Double fatsPer100Units, Double carbohydratesPer100Units) {
        this.ingredientId = ingredientId;
        this.intgredientName = intgredientName;
        this.unitName = unitName;
        this.unitsNumber = unitsNumber;
        this.kcalPer100Units = kcalPer100Units;
        this.proteinPer100Units = proteinPer100Units;
        this.fatsPer100Units = fatsPer100Units;
        this.carbohydratesPer100Units = carbohydratesPer100Units;
    }

    public Integer getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Integer ingredientId) {
        this.ingredientId = ingredientId;
    }

    public Integer getIntgredientName() {
        return intgredientName;
    }

    public void setIntgredientName(Integer intgredientName) {
        this.intgredientName = intgredientName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Integer getUnitsNumber() {
        return unitsNumber;
    }

    public void setUnitsNumber(Integer unitsNumber) {
        this.unitsNumber = unitsNumber;
    }

    public Double getKcalPer100Units() {
        return kcalPer100Units;
    }

    public void setKcalPer100Units(Double kcalPer100Units) {
        this.kcalPer100Units = kcalPer100Units;
    }

    public Double getProteinPer100Units() {
        return proteinPer100Units;
    }

    public void setProteinPer100Units(Double proteinPer100Units) {
        this.proteinPer100Units = proteinPer100Units;
    }

    public Double getFatsPer100Units() {
        return fatsPer100Units;
    }

    public void setFatsPer100Units(Double fatsPer100Units) {
        this.fatsPer100Units = fatsPer100Units;
    }

    public Double getCarbohydratesPer100Units() {
        return carbohydratesPer100Units;
    }

    public void setCarbohydratesPer100Units(Double carbohydratesPer100Units) {
        this.carbohydratesPer100Units = carbohydratesPer100Units;
    }
}
