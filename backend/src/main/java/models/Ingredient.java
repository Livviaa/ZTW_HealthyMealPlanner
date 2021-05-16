package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Ingredient {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer IngredientId;
    private String Name;
    private String MeasureUnit;
    private Integer Units;
    private Double KcalPer100;
    private Double ProteinPer100;
    private Double FatsPer100;
    private Double CarbohydratesPer100;

    public Ingredient() {
    }

    public Ingredient(Integer ingredientId, String name, String measureUnit, Integer units, Double kcalPer100, Double proteinPer100, Double fatsPer100, Double carbohydratesPer100) {
        IngredientId = ingredientId;
        Name = name;
        MeasureUnit = measureUnit;
        Units = units;
        KcalPer100 = kcalPer100;
        ProteinPer100 = proteinPer100;
        FatsPer100 = fatsPer100;
        CarbohydratesPer100 = carbohydratesPer100;
    }

    @JsonIgnore
    public Integer getIngredientId() {
        return IngredientId;
    }

    @JsonIgnore
    public void setIngredientId(Integer ingredientId) {
        IngredientId = ingredientId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMeasureUnit() {
        return MeasureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        MeasureUnit = measureUnit;
    }

    public Integer getUnits() {
        return Units;
    }

    public void setUnits(Integer units) {
        Units = units;
    }

    public Double getKcalPer100() {
        return KcalPer100;
    }

    public void setKcalPer100(Double kcalPer100) {
        KcalPer100 = kcalPer100;
    }

    public Double getProteinPer100() {
        return ProteinPer100;
    }

    public void setProteinPer100(Double proteinPer100) {
        ProteinPer100 = proteinPer100;
    }

    public Double getFatsPer100() {
        return FatsPer100;
    }

    public void setFatsPer100(Double fatsPer100) {
        FatsPer100 = fatsPer100;
    }

    public Double getCarbohydratesPer100() {
        return CarbohydratesPer100;
    }

    public void setCarbohydratesPer100(Double carbohydratesPer100) {
        CarbohydratesPer100 = carbohydratesPer100;
    }
}
